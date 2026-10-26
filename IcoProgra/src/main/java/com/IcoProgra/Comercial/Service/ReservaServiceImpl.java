package com.IcoProgra.Comercial.Service;

import com.IcoProgra.Comercial.Domain.EstudianteEntity;
import com.IcoProgra.Comercial.Domain.ReservaEntity;
import com.IcoProgra.Comercial.Repository.EstudianteRepository;
import com.IcoProgra.Comercial.Repository.ReservaRepository;
import jakarta.persistence.EntityExistsException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Vis
 */
@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    ReservaRepository db;

    @Autowired
    EstudianteRepository dbEstudiante;

    @Autowired
    EstudianteService servicioEstudiante;

    @Override
    @Transactional
    public void registrarReserva(ReservaEntity reserva) {
        validar(reserva);

        EstudianteEntity datos = reserva.getEstudiante();
        String rut = EstudianteServiceImpl.normalizarRut(datos.getRut());

        EstudianteEntity estudiante = servicioEstudiante.buscarPorRut(rut).orElseGet(() -> dbEstudiante.save(new EstudianteEntity(datos.getNombre().trim(), rut, datos.getCurso())));

        if (db.existsByEstudianteAndFechaAndHora(estudiante, reserva.getFecha(), reserva.getHora())) {
            throw new EntityExistsException("El estudiante con RUT '" + rut+ "' ya tiene una reserva el "+ reserva.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+ " a las " + reserva.getHora() + ".");
        }
        reserva.setId(null);
        reserva.setEstudiante(estudiante);
        reserva.setContenido(reserva.getContenido().trim());
        db.save(reserva);
    }

    private void validar(ReservaEntity r) {
        EstudianteEntity e;
        if (r == null) {
            e = null;
        } else {
            e = r.getEstudiante();
        }
        if (e == null
                || vacio(e.getNombre()) || vacio(e.getRut()) || vacio(e.getCurso())
                || vacio(r.getContenido()) || r.getFecha() == null || r.getHora() == null) {
            throw new IllegalArgumentException("Todos los campos son obligatorios.");
        }
        if (e.getNombre().trim().length() < 3 || e.getNombre().trim().length() > 100) {
            throw new IllegalArgumentException("El nombre debe tener entre 3 y 100 caracteres.");
        }
        String rut = EstudianteServiceImpl.normalizarRut(e.getRut());
        if (!rut.matches("\\d{7,8}-[\\dK]")) {
            throw new IllegalArgumentException("El RUT no tiene un formato válido (ej: 12345678-9).");
        }
        if (r.getFecha().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de la reserva no puede estar en el pasado.");
        }
    }

    private boolean vacio(String s) {
        return s == null || s.isBlank();
    }
}

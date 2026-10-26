package com.IcoProgra.Comercial.Service;

import com.IcoProgra.Comercial.Domain.EstudianteEntity;
import com.IcoProgra.Comercial.Repository.EstudianteRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Vis
 */
@Service
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    EstudianteRepository db;

    @Override
    public Optional<EstudianteEntity> buscarPorRut(String rut) {
        if (rut == null || rut.isBlank()) {
            return Optional.empty();
        }
        return db.findByRut(normalizarRut(rut));
    }

    // Quita puntos y espacios y deja la K en mayúscula: "12.345.678-k" -> "12345678-K"
    static String normalizarRut(String rut) {
        return rut.replace(".", "").replace(" ", "").toUpperCase();
    }
}

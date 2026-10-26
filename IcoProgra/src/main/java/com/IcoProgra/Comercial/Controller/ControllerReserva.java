package com.IcoProgra.Comercial.Controller;

import com.IcoProgra.Comercial.Domain.EstudianteEntity;
import com.IcoProgra.Comercial.Domain.ReservaEntity;
import com.IcoProgra.Comercial.Service.ReservaService;
import jakarta.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Vis
 */
@Controller
public class ControllerReserva {

    @Autowired
    ReservaService servicioReserva;

    // GET: carga el formulario
    @GetMapping("/Reserva")
    public String Reserva(Model form) {
        if (!form.containsAttribute("datosReserva")) {
            ReservaEntity reserva = new ReservaEntity();
            reserva.setEstudiante(new EstudianteEntity());
            form.addAttribute("datosReserva", reserva);
        }
        form.addAttribute("cursos", listaCursos());
        return "Reserva";
    }

    // POST: procesa el formulario
    @PostMapping("/Reserva")
    public String Registrar_Reserva(@ModelAttribute("datosReserva") ReservaEntity reserva,
                                    BindingResult resultado,
                                    RedirectAttributes redirectAttributes) {
        // Fecha u hora con formato inválido (no se pudieron convertir a LocalDate / LocalTime)
        if (resultado.hasErrors()) {
            redirectAttributes.addFlashAttribute("mensajeError", "La fecha u hora ingresada no es válida.");
            redirectAttributes.addFlashAttribute("datosReserva", reserva);
            return "redirect:/Reserva";
        }
        try {
            servicioReserva.registrarReserva(reserva);
            redirectAttributes.addFlashAttribute("mensajeExito", "¡Reserva registrada correctamente!");
            return "redirect:/Reserva";
        } catch (EntityExistsException ex) {
            redirectAttributes.addFlashAttribute("errorDuplicado", ex.getMessage());
            redirectAttributes.addFlashAttribute("datosReserva", reserva);
            return "redirect:/Reserva";
        } catch (IllegalArgumentException ex) {
            redirectAttributes.addFlashAttribute("mensajeError", ex.getMessage());
            redirectAttributes.addFlashAttribute("datosReserva", reserva);
            return "redirect:/Reserva";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("mensajeError",
                    "No se pudo registrar la reserva. Intenta nuevamente.");
            redirectAttributes.addFlashAttribute("datosReserva", reserva);
            return "redirect:/Reserva";
        }
    }

    // Opciones del select de curso: 1A, 1B ... 4F
    private List<String> listaCursos() {
        List<String> cursos = new ArrayList<>();
        for (int nivel = 1; nivel <= 4; nivel++) {
            for (char letra = 'A'; letra <= 'F'; letra++) {
                cursos.add(nivel + "" + letra);
            }
        }
        return cursos;
    }
}

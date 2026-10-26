package com.IcoProgra.Comercial.Service;

import com.IcoProgra.Comercial.Domain.EstudianteEntity;
import java.util.Optional;

/**
 * @author Vis
 */
public interface EstudianteService {

    // Busca un estudiante por su RUT (acepta formato con o sin puntos)
    public Optional<EstudianteEntity> buscarPorRut(String rut);
}

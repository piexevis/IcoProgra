package com.IcoProgra.Comercial.Repository;

import com.IcoProgra.Comercial.Domain.EstudianteEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Vis
 */
@Repository
public interface EstudianteRepository extends JpaRepository<EstudianteEntity, Integer> {

    Optional<EstudianteEntity> findByRut(String rut);

    boolean existsByRut(String rut);
}

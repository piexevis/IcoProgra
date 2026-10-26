package com.IcoProgra.Comercial.Repository;

import com.IcoProgra.Comercial.Domain.EstudianteEntity;
import com.IcoProgra.Comercial.Domain.ReservaEntity;
import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vis
 */
@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Integer> {

    boolean existsByEstudianteAndFechaAndHora(EstudianteEntity estudiante, LocalDate fecha, LocalTime hora);
}

package com.danilore.piniateria_lizzety.repository.persona;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.danilore.piniateria_lizzety.model.persona.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {
    @Query("SELECT DISTINCT d FROM Departamento d WHERE " +
            "LOWER(d.descripcion) LIKE LOWER(CONCAT('%', :criterio, '%')) OR " +
            "CAST(d.idDepartamento AS string) LIKE :criterio")
    Page<Departamento> buscarPorCriterio(@Param("criterio") String criterio, Pageable pageable);

    Optional<Departamento> findByDescripcion(String descripcion);

}

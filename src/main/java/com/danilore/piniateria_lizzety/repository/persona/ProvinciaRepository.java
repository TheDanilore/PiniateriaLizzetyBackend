package com.danilore.piniateria_lizzety.repository.persona;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.danilore.piniateria_lizzety.model.persona.Provincia;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Integer> {
    @Query("SELECT DISTINCT p FROM Provincia p WHERE " +
            "LOWER(p.descripcion) LIKE LOWER(CONCAT('%', :criterio, '%')) OR " +
            "CAST(p.idProvincia AS string) LIKE :criterio")
    Page<Provincia> buscarPorCriterio(@Param("criterio") String criterio, Pageable pageable);

    // Busca las provincias por el id del departamento
    Page<Provincia> findByDepartamentoIdDepartamento(Integer id, Pageable pageable);

    Optional<Provincia> findByDescripcion(String descripcion);

    @Query("SELECT p FROM Provincia p JOIN FETCH p.departamento WHERE p.id = :id")
    Optional<Provincia> findByIdConDepartamento(@Param("id") Integer id);
}

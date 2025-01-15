package com.danilore.piniateria_lizzety.repository.persona;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.danilore.piniateria_lizzety.model.persona.Distrito;

@Repository
public interface DistritoRepository extends JpaRepository<Distrito, Integer> {
    @Query("SELECT DISTINCT d FROM Distrito d WHERE " +
            "LOWER(d.descripcion) LIKE LOWER(CONCAT('%', :criterio, '%')) OR " +
            "CAST(d.idDistrito AS string) LIKE :criterio")
    Page<Distrito> buscarPorCriterio(@Param("criterio") String criterio, Pageable pageable);

    Page<Distrito> findByProvinciaIdProvincia(Integer id, Pageable pageable);

    Optional<Distrito> findByDescripcion(String descripcion);

}

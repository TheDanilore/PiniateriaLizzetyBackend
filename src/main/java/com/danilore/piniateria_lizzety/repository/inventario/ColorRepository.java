package com.danilore.piniateria_lizzety.repository.inventario;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.danilore.piniateria_lizzety.model.inventario.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {

    @Query("SELECT DISTINCT c FROM Color c WHERE " +
            "LOWER(c.descripcion) LIKE LOWER(CONCAT('%', :criterio, '%')) OR " +
            "CAST(c.id AS string) LIKE :criterio")
    Page<Color> buscarPorCriterio(@Param("criterio") String criterio, Pageable pageable);

    Optional<Color> findByDescripcion(String descripcion);

}

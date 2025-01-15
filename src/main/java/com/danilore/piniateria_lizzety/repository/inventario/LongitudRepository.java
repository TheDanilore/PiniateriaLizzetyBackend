package com.danilore.piniateria_lizzety.repository.inventario;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.danilore.piniateria_lizzety.model.inventario.Longitud;

@Repository
public interface LongitudRepository extends JpaRepository<Longitud, Long> {

    @Query("SELECT DISTINCT l FROM Longitud l WHERE " +
            "LOWER(l.descripcion) LIKE LOWER(CONCAT('%', :criterio, '%')) OR " +
            "CAST(l.id AS string) LIKE :criterio")
    Page<Longitud> buscarPorCriterio(@Param("criterio") String criterio, Pageable pageable);

    Optional<Longitud> findByDescripcion(String descripcion);
}

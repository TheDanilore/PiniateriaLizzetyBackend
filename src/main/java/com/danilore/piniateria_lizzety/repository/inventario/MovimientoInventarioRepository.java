package com.danilore.piniateria_lizzety.repository.inventario;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.danilore.piniateria_lizzety.model.inventario.Inventario;
import com.danilore.piniateria_lizzety.model.inventario.MovimientoInventario;

@Repository
public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Long> {

    @Query("SELECT DISTINCT m FROM MovimientoInventario m WHERE " +
            "LOWER(m.inventario) LIKE LOWER(CONCAT('%', :criterio, '%')) OR " +
            "CAST(m.id AS string) LIKE :criterio")
    Page<MovimientoInventario> buscarPorCriterio(@Param("criterio") String criterio, Pageable pageable);
    
    Optional<MovimientoInventario> findByInventario(Inventario inventario);
}

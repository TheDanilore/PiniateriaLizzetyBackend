package com.danilore.piniateria_lizzety.repository.inventario;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.danilore.piniateria_lizzety.model.inventario.Inventario;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {

        @Query("SELECT DISTINCT i FROM Inventario i WHERE " +
                        "LOWER(i.producto.nombre) LIKE LOWER(CONCAT('%', :criterio, '%')) OR " +
                        "CAST(i.id AS string) LIKE :criterio")
        Page<Inventario> buscarPorCriterio(@Param("criterio") String criterio, Pageable pageable);

        boolean existsByProductoIdAndVariacionId(Long productoId, Long variacionId);

        @Query("SELECT i FROM Inventario i WHERE i.producto.id = :productoId " +
                        "AND (:colorId IS NULL OR i.variacion.color.id = :colorId) " +
                        "AND (:longitudId IS NULL OR i.variacion.longitud.id = :longitudId) " +
                        "AND (:tamanoId IS NULL OR i.variacion.tamano.id = :tamanoId)")
        Optional<Inventario> findByProductoIdAndVariacion(
                        @Param("productoId") Long productoId,
                        @Param("colorId") Long colorId,
                        @Param("longitudId") Long longitudId,
                        @Param("tamanoId") Long tamanoId);

}

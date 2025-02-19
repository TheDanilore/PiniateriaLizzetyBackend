package com.danilore.piniateria_lizzety.repository.inventario;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.danilore.piniateria_lizzety.model.inventario.EntradaProducto;

@Repository
public interface EntradaProductoRepository extends JpaRepository<EntradaProducto, Long> {

    @Query("SELECT DISTINCT e FROM EntradaProducto e WHERE e.deletedAt IS NULL AND " +
            "LOWER(e.guiaRemision) LIKE LOWER(CONCAT('%', :criterio, '%')) OR " +
            "LOWER(e.tipoEntrada) LIKE LOWER(CONCAT('%', :criterio, '%')) OR " +
            "CAST(e.id AS string) LIKE :criterio")
    Page<EntradaProducto> buscarPorCriterio(@Param("criterio") String criterio, Pageable pageable);

    Optional<EntradaProducto> findByGuiaRemision(String guiaRemision);

    @Query("SELECT e FROM EntradaProducto e WHERE e.deletedAt IS NULL")
    Page<EntradaProducto> findAllActivas(Pageable pageable);

    @Query("SELECT e FROM EntradaProducto e WHERE e.id = :id AND e.deletedAt IS NULL")
    Optional<EntradaProducto> findByIdActiva(@Param("id") Long id);

}

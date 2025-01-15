package com.danilore.piniateria_lizzety.repository.producto;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.danilore.piniateria_lizzety.model.producto.CategoriaProducto;

@Repository
public interface CategoriaProductoRepository extends JpaRepository<CategoriaProducto, Long> {
    
    @Query("SELECT DISTINCT c FROM CategoriaProducto p WHERE " +
            "LOWER(c.descripcion) LIKE LOWER(CONCAT('%', :criterio, '%')) OR " +
            "CAST(c.id AS string) LIKE :criterio")
    Page<CategoriaProducto> buscarPorCriterio(@Param("criterio") String criterio, Pageable pageable);

    
    Optional<CategoriaProducto> findByDescripcion(String descripcion);
}

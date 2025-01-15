package com.danilore.piniateria_lizzety.repository.producto;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.danilore.piniateria_lizzety.model.producto.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    @Query("SELECT DISTINCT p FROM Producto p WHERE " +
            "LOWER(p.nombre) LIKE LOWER(CONCAT('%', :criterio, '%')) OR " +
            "LOWER(p.categoriaProducto.descripcion) LIKE LOWER(CONCAT('%', :criterio, '%')) OR " +
            "CAST(p.id AS string) LIKE :criterio")
    Page<Producto> buscarPorCriterio(@Param("criterio") String criterio, Pageable pageable);

    Optional<Producto> findByNombre(String nombre);

}

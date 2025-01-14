package com.danilore.piniateria_lizzety.repository.producto;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.danilore.piniateria_lizzety.model.producto.CategoriaProducto;

@Repository
public interface CategoriaProductoRepository extends JpaRepository<CategoriaProducto, Long> {
    Optional<CategoriaProductoRepository> findByDescripcion(String descripcion);
}

package com.danilore.piniateria_lizzety.repository.inventario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.danilore.piniateria_lizzety.model.inventario.SalidaProducto;

@Repository
public interface SalidaProductoRepository extends JpaRepository<SalidaProducto, Long> {

}

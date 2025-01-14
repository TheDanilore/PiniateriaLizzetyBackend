package com.danilore.piniateria_lizzety.repository.producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.danilore.piniateria_lizzety.model.producto.Proveedor;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long>{

}

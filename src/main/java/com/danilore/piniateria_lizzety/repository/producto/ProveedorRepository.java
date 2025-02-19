package com.danilore.piniateria_lizzety.repository.producto;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.danilore.piniateria_lizzety.model.producto.Proveedor;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    @Query("SELECT DISTINCT p FROM Proveedor p WHERE " +
            "CAST(p.ruc AS string) LIKE CONCAT('%', :criterio, '%') OR " +
            "LOWER(p.razonSocial) LIKE LOWER(CONCAT('%', :criterio, '%')) OR " +
            "CAST(p.id AS string) LIKE :criterio")
    Page<Proveedor> buscarPorCriterio(@Param("criterio") String criterio, Pageable pageable);

    Optional<Proveedor> findByRuc(BigInteger ruc);
    Optional<Proveedor> findByRazonSocial(String razonSocial);
    Optional<Proveedor> findByTelefono(String telefono);
}

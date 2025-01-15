package com.danilore.piniateria_lizzety.repository.producto;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.danilore.piniateria_lizzety.model.producto.Ubicacion;

@Repository
public interface UbicacionRepository extends JpaRepository<Ubicacion, Long> {
    @Query("SELECT DISTINCT u FROM Ubicacion u WHERE " +
            "LOWER(u.codigo) LIKE LOWER(CONCAT('%', :criterio, '%')) OR " +
            "LOWER(u.descripcion) LIKE LOWER(CONCAT('%', :criterio, '%')) OR " +
            "CAST(u.id AS string) LIKE :criterio")
    Page<Ubicacion> buscarPorCriterio(@Param("criterio") String criterio, Pageable pageable);

    Optional<Ubicacion> findByDescripcion(String descripcion);

    Optional<Ubicacion> findByCodigo(String descripcion);

}

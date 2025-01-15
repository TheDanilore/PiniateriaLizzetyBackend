package com.danilore.piniateria_lizzety.repository.inventario;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.danilore.piniateria_lizzety.model.inventario.SalidaProducto;

@Repository
public interface SalidaProductoRepository extends JpaRepository<SalidaProducto, Long> {

    @Query("SELECT DISTINCT s FROM SalidaProducto s WHERE " +
            "LOWER(s.guiaSalida) LIKE LOWER(CONCAT('%', :criterio, '%')) OR " +
            "LOWER(s.tipoSalida) LIKE LOWER(CONCAT('%', :criterio, '%')) OR " +
            "CAST(s.id AS string) LIKE :criterio")
    Page<SalidaProducto> buscarPorCriterio(@Param("criterio") String criterio, Pageable pageable);

    Optional<SalidaProducto> findByGuiaSalida(String guiaSalida);
}

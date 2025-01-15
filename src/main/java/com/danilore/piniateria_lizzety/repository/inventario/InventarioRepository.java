package com.danilore.piniateria_lizzety.repository.inventario;

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

}

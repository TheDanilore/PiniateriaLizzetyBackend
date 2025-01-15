package com.danilore.piniateria_lizzety.repository.inventario;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.danilore.piniateria_lizzety.model.inventario.Tamano;
@Repository
public interface TamanoRepository extends JpaRepository<Tamano, Long> {

    @Query("SELECT DISTINCT t FROM Tamano t WHERE " +
            "LOWER(t.descripcion) LIKE LOWER(CONCAT('%', :criterio, '%')) OR " +
            "CAST(t.id AS string) LIKE :criterio")
    Page<Tamano> buscarPorCriterio(@Param("criterio") String criterio, Pageable pageable);

    Optional<Tamano> findByDescripcion(String descripcion);
}

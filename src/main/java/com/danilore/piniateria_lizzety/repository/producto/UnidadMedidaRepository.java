package com.danilore.piniateria_lizzety.repository.producto;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.danilore.piniateria_lizzety.model.producto.UnidadMedida;

@Repository
public interface UnidadMedidaRepository extends JpaRepository<UnidadMedida, Long> {
    @Query("SELECT DISTINCT u FROM UnidadMedida u WHERE " +
            "LOWER(u.descripcion) LIKE LOWER(CONCAT('%', :criterio, '%')) OR " +
            "LOWER(u.abreviatura) LIKE LOWER(CONCAT('%', :criterio, '%')) OR " +
            "CAST(u.id AS string) LIKE :criterio")
    Page<UnidadMedida> buscarPorCriterio(@Param("criterio") String criterio, Pageable pageable);

    Optional<UnidadMedida> findByDescripcion(String descripcion);
}

package com.danilore.piniateria_lizzety.repository.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.danilore.piniateria_lizzety.model.usuario.Permiso;

import java.util.List;
import java.util.Optional;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Integer> {
    @Query("SELECT DISTINCT p FROM Permiso p WHERE " +
            "LOWER(p.descripcion) LIKE LOWER(CONCAT('%', :criterio, '%')) OR " +
            "CAST(p.id AS string) LIKE :criterio")
    Page<Permiso> buscarPorCriterio(@Param("criterio") String criterio, Pageable pageable);

    Optional<Permiso> findByDescripcion(String descripcion);

    List<Permiso> findByAccion(String accion);
}

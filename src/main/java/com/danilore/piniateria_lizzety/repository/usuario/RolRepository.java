package com.danilore.piniateria_lizzety.repository.usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.danilore.piniateria_lizzety.model.usuario.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    @Query("SELECT DISTINCT r FROM Rol r LEFT JOIN FETCH r.permisos WHERE " +
            "LOWER(r.descripcion) LIKE LOWER(CONCAT('%', :criterio, '%')) OR " +
            "CAST(r.id AS string) LIKE :criterio")
    Page<Rol> buscarPorCriterio(@Param("criterio") String criterio, Pageable pageable);


    Optional<Rol> findByDescripcion(String descripcion);

    // Buscar roles asociados a un usuario específico
    @Query("SELECT r FROM Rol r JOIN r.usuarios u WHERE u.id = :usuarioId")
    List<Rol> findRolesByUsuarioId(Integer usuarioId);
}


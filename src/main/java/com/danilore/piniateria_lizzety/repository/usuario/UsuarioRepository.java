package com.danilore.piniateria_lizzety.repository.usuario;

import com.danilore.piniateria_lizzety.model.usuario.Usuario;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNombreOrEmailOrId(String nombre, String email, Long id);

    @Query("SELECT DISTINCT u FROM Usuario u LEFT JOIN FETCH u.roles WHERE " +
            "LOWER(u.nombre) LIKE LOWER(CONCAT('%', :criterio, '%')) OR " +
            "LOWER(u.email) LIKE LOWER(CONCAT('%', :criterio, '%')) OR " +
            "CAST(u.id AS string) LIKE :criterio")
    Page<Usuario> buscarPorCriterio(@Param("criterio") String criterio, Pageable pageable);

    Optional<Usuario> findByEmail(String email);

    // Recuperar usuario con roles por email
    @Query("SELECT u FROM Usuario u JOIN FETCH u.roles WHERE u.email = :email")
    Optional<Usuario> findByEmailWithRoles(@Param("email") String email);

}

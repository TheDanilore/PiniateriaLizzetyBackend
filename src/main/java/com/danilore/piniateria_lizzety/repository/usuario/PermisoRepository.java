package com.danilore.piniateria_lizzety.repository.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.danilore.piniateria_lizzety.model.usuario.Permiso;

import java.util.List;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Integer> {
    Permiso findByDescripcion(String descripcion);

    List<Permiso> findByAccion(String accion);
}

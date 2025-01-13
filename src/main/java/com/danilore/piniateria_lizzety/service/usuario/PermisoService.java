package com.danilore.piniateria_lizzety.service.usuario;

import com.danilore.piniateria_lizzety.exception.DAOException;
import com.danilore.piniateria_lizzety.model.usuario.Permiso;
import com.danilore.piniateria_lizzety.repository.usuario.PermisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PermisoService {

    @Autowired
    private PermisoRepository permisoRepository;

    public Page<Permiso> listarTodos(Pageable pageable) {
        return permisoRepository.findAll(pageable);
    }

    public Permiso guardar(Permiso permiso) {
        if (permisoRepository.findByDescripcion(permiso.getDescripcion()) != null) {
            throw new DAOException("El permiso ya existe");
        }
        
        return permisoRepository.save(permiso);
    }

    public Permiso editar(int id, Permiso permisoActualizado) {
        Permiso permisoExistente = permisoRepository.findById(id)
                .orElseThrow(() -> new DAOException("Rol no encontrado"));

        // Actualizar solo los campos permitidos
        permisoExistente.setDescripcion(permisoActualizado.getDescripcion());
        permisoExistente.setAccion(permisoActualizado.getAccion());
        return permisoRepository.save(permisoExistente);
    }

    public Permiso buscarPorId(int id) {
        return permisoRepository.findById(id).orElseThrow(() -> new DAOException("Rol no encontrado"));
    }

    public void eliminarPorId(int id) {
        if(!permisoRepository.existsById(id)) {
            throw new DAOException("Permiso no encontrado");
        }
        permisoRepository.deleteById(id);
    }

    public Permiso buscarPorDescripcion(String descripcion) {
        return permisoRepository.findByDescripcion(descripcion);
    }
}

package com.danilore.piniateria_lizzety.service.usuario;

import com.danilore.piniateria_lizzety.exception.DAOException;
import com.danilore.piniateria_lizzety.model.EstadoEnum;
import com.danilore.piniateria_lizzety.model.usuario.Rol;
import com.danilore.piniateria_lizzety.repository.usuario.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.HashSet;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public Page<Rol> listarTodos(Pageable pageable) {
        return rolRepository.findAll(pageable);
    }

    public Rol guardar(Rol rol) {
        if (rolRepository.findByDescripcion(rol.getDescripcion()) != null) {
            throw new DAOException("El rol ya existe");
        }
        
        // Verifica si los roles son nulos o vacíos y los inicializa
        if (rol.getPermisos() == null || rol.getPermisos().isEmpty()) {
            rol.setPermisos(new HashSet<>());
        }

        rol.setEstado(EstadoEnum.ACTIVO); // Estado por defecto
        return rolRepository.save(rol);
    }

    public Rol editar(int id, Rol rolActualizado) {
        Rol rolExistente = rolRepository.findById(id)
                .orElseThrow(() -> new DAOException("Rol no encontrado"));

        // Actualizar solo los campos permitidos
        rolExistente.setDescripcion(rolActualizado.getDescripcion());

        rolExistente.setPermisos(rolActualizado.getPermisos() != null ? rolActualizado.getPermisos() : new HashSet<>());

        return rolRepository.save(rolExistente);
    }

    public Rol buscarPorId(int id) {
        return rolRepository.findById(id).orElseThrow(() -> new DAOException("Rol no encontrado"));
    }

    public Rol cambiarEstado(int id, EstadoEnum nuevoEstado) {
        Rol rol = rolRepository.findById(id).orElseThrow(() -> new DAOException("Rol no encontrado"));
        rol.setEstado(nuevoEstado);
        return rolRepository.save(rol);
    }

    public void eliminarPorId(int id) {
        if (!rolRepository.existsById(id)) {
            throw new DAOException("Rol no encontrado");
        }
        rolRepository.deleteById(id);
    }

    public Rol buscarPorDescripcion(String descripcion) {
        return rolRepository.findByDescripcion(descripcion);
    }
}

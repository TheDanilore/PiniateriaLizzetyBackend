package com.danilore.piniateria_lizzety.service.usuario;

import com.danilore.piniateria_lizzety.dto.usuario.RolDTO;
import com.danilore.piniateria_lizzety.exception.DAOException;
import com.danilore.piniateria_lizzety.model.EstadoEnum;
import com.danilore.piniateria_lizzety.model.usuario.Rol;
import com.danilore.piniateria_lizzety.repository.usuario.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.HashSet;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public Page<RolDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Rol> rolPage = rolRepository.findAll(pageable);
        return rolPage.map(RolDTO::fromEntity);
    }

    public RolDTO getById(int id) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado con el ID: " + id));
        return RolDTO.fromEntity(rol);
    }

    public RolDTO save(RolDTO rolDTO) {
        Rol rol = rolDTO.toEntity();

        if (rolRepository.findByDescripcion(rol.getDescripcion()).isPresent()) {
            throw new DAOException("El rol ya está registrado. Otro rol ya tiene esa descripcion");
        }

        rol.setEstado(EstadoEnum.ACTIVO); // Estado por defecto
        Rol savedRol = rolRepository.save(rol);
        return RolDTO.fromEntity(savedRol);
    }

    public RolDTO update(int id, RolDTO rolDTO) {
        // Convertir el DTO en una entidad Rol
        Rol rolActualizado = rolDTO.toEntity();

        Rol rolExistente = rolRepository.findById(id)
                .orElseThrow(() -> new DAOException("Rol no encontrado con el ID: " + id));

        rolExistente.setDescripcion(rolActualizado.getDescripcion());

        rolExistente
                .setPermisos(rolActualizado.getPermisos() != null ? rolActualizado.getPermisos() : new HashSet<>());

        // Convertir la entidad actualizada nuevamente a DTO y devolverla
        return RolDTO.fromEntity(rolRepository.save(rolExistente));
    }

    public Rol cambiarEstado(int id, EstadoEnum nuevoEstado) {
        Rol rol = rolRepository.findById(id).orElseThrow(() -> new DAOException("Rol no encontrado el ID: " + id));
        rol.setEstado(nuevoEstado);
        return rolRepository.save(rol);
    }

    public void deleteById(int id) {
        if (!rolRepository.existsById(id)) {
            throw new DAOException("Rol no encontrado con el ID: " + id);
        }
        rolRepository.deleteById(id);
    }

    public RolDTO getByDescripcion(String descripcion) {
        Rol rol = rolRepository.findByDescripcion(descripcion)
                .orElseThrow(() -> new IllegalArgumentException("Rol no existe"));
        return RolDTO.fromEntity(rol);
    }

    // Listar por descripcion o ID
    public Page<RolDTO> buscarPorCriterio(String criterio, int page, int size) {
        if (criterio == null || criterio.isBlank()) {
            throw new IllegalArgumentException("El criterio de búsqueda no puede estar vacío");
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("descripcion").ascending());
        Page<Rol> rolPage = rolRepository.buscarPorCriterio(criterio, pageable);
        return rolPage.map(RolDTO::fromEntity);
    }
}

package com.danilore.piniateria_lizzety.service.usuario;

import com.danilore.piniateria_lizzety.dto.usuario.PermisoDTO;
import com.danilore.piniateria_lizzety.exception.DAOException;
import com.danilore.piniateria_lizzety.model.usuario.Permiso;
import com.danilore.piniateria_lizzety.repository.usuario.PermisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PermisoService {

    @Autowired
    private PermisoRepository permisoRepository;

    public Page<PermisoDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Permiso> rolPage = permisoRepository.findAll(pageable);

        return rolPage.map(PermisoDTO::fromEntity);
    }

    public PermisoDTO getById(int id) {
        Permiso permiso = permisoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rol no existe con el ID: " + id));
        return PermisoDTO.fromEntity(permiso);
    }

    public PermisoDTO save(PermisoDTO permisoDTO) {
        Permiso permiso = permisoDTO.toEntity();

        if (permisoRepository.findByDescripcion(permiso.getDescripcion()).isPresent()) {
            throw new DAOException("La descripción ya está registrado.");
        }

        Permiso savedPermiso = permisoRepository.save(permiso);
        return PermisoDTO.fromEntity(savedPermiso);
    }

    public PermisoDTO update(int id, PermisoDTO permisoDTO) {
        // Convertir el DTO en una entidad Usuario
        Permiso permisoActualizado = permisoDTO.toEntity();

        Permiso permisoExistente = permisoRepository.findById(id)
                .orElseThrow(() -> new DAOException("Rol no encontrado con ID: " + id));

        permisoExistente.setDescripcion(permisoActualizado.getDescripcion());
        permisoExistente.setAccion(permisoActualizado.getAccion());

        // Convertir la entidad actualizada nuevamente a DTO y devolverla
        return PermisoDTO.fromEntity(permisoRepository.save(permisoExistente));
    }

    public void deleteById(int id) {
        if (!permisoRepository.existsById(id)) {
            throw new DAOException("Permiso no encontrado");
        }
        permisoRepository.deleteById(id);
    }

    public PermisoDTO getByDescripcion(String descripcion) {
        Permiso permiso = permisoRepository.findByDescripcion(descripcion)
                .orElseThrow(() -> new IllegalArgumentException("Permiso no existe"));
        return PermisoDTO.fromEntity(permiso);
    }

    // Listar usuario por descripcion,o ID
    public Page<PermisoDTO> buscarPorCriterio(String criterio, int page, int size) {
        if (criterio == null || criterio.isBlank()) {
            throw new IllegalArgumentException("El criterio de búsqueda no puede estar vacío");
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("descripcion").ascending());
        Page<Permiso> permisoPage = permisoRepository.buscarPorCriterio(criterio, pageable);
        return permisoPage.map(PermisoDTO::fromEntity);
    }

}

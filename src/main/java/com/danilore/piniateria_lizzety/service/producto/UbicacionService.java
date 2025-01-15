package com.danilore.piniateria_lizzety.service.producto;

import com.danilore.piniateria_lizzety.dto.producto.UbicacionDTO;
import com.danilore.piniateria_lizzety.exception.DAOException;
import com.danilore.piniateria_lizzety.exception.ResourceNotFoundException;
import com.danilore.piniateria_lizzety.model.producto.Ubicacion;
import com.danilore.piniateria_lizzety.repository.producto.UbicacionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UbicacionService {

    @Autowired
    private UbicacionRepository ubicacionRepository;

    public Page<UbicacionDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Ubicacion> ubicacionPage = ubicacionRepository.findAll(pageable);

        return ubicacionPage.map(UbicacionDTO::fromEntity);
    }

    public UbicacionDTO getById(Long id) {
        Ubicacion ubicacion = ubicacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ubicación no encontrada con ID: " + id));
        return UbicacionDTO.fromEntity(ubicacion);
    }

    public UbicacionDTO save(UbicacionDTO ubicacionDTO) {
        Ubicacion ubicacion = ubicacionDTO.toEntity();

        if (ubicacionRepository.findByDescripcion(ubicacion.getDescripcion()).isPresent()) {
            throw new DAOException("La descripción ya está registrado.");
        }

        if (ubicacionRepository.findByDescripcion(ubicacion.getDescripcion()).isPresent()) {
            throw new DAOException("El código ya está registrado.");
        }

        Ubicacion ubicacionGuardada = ubicacionRepository.save(ubicacion);
        return UbicacionDTO.fromEntity(ubicacionGuardada);
    }

    public UbicacionDTO update(Long id, UbicacionDTO ubicacionDTO) {
        Ubicacion ubicacionActualizado = ubicacionDTO.toEntity();

        Ubicacion ubicacionExistente = ubicacionRepository.findById(id)
                .orElseThrow(() -> new DAOException("Departamento no encontrado con ID: " + id));

        ubicacionExistente.setCodigo(ubicacionActualizado.getCodigo());
        ubicacionExistente.setDescripcion(ubicacionActualizado.getDescripcion());

        // Convertir la entidad actualizada nuevamente a DTO y devolverla
        return UbicacionDTO.fromEntity(ubicacionRepository.save(ubicacionExistente));
    }

    public void deleteById(Long id) {
        if (!ubicacionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Ubicación no encontrada con ID: " + id);
        }
        ubicacionRepository.deleteById(id);
    }

    // Listar por descripcion, codigo o ID
    public Page<UbicacionDTO> buscarPorCriterio(String criterio, int page, int size) {
        if (criterio == null || criterio.isBlank()) {
            throw new IllegalArgumentException("El criterio de búsqueda no puede estar vacío");
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("descripcion").ascending());
        Page<Ubicacion> ubicacionPage = ubicacionRepository.buscarPorCriterio(criterio, pageable);
        return ubicacionPage.map(UbicacionDTO::fromEntity);
    }
}

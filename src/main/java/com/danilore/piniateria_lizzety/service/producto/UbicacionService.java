package com.danilore.piniateria_lizzety.service.producto;

import com.danilore.piniateria_lizzety.dto.producto.UbicacionDTO;
import com.danilore.piniateria_lizzety.exception.ResourceNotFoundException;
import com.danilore.piniateria_lizzety.model.producto.Ubicacion;
import com.danilore.piniateria_lizzety.repository.producto.UbicacionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UbicacionService {

    @Autowired
    private UbicacionRepository ubicacionRepository;

    public Page<UbicacionDTO> listarTodos(Pageable pageable) {
        return ubicacionRepository.findAll(pageable).map(UbicacionDTO::fromEntity);
    }

    public UbicacionDTO buscarPorId(Long id) {
        Ubicacion ubicacion = ubicacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ubicación no encontrada con ID: " + id));
        return UbicacionDTO.fromEntity(ubicacion);
    }

    public UbicacionDTO guardar(UbicacionDTO ubicacionDTO) {
        Ubicacion ubicacion = ubicacionDTO.toEntity();
        Ubicacion ubicacionGuardada = ubicacionRepository.save(ubicacion);
        return UbicacionDTO.fromEntity(ubicacionGuardada);
    }

    public UbicacionDTO editar(Long id, UbicacionDTO ubicacionDTO) {
        Ubicacion ubicacionExistente = ubicacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ubicación no encontrada con ID: " + id));
        
        ubicacionExistente.setCodigo(ubicacionDTO.getCodigo());
        ubicacionExistente.setDescripcion(ubicacionDTO.getDescripcion());
        ubicacionExistente.setUpdatedAt(ubicacionDTO.getUpdatedAt());
        
        Ubicacion ubicacionEditada = ubicacionRepository.save(ubicacionExistente);
        return UbicacionDTO.fromEntity(ubicacionEditada);
    }

    public void eliminar(Long id) {
        if (!ubicacionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Ubicación no encontrada con ID: " + id);
        }
        ubicacionRepository.deleteById(id);
    }
}

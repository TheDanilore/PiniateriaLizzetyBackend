package com.danilore.piniateria_lizzety.service.inventario;

import com.danilore.piniateria_lizzety.dto.inventario.TamanoDTO;
import com.danilore.piniateria_lizzety.exception.ResourceNotFoundException;
import com.danilore.piniateria_lizzety.model.inventario.Tamano;
import com.danilore.piniateria_lizzety.repository.inventario.TamanoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TamanoService {

    @Autowired
    private TamanoRepository tamanoRepository;

    public Page<TamanoDTO> listarTodos(Pageable pageable) {
        return tamanoRepository.findAll(pageable).map(TamanoDTO::fromEntity);
    }

    public TamanoDTO buscarPorId(Long id) {
        Tamano tamano = tamanoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tamaño no encontrado con ID: " + id));
        return TamanoDTO.fromEntity(tamano);
    }

    public TamanoDTO guardar(TamanoDTO tamanoDTO) {
        Tamano tamano = tamanoDTO.toEntity();
        Tamano tamanoGuardado = tamanoRepository.save(tamano);
        return TamanoDTO.fromEntity(tamanoGuardado);
    }

    public TamanoDTO editar(Long id, TamanoDTO tamanoDTO) {
        Tamano tamanoExistente = tamanoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tamaño no encontrado con ID: " + id));

        tamanoExistente.setDescripcion(tamanoDTO.getDescripcion());
        tamanoExistente.setUpdatedAt(tamanoDTO.getUpdatedAt());

        Tamano tamanoEditado = tamanoRepository.save(tamanoExistente);
        return TamanoDTO.fromEntity(tamanoEditado);
    }

    public void eliminar(Long id) {
        if (!tamanoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tamaño no encontrado con ID: " + id);
        }
        tamanoRepository.deleteById(id);
    }
}

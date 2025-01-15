package com.danilore.piniateria_lizzety.service.inventario;

import com.danilore.piniateria_lizzety.dto.inventario.TamanoDTO;
import com.danilore.piniateria_lizzety.exception.DAOException;
import com.danilore.piniateria_lizzety.model.inventario.Tamano;
import com.danilore.piniateria_lizzety.repository.inventario.TamanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TamanoService {

    @Autowired
    private TamanoRepository tamanoRepository;

    public Page<TamanoDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Tamano> tamanoPage = tamanoRepository.findAll(pageable);

        return tamanoPage.map(TamanoDTO::fromEntity);
    }

    public TamanoDTO getById(Long id) {
        Tamano tamano = tamanoRepository.findById(id)
                .orElseThrow(() -> new DAOException("Tamaño no encontrado con ID: " + id));
        return TamanoDTO.fromEntity(tamano);
    }

    public TamanoDTO save(TamanoDTO tamanoDTO) {
        Tamano tamano = tamanoDTO.toEntity();

        if (tamanoRepository.findByDescripcion(tamano.getDescripcion()).isPresent()) {
            throw new DAOException("La descripción ya está registrado.");
        }

        Tamano savedTamano = tamanoRepository.save(tamano);
        return TamanoDTO.fromEntity(savedTamano);
    }

    public TamanoDTO update(Long id, TamanoDTO tamanoDTO) {
        Tamano tamanoActualizado = tamanoDTO.toEntity();

        Tamano tamanoExistente = tamanoRepository.findById(id)
                .orElseThrow(() -> new DAOException("Tamaño no encontrado con ID: " + id));

        tamanoExistente.setDescripcion(tamanoActualizado.getDescripcion());

        return TamanoDTO.fromEntity(tamanoRepository.save(tamanoExistente));
    }

    public void deleteById(Long id) {
        if (!tamanoRepository.existsById(id)) {
            throw new DAOException("Tamaño no encontrado con ID: " + id);
        }
        tamanoRepository.deleteById(id);
    }

    // Listar por descripcion o ID
    public Page<TamanoDTO> buscarPorCriterio(String criterio, int page, int size) {
        if (criterio == null || criterio.isBlank()) {
            throw new DAOException("El criterio de búsqueda no puede estar vacío");
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("descripcion").ascending());
        Page<Tamano> tamanoPage = tamanoRepository.buscarPorCriterio(criterio, pageable);
        return tamanoPage.map(TamanoDTO::fromEntity);
    }
}

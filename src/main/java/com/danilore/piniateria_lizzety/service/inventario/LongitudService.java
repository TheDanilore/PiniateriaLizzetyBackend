package com.danilore.piniateria_lizzety.service.inventario;

import com.danilore.piniateria_lizzety.dto.inventario.LongitudDTO;
import com.danilore.piniateria_lizzety.exception.DAOException;
import com.danilore.piniateria_lizzety.repository.inventario.LongitudRepository;
import com.danilore.piniateria_lizzety.model.inventario.Longitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class LongitudService {

    @Autowired
    private LongitudRepository longitudRepository;

    public Page<LongitudDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Longitud> longitudPage = longitudRepository.findAll(pageable);

        return longitudPage.map(LongitudDTO::fromEntity);
    }

    public LongitudDTO getById(Long id) {
        Longitud longitud = longitudRepository.findById(id)
                .orElseThrow(() -> new DAOException("Longitud no encontrado con ID: " + id));
        return LongitudDTO.fromEntity(longitud);
    }

    public LongitudDTO save(LongitudDTO longitudDTO) {
        Longitud longitud = longitudDTO.toEntity();

        if (longitudRepository.findByDescripcion(longitud.getDescripcion()).isPresent()) {
            throw new DAOException("La descripción ya está registrado.");
        }

        Longitud savedLongitud = longitudRepository.save(longitud);
        return LongitudDTO.fromEntity(savedLongitud);
    }

    public LongitudDTO update(Long id, LongitudDTO longitudDTO) {
        Longitud longitudActualizado = longitudDTO.toEntity();

        Longitud longitudExistente = longitudRepository.findById(id)
                .orElseThrow(() -> new DAOException("Longitud no encontrado con ID: " + id));

        longitudExistente.setDescripcion(longitudActualizado.getDescripcion());

        return LongitudDTO.fromEntity(longitudRepository.save(longitudExistente));
    }

    public void deleteById(Long id) {
        if (!longitudRepository.existsById(id)) {
            throw new DAOException("Longitud no encontrado con ID: " + id);
        }
        longitudRepository.deleteById(id);
    }

    // Listar por descripcion o ID
    public Page<LongitudDTO> buscarPorCriterio(String criterio, int page, int size) {
        if (criterio == null || criterio.isBlank()) {
            throw new DAOException("El criterio de búsqueda no puede estar vacío");
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("descripcion").ascending());
        Page<Longitud> longitudPage = longitudRepository.buscarPorCriterio(criterio, pageable);
        return longitudPage.map(LongitudDTO::fromEntity);
    }
}

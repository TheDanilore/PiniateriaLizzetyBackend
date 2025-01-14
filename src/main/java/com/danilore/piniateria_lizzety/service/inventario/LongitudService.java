package com.danilore.piniateria_lizzety.service.inventario;

import com.danilore.piniateria_lizzety.dto.inventario.LongitudDTO;
import com.danilore.piniateria_lizzety.repository.inventario.LongitudRepository;
import com.danilore.piniateria_lizzety.model.inventario.Longitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class LongitudService {

    @Autowired
    private LongitudRepository longitudRepository;

    public LongitudDTO create(LongitudDTO dto) {
        Longitud entity = dto.toEntity();
        Longitud savedEntity = longitudRepository.save(entity);
        return LongitudDTO.fromEntity(savedEntity);
    }

    public LongitudDTO update(Long id, LongitudDTO dto) {
        Optional<Longitud> existing = longitudRepository.findById(id);
        if (existing.isPresent()) {
            Longitud entity = existing.get();
            // Actualiza las propiedades necesarias de `entity` aquí
            entity = dto.toEntity();
            Longitud updatedEntity = longitudRepository.save(entity);
            return LongitudDTO.fromEntity(updatedEntity);
        }
        return null; // O lanzar una excepción si no se encuentra
    }

    public Page<LongitudDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Longitud> entities = longitudRepository.findAll(pageable);
        return entities.map(LongitudDTO::fromEntity);
    }

    public LongitudDTO findById(Long id) {
        Optional<Longitud> entity = longitudRepository.findById(id);
        return entity.map(LongitudDTO::fromEntity).orElse(null);
    }

    public void delete(Long id) {
        longitudRepository.deleteById(id);
    }
}

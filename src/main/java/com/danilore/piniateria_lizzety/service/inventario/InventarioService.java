package com.danilore.piniateria_lizzety.service.inventario;

import com.danilore.piniateria_lizzety.dto.inventario.InventarioDTO;
import com.danilore.piniateria_lizzety.model.inventario.Inventario;
import com.danilore.piniateria_lizzety.repository.inventario.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventarioService {

    @Autowired
    private final InventarioRepository repository;

    public InventarioService(InventarioRepository repository) {
        this.repository = repository;
    }

    public InventarioDTO create(InventarioDTO dto) {
        Inventario entity = dto.toEntity();
        Inventario savedEntity = repository.save(entity);
        return InventarioDTO.fromEntity(savedEntity);
    }

    public InventarioDTO update(Long id, InventarioDTO dto) {
        Optional<Inventario> existing = repository.findById(id);
        if (existing.isPresent()) {
            Inventario entity = existing.get();
            // Actualizar las propiedades necesarias
            entity = dto.toEntity();
            Inventario updatedEntity = repository.save(entity);
            return InventarioDTO.fromEntity(updatedEntity);
        }
        return null; // O lanzar una excepción
    }

    public Page<InventarioDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Inventario> entities = repository.findAll(pageable);
        return entities.map(InventarioDTO::fromEntity);
    }

    public InventarioDTO findById(Long id) {
        Optional<Inventario> entity = repository.findById(id);
        return entity.map(InventarioDTO::fromEntity).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

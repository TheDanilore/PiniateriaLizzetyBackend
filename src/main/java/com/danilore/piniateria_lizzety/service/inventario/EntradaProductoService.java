package com.danilore.piniateria_lizzety.service.inventario;

import com.danilore.piniateria_lizzety.dto.inventario.EntradaProductoDTO;
import com.danilore.piniateria_lizzety.model.inventario.EntradaProducto;
import com.danilore.piniateria_lizzety.repository.inventario.EntradaProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EntradaProductoService {

    @Autowired
    private final EntradaProductoRepository repository;

    public EntradaProductoService(EntradaProductoRepository repository) {
        this.repository = repository;
    }

    public EntradaProductoDTO create(EntradaProductoDTO dto) {
        EntradaProducto entity = dto.toEntity();
        EntradaProducto savedEntity = repository.save(entity);
        return EntradaProductoDTO.fromEntity(savedEntity);
    }

    public EntradaProductoDTO update(Long id, EntradaProductoDTO dto) {
        Optional<EntradaProducto> existing = repository.findById(id);
        if (existing.isPresent()) {
            EntradaProducto entity = existing.get();
            entity = dto.toEntity();  // Actualizar los campos necesarios
            EntradaProducto updatedEntity = repository.save(entity);
            return EntradaProductoDTO.fromEntity(updatedEntity);
        }
        return null; // O lanzar una excepción
    }

    public Page<EntradaProductoDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EntradaProducto> entities = repository.findAll(pageable);
        return entities.map(EntradaProductoDTO::fromEntity);
    }

    public EntradaProductoDTO findById(Long id) {
        Optional<EntradaProducto> entity = repository.findById(id);
        return entity.map(EntradaProductoDTO::fromEntity).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

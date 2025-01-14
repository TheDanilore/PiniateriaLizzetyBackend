package com.danilore.piniateria_lizzety.service.inventario;

import com.danilore.piniateria_lizzety.dto.inventario.MovimientoInventarioDTO;
import com.danilore.piniateria_lizzety.repository.inventario.MovimientoInventarioRepository;
import com.danilore.piniateria_lizzety.model.inventario.MovimientoInventario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class MovimientoInventarioService {

    @Autowired
    private MovimientoInventarioRepository movimientoInventarioRepository;

    public MovimientoInventarioDTO create(MovimientoInventarioDTO dto) {
        MovimientoInventario entity = dto.toEntity();
        MovimientoInventario savedEntity = movimientoInventarioRepository.save(entity);
        return MovimientoInventarioDTO.fromEntity(savedEntity);
    }

    public MovimientoInventarioDTO update(Long id, MovimientoInventarioDTO dto) {
        Optional<MovimientoInventario> existing = movimientoInventarioRepository.findById(id);
        if (existing.isPresent()) {
            MovimientoInventario entity = existing.get();
            // Actualiza las propiedades necesarias de `entity` aquí
            entity = dto.toEntity();
            MovimientoInventario updatedEntity = movimientoInventarioRepository.save(entity);
            return MovimientoInventarioDTO.fromEntity(updatedEntity);
        }
        return null; // O lanzar una excepción si no se encuentra
    }

    public Page<MovimientoInventarioDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MovimientoInventario> entities = movimientoInventarioRepository.findAll(pageable);
        return entities.map(MovimientoInventarioDTO::fromEntity);
    }

    public MovimientoInventarioDTO findById(Long id) {
        Optional<MovimientoInventario> entity = movimientoInventarioRepository.findById(id);
        return entity.map(MovimientoInventarioDTO::fromEntity).orElse(null);
    }

    public void delete(Long id) {
        movimientoInventarioRepository.deleteById(id);
    }
}

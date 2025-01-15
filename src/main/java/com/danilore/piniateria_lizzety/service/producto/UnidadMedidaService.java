package com.danilore.piniateria_lizzety.service.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.danilore.piniateria_lizzety.dto.producto.UnidadMedidaDTO;
import com.danilore.piniateria_lizzety.exception.DAOException;
import com.danilore.piniateria_lizzety.model.producto.UnidadMedida;
import com.danilore.piniateria_lizzety.repository.producto.UnidadMedidaRepository;

@Service
public class UnidadMedidaService {

    @Autowired
    private UnidadMedidaRepository unidadMedidaRepository;

    public Page<UnidadMedidaDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UnidadMedida> unidadMedidaPage = unidadMedidaRepository.findAll(pageable);
        return unidadMedidaPage.map(UnidadMedidaDTO::fromEntity);
    }

    public UnidadMedidaDTO getById(Long id) {
        UnidadMedida unidadMedida = unidadMedidaRepository.findById(id)
                .orElseThrow(() -> new DAOException("Unidad de medida no encontrada con el ID: " + id));
        return UnidadMedidaDTO.fromEntity(unidadMedida);
    }

    public UnidadMedidaDTO save(UnidadMedidaDTO unidadMedidaDTO) {
        UnidadMedida unidadMedida = unidadMedidaDTO.toEntity();

        if (unidadMedidaRepository.findByDescripcion(unidadMedida.getDescripcion()).isPresent()) {
            throw new DAOException("La descripcion ya está registrado.");
        }

        UnidadMedida savedUnidadMedida = unidadMedidaRepository.save(unidadMedida);
        return UnidadMedidaDTO.fromEntity(savedUnidadMedida);
    }

    public UnidadMedidaDTO update(Long id, UnidadMedidaDTO unidadMedidaDTO) {
        UnidadMedida unidadMedidaActualizado = unidadMedidaDTO.toEntity();
        UnidadMedida unidadMedidaExistente = unidadMedidaRepository.findById(id)
                .orElseThrow(() -> new DAOException("Unidad de medida no encontrado con el ID: " + id));

        unidadMedidaExistente.setDescripcion(unidadMedidaActualizado.getDescripcion());
        unidadMedidaExistente.setAbreviatura(unidadMedidaActualizado.getAbreviatura());

        // Convertir la entidad actualizada nuevamente a DTO y devolverla
        return UnidadMedidaDTO.fromEntity(unidadMedidaRepository.save(unidadMedidaExistente));
    }

    public void deleteById(Long id) {
        if (!unidadMedidaRepository.existsById(id)) {
            throw new DAOException("Unidad de medida no encontrada con el ID: " + id);
        }
        unidadMedidaRepository.deleteById(id);
    }

        // Listar por descripcion o ID
    public Page<UnidadMedidaDTO> buscarPorCriterio(String criterio, int page, int size) {
        if (criterio == null || criterio.isBlank()) {
            throw new IllegalArgumentException("El criterio de búsqueda no puede estar vacío");
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("descripcion").ascending());
        Page<UnidadMedida> unidadPage = unidadMedidaRepository.buscarPorCriterio(criterio, pageable);
        return unidadPage.map(UnidadMedidaDTO::fromEntity);
    }
}

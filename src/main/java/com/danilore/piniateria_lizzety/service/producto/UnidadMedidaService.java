package com.danilore.piniateria_lizzety.service.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.danilore.piniateria_lizzety.dto.producto.UnidadMedidaDTO;
import com.danilore.piniateria_lizzety.model.producto.UnidadMedida;
import com.danilore.piniateria_lizzety.repository.producto.UnidadMedidaRepository;

@Service
public class UnidadMedidaService {

    @Autowired
    private UnidadMedidaRepository unidadMedidaRepository;

    public Page<UnidadMedidaDTO> getAllUnidadMedida(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UnidadMedida> unidadMedidaPage = unidadMedidaRepository.findAll(pageable);

        return unidadMedidaPage.map(UnidadMedidaDTO::fromEntity);
    }

    public UnidadMedidaDTO getUnidadMedidaById(Long id) {
        UnidadMedida unidadMedida = unidadMedidaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Unidad de medida no encontrada con el ID: " + id));
        return UnidadMedidaDTO.fromEntity(unidadMedida);
    }

    public UnidadMedidaDTO saveUnidadMedida(UnidadMedidaDTO unidadMedidaDTO) {
        UnidadMedida unidadMedida = unidadMedidaDTO.toEntity();
        UnidadMedida savedUnidadMedida = unidadMedidaRepository.save(unidadMedida);
        return UnidadMedidaDTO.fromEntity(savedUnidadMedida);
    }

    public UnidadMedidaDTO updateUnidadMedida(Long id, UnidadMedidaDTO unidadMedidaDTO) {
        if (!unidadMedidaRepository.existsById(id)) {
            throw new IllegalArgumentException("Unidad de medida no encontrada con el ID: " + id);
        }
        UnidadMedida unidadMedida = unidadMedidaDTO.toEntity();
        unidadMedida.setId(id);
        UnidadMedida updatedUnidadMedida = unidadMedidaRepository.save(unidadMedida);
        return UnidadMedidaDTO.fromEntity(updatedUnidadMedida);
    }

    public void deleteUnidadMedida(Long id) {
        if (!unidadMedidaRepository.existsById(id)) {
            throw new IllegalArgumentException("Unidad de medida no encontrada con el ID: " + id);
        }
        unidadMedidaRepository.deleteById(id);
    }
}

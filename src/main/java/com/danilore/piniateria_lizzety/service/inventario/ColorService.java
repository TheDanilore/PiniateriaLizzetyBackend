package com.danilore.piniateria_lizzety.service.inventario;

import com.danilore.piniateria_lizzety.dto.inventario.ColorDTO;
import com.danilore.piniateria_lizzety.exception.DAOException;
import com.danilore.piniateria_lizzety.model.inventario.Color;
import com.danilore.piniateria_lizzety.repository.inventario.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ColorService {

    @Autowired
    private ColorRepository colorRepository;

    public Page<ColorDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Color> tamanoPage = colorRepository.findAll(pageable);

        return tamanoPage.map(ColorDTO::fromEntity);
    }

    public ColorDTO getById(Long id) {
        Color tamano = colorRepository.findById(id)
                .orElseThrow(() -> new DAOException("Color no encontrado con ID: " + id));
        return ColorDTO.fromEntity(tamano);
    }

    public ColorDTO save(ColorDTO colorDTO) {
        Color color = colorDTO.toEntity();

        if (colorRepository.findByDescripcion(color.getDescripcion()).isPresent()) {
            throw new DAOException("La descripción ya está registrado.");
        }

        Color savedColor = colorRepository.save(color);
        return ColorDTO.fromEntity(savedColor);
    }

    public ColorDTO update(Long id, ColorDTO colorDTO) {
        Color colorActualizado = colorDTO.toEntity();

        Color colorExistente = colorRepository.findById(id)
                .orElseThrow(() -> new DAOException("Color no encontrado con ID: " + id));

        colorExistente.setDescripcion(colorActualizado.getDescripcion());
        colorExistente.setCodigo(colorActualizado.getCodigo());

        return ColorDTO.fromEntity(colorRepository.save(colorExistente));
    }

    public void deleteById(Long id) {
        if (!colorRepository.existsById(id)) {
            throw new DAOException("Color no encontrado con ID: " + id);
        }
        colorRepository.deleteById(id);
    }

    // Listar por descripcion o ID
    public Page<ColorDTO> buscarPorCriterio(String criterio, int page, int size) {
        if (criterio == null || criterio.isBlank()) {
            throw new DAOException("El criterio de búsqueda no puede estar vacío");
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("descripcion").ascending());
        Page<Color> colorPage = colorRepository.buscarPorCriterio(criterio, pageable);
        return colorPage.map(ColorDTO::fromEntity);
    }

}

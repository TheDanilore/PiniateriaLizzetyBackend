package com.danilore.piniateria_lizzety.service.inventario;

import com.danilore.piniateria_lizzety.dto.inventario.ColorDTO;
import com.danilore.piniateria_lizzety.model.inventario.Color;
import com.danilore.piniateria_lizzety.repository.inventario.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ColorService {

    @Autowired
    private final ColorRepository colorRepository;

    public ColorService(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    public ColorDTO create(ColorDTO colorDTO) {
        Color colorEntity = colorDTO.toEntity();
        Color savedColor = colorRepository.save(colorEntity);
        return ColorDTO.fromEntity(savedColor);
    }

    public ColorDTO update(Long id, ColorDTO colorDTO) {
        Optional<Color> existingColor = colorRepository.findById(id);
        if (existingColor.isPresent()) {
            Color color = existingColor.get();
            color.setDescripcion(colorDTO.getDescripcion());
            color.setCodigo(colorDTO.getCodigo());
            color.setUpdatedAt(colorDTO.getUpdatedAt());
            Color updatedColor = colorRepository.save(color);
            return ColorDTO.fromEntity(updatedColor);
        }
        return null; // O lanzar una excepción, por ejemplo, `ColorNotFoundException`
    }

    public ColorDTO findById(Long id) {
        Optional<Color> color = colorRepository.findById(id);
        return color.map(ColorDTO::fromEntity).orElse(null);
    }

    public void delete(Long id) {
        colorRepository.deleteById(id);
    }


}

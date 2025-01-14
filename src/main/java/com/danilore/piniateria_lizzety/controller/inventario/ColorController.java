package com.danilore.piniateria_lizzety.controller.inventario;

import com.danilore.piniateria_lizzety.dto.inventario.ColorDTO;
import com.danilore.piniateria_lizzety.service.inventario.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/colors")
public class ColorController {

    @Autowired
    private final ColorService colorService;

    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<ColorDTO> getColorById(@PathVariable Long id) {
        ColorDTO colorDTO = colorService.findById(id);
        return colorDTO != null ? ResponseEntity.ok(colorDTO) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ColorDTO> createColor(@RequestBody ColorDTO colorDTO) {
        ColorDTO createdColor = colorService.create(colorDTO);
        return ResponseEntity.ok(createdColor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColorDTO> updateColor(@PathVariable Long id, @RequestBody ColorDTO colorDTO) {
        ColorDTO updatedColor = colorService.update(id, colorDTO);
        return updatedColor != null ? ResponseEntity.ok(updatedColor) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColor(@PathVariable Long id) {
        colorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

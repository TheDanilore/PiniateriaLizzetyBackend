package com.danilore.piniateria_lizzety.controller.inventario;

import com.danilore.piniateria_lizzety.dto.inventario.ColorDTO;
import com.danilore.piniateria_lizzety.service.inventario.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/colors")
public class ColorController {

    @Autowired
    private ColorService colorService;

    @GetMapping
    public ResponseEntity<Page<ColorDTO>> getAll(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(colorService.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColorDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(colorService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ColorDTO> save(@RequestBody ColorDTO colorDTO) {
        return ResponseEntity.ok(colorService.save(colorDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColorDTO> update(@PathVariable Long id, @RequestBody ColorDTO colorDTO) {
        return ResponseEntity.ok(colorService.update(id, colorDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        colorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<ColorDTO>> buscarPorCriterio(
            @RequestParam String criterio,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(colorService.buscarPorCriterio(criterio, page, size));
    }

}

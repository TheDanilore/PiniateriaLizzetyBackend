package com.danilore.piniateria_lizzety.controller.inventario;

import com.danilore.piniateria_lizzety.dto.inventario.LongitudDTO;
import com.danilore.piniateria_lizzety.service.inventario.LongitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/longitudes")
public class LongitudController {

    @Autowired
    private LongitudService longitudService;

    @GetMapping
    public ResponseEntity<Page<LongitudDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(longitudService.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LongitudDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(longitudService.getById(id));
    }

    @PostMapping
    public ResponseEntity<LongitudDTO> save(@RequestBody LongitudDTO longitudDTO) {
        return ResponseEntity.ok(longitudService.save(longitudDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LongitudDTO> update(@PathVariable Long id, @RequestBody LongitudDTO longitudDTO) {
        return ResponseEntity.ok(longitudService.update(id, longitudDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        longitudService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Page<LongitudDTO>> buscarPorCriterio(
            @RequestParam String criterio,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(longitudService.buscarPorCriterio(criterio, page, size));
    }
}

package com.danilore.piniateria_lizzety.controller.inventario;

import com.danilore.piniateria_lizzety.dto.inventario.TamanoDTO;
import com.danilore.piniateria_lizzety.service.inventario.TamanoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tamanos")
public class TamanoController {

    @Autowired
    private TamanoService tamanoService;

    @GetMapping
    public ResponseEntity<Page<TamanoDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(tamanoService.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TamanoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(tamanoService.getById(id));
    }

    @PostMapping
    public ResponseEntity<TamanoDTO> save(@RequestBody TamanoDTO tamanoDTO) {
        return ResponseEntity.ok(tamanoService.save(tamanoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TamanoDTO> update(@PathVariable Long id, @RequestBody TamanoDTO tamanoDTO) {
        return ResponseEntity.ok(tamanoService.update(id, tamanoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        tamanoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<TamanoDTO>> buscarPorCriterio(
            @RequestParam String criterio,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(tamanoService.buscarPorCriterio(criterio, page, size));
    }

}

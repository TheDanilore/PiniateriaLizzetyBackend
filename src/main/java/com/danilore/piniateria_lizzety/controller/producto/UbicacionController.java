package com.danilore.piniateria_lizzety.controller.producto;

import com.danilore.piniateria_lizzety.dto.producto.UbicacionDTO;
import com.danilore.piniateria_lizzety.service.producto.UbicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ubicaciones")
public class UbicacionController {

    @Autowired
    private UbicacionService ubicacionService;

    @GetMapping
    public ResponseEntity<Page<UbicacionDTO>> getAll(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ubicacionService.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UbicacionDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ubicacionService.getById(id));
    }

    @PostMapping
    public ResponseEntity<UbicacionDTO> save(@RequestBody UbicacionDTO ubicacionDTO) {
        return ResponseEntity.ok(ubicacionService.save(ubicacionDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UbicacionDTO> update(@PathVariable Long id, @RequestBody UbicacionDTO ubicacionDTO) {
        return ResponseEntity.ok(ubicacionService.update(id, ubicacionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        ubicacionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Page<UbicacionDTO>> buscarPorCriterio(
            @RequestParam String criterio,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ubicacionService.buscarPorCriterio(criterio, page, size));
    }
}

package com.danilore.piniateria_lizzety.controller.producto;

import com.danilore.piniateria_lizzety.dto.producto.ProveedorDTO;
import com.danilore.piniateria_lizzety.service.producto.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<Page<ProveedorDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(proveedorService.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(proveedorService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ProveedorDTO> save(@RequestBody ProveedorDTO proveedorDTO) {
        return ResponseEntity.ok(proveedorService.save(proveedorDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorDTO> update(@PathVariable Long id, @RequestBody ProveedorDTO proveedorDTO) {
        return ResponseEntity.ok(proveedorService.update(id, proveedorDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        proveedorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<ProveedorDTO>> buscarPorCriterio(
            @RequestParam String criterio,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(proveedorService.buscarPorCriterio(criterio, page, size));
    }
}

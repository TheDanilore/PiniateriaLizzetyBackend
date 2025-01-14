package com.danilore.piniateria_lizzety.controller.producto;

import com.danilore.piniateria_lizzety.dto.producto.ProveedorDTO;
import com.danilore.piniateria_lizzety.service.producto.ProveedorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<Page<ProveedorDTO>> listarTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(proveedorService.listarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(proveedorService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProveedorDTO> guardar(@RequestBody ProveedorDTO proveedorDTO) {
        return ResponseEntity.ok(proveedorService.guardar(proveedorDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorDTO> editar(@PathVariable Long id, @RequestBody ProveedorDTO proveedorDTO) {
        return ResponseEntity.ok(proveedorService.editar(id, proveedorDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        proveedorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}


package com.danilore.piniateria_lizzety.controller.inventario;

import com.danilore.piniateria_lizzety.dto.inventario.SalidaProductoDTO;
import com.danilore.piniateria_lizzety.service.inventario.SalidaProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/salidas-productos")
public class SalidaProductoController {

    @Autowired
    private SalidaProductoService salidaProductoService;

    @GetMapping
    public ResponseEntity<Page<SalidaProductoDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(salidaProductoService.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalidaProductoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(salidaProductoService.getById(id));
    }

    @PostMapping
    public ResponseEntity<SalidaProductoDTO> save(@RequestBody SalidaProductoDTO salidaProductoDTO) {
        return ResponseEntity.ok(salidaProductoService.save(salidaProductoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalidaProductoDTO> update(@PathVariable Long id,
            @RequestBody SalidaProductoDTO salidaProductoDTO) {
        return ResponseEntity.ok(salidaProductoService.update(id, salidaProductoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        salidaProductoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<SalidaProductoDTO>> buscarPorCriterio(
            @RequestParam String criterio,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(salidaProductoService.buscarPorCriterio(criterio, page, size));
    }

}

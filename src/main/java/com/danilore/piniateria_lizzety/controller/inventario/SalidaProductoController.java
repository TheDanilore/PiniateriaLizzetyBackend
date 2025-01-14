package com.danilore.piniateria_lizzety.controller.inventario;

import com.danilore.piniateria_lizzety.dto.inventario.SalidaProductoDTO;
import com.danilore.piniateria_lizzety.service.inventario.SalidaProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/salidas-productos")
public class SalidaProductoController {

    @Autowired
    private SalidaProductoService salidaProductoService;

    @GetMapping
    public ResponseEntity<Page<SalidaProductoDTO>> listarTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(salidaProductoService.listarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalidaProductoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(salidaProductoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<SalidaProductoDTO> guardar(@RequestBody SalidaProductoDTO salidaProductoDTO) {
        return ResponseEntity.ok(salidaProductoService.guardar(salidaProductoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalidaProductoDTO> editar(@PathVariable Long id, @RequestBody SalidaProductoDTO salidaProductoDTO) {
        return ResponseEntity.ok(salidaProductoService.editar(id, salidaProductoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        salidaProductoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

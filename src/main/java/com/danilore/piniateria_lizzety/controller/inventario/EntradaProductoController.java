package com.danilore.piniateria_lizzety.controller.inventario;

import com.danilore.piniateria_lizzety.dto.inventario.EntradaProductoDTO;
import com.danilore.piniateria_lizzety.dto.inventario.EntradaProductoRequest;
import com.danilore.piniateria_lizzety.dto.inventario.ItemEntradaDTO;
import com.danilore.piniateria_lizzety.service.inventario.EntradaProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/entradas-productos")
public class EntradaProductoController {

    @Autowired
    private EntradaProductoService entradaProductoService;

    @GetMapping
    public ResponseEntity<Page<EntradaProductoDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(entradaProductoService.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Page<ItemEntradaDTO>> getById(@PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(entradaProductoService.getById(id, page, size));
    }

    @PostMapping
    public ResponseEntity<EntradaProductoDTO> save(@RequestBody EntradaProductoRequest request) {
        return ResponseEntity
                .ok(entradaProductoService.save(request.getEntradaProductoDTO(), request.getItemsEntradaDTO()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        entradaProductoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<EntradaProductoDTO>> buscarPorCriterio(
            @RequestParam String criterio,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(entradaProductoService.buscarPorCriterio(criterio, page, size));
    }

}

package com.danilore.piniateria_lizzety.controller.inventario;

import com.danilore.piniateria_lizzety.dto.inventario.EntradaProductoDTO;
import com.danilore.piniateria_lizzety.service.inventario.EntradaProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/entradas-producto")
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
    public ResponseEntity<EntradaProductoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(entradaProductoService.getById(id));    
    }

    @PostMapping
    public ResponseEntity<EntradaProductoDTO> save(@RequestBody EntradaProductoDTO entradaProductoDTO) {
        return ResponseEntity.ok(entradaProductoService.save(entradaProductoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntradaProductoDTO> update(@PathVariable Long id, @RequestBody EntradaProductoDTO entradaProductoDTO) {
        return ResponseEntity.ok(entradaProductoService.update(id, entradaProductoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        entradaProductoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Page<EntradaProductoDTO>> buscarPorCriterio(
            @RequestParam String criterio,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(entradaProductoService.buscarPorCriterio(criterio, page, size));
    }


}

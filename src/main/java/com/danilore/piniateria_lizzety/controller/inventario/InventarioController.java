package com.danilore.piniateria_lizzety.controller.inventario;

import com.danilore.piniateria_lizzety.dto.inventario.InventarioDTO;
import com.danilore.piniateria_lizzety.service.inventario.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventarios")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public ResponseEntity<Page<InventarioDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(inventarioService.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(inventarioService.getById(id));
    }

    @PostMapping
    public ResponseEntity<InventarioDTO> save(@RequestBody InventarioDTO inventarioDTO) {
        return ResponseEntity.ok(inventarioService.save(inventarioDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventarioDTO> update(@PathVariable Long id, @RequestBody InventarioDTO inventarioDTO,
            @RequestParam Long usuarioId) {
        return ResponseEntity.ok(inventarioService.update(id, inventarioDTO, usuarioId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        inventarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<InventarioDTO>> buscarPorCriterio(
            @RequestParam String criterio,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(inventarioService.buscarPorCriterio(criterio, page, size));
    }
}

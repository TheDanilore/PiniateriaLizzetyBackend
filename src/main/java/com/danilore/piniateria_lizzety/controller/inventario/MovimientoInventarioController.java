package com.danilore.piniateria_lizzety.controller.inventario;

import com.danilore.piniateria_lizzety.dto.inventario.MovimientoInventarioDTO;
import com.danilore.piniateria_lizzety.service.inventario.MovimientoInventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movimientos-inventarios")
public class MovimientoInventarioController {

    @Autowired
    private MovimientoInventarioService movimientoInventarioService;

    @GetMapping
    public ResponseEntity<Page<MovimientoInventarioDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(movimientoInventarioService.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoInventarioDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(movimientoInventarioService.getById(id));
    }

    @PostMapping
    public ResponseEntity<MovimientoInventarioDTO> save(@RequestBody MovimientoInventarioDTO movimientoDTO) {
        return ResponseEntity.ok(movimientoInventarioService.save(movimientoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimientoInventarioDTO> update(@PathVariable Long id,
            @RequestBody MovimientoInventarioDTO movimientoDTO) {
        return ResponseEntity.ok(movimientoInventarioService.update(id, movimientoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        movimientoInventarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<MovimientoInventarioDTO>> buscarPorCriterio(
            @RequestParam String criterio,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(movimientoInventarioService.buscarPorCriterio(criterio, page, size));
    }
}

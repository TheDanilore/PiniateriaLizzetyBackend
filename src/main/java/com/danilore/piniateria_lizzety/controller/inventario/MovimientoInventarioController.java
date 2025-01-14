package com.danilore.piniateria_lizzety.controller.inventario;

import com.danilore.piniateria_lizzety.dto.inventario.MovimientoInventarioDTO;
import com.danilore.piniateria_lizzety.service.inventario.MovimientoInventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimiento-inventario")
public class MovimientoInventarioController {

    @Autowired
    private MovimientoInventarioService movimientoInventarioService;


    @GetMapping
    public ResponseEntity<List<MovimientoInventarioDTO>> getAll(
            @RequestParam(defaultValue = "0") int page, 
            @RequestParam(defaultValue = "10") int size) {
        var pageResult = movimientoInventarioService.findAll(page, size);
        return ResponseEntity.ok(pageResult.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoInventarioDTO> getById(@PathVariable Long id) {
        MovimientoInventarioDTO dto = movimientoInventarioService.findById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<MovimientoInventarioDTO> create(@RequestBody MovimientoInventarioDTO dto) {
        MovimientoInventarioDTO createdDto = movimientoInventarioService.create(dto);
        return ResponseEntity.ok(createdDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimientoInventarioDTO> update(@PathVariable Long id, @RequestBody MovimientoInventarioDTO dto) {
        MovimientoInventarioDTO updatedDto = movimientoInventarioService.update(id, dto);
        return updatedDto != null ? ResponseEntity.ok(updatedDto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movimientoInventarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

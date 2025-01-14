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
    private final EntradaProductoService entradaProductoService;

    public EntradaProductoController(EntradaProductoService entradaProductoService) {
        this.entradaProductoService = entradaProductoService;
    }

    @GetMapping
    public ResponseEntity<Page<EntradaProductoDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<EntradaProductoDTO> pageResult = entradaProductoService.findAll(page, size);
        return ResponseEntity.ok(pageResult);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntradaProductoDTO> getById(@PathVariable Long id) {
        EntradaProductoDTO dto = entradaProductoService.findById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<EntradaProductoDTO> create(@RequestBody EntradaProductoDTO dto) {
        EntradaProductoDTO createdDto = entradaProductoService.create(dto);
        return ResponseEntity.ok(createdDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntradaProductoDTO> update(@PathVariable Long id, @RequestBody EntradaProductoDTO dto) {
        EntradaProductoDTO updatedDto = entradaProductoService.update(id, dto);
        return updatedDto != null ? ResponseEntity.ok(updatedDto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        entradaProductoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

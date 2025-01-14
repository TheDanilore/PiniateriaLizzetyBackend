package com.danilore.piniateria_lizzety.controller.inventario;

import com.danilore.piniateria_lizzety.dto.inventario.TamanoDTO;
import com.danilore.piniateria_lizzety.service.inventario.TamanoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tamanos")
public class TamanoController {

    @Autowired
    private TamanoService tamanoService;

    @GetMapping
    public ResponseEntity<Page<TamanoDTO>> listarTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(tamanoService.listarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TamanoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tamanoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<TamanoDTO> guardar(@RequestBody TamanoDTO tamanoDTO) {
        return ResponseEntity.ok(tamanoService.guardar(tamanoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TamanoDTO> editar(@PathVariable Long id, @RequestBody TamanoDTO tamanoDTO) {
        return ResponseEntity.ok(tamanoService.editar(id, tamanoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        tamanoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

package com.danilore.piniateria_lizzety.controller.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.danilore.piniateria_lizzety.dto.producto.UnidadMedidaDTO;
import com.danilore.piniateria_lizzety.service.producto.UnidadMedidaService;

@RestController
@RequestMapping("/api/unidad-medida")
public class UnidadMedidaController {

    @Autowired
    private UnidadMedidaService unidadMedidaService;

    @GetMapping
    public ResponseEntity<Page<UnidadMedidaDTO>> getAll(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(unidadMedidaService.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadMedidaDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(unidadMedidaService.getById(id));
    }

    @PostMapping
    public ResponseEntity<UnidadMedidaDTO> save(@RequestBody UnidadMedidaDTO unidadMedidaDTO) {
        return ResponseEntity.ok(unidadMedidaService.save(unidadMedidaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadMedidaDTO> update(@PathVariable Long id,
            @RequestBody UnidadMedidaDTO unidadMedidaDTO) {
        return ResponseEntity.ok(unidadMedidaService.update(id, unidadMedidaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUnidadMedida(@PathVariable Long id) {
        unidadMedidaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<UnidadMedidaDTO>> buscarPorCriterio(
            @RequestParam String criterio,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(unidadMedidaService.buscarPorCriterio(criterio, page, size));
    }
}

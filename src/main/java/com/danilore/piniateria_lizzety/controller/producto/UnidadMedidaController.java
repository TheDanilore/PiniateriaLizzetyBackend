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
    public ResponseEntity<Page<UnidadMedidaDTO>> getAllUnidadMedida(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(unidadMedidaService.getAllUnidadMedida(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadMedidaDTO> getUnidadMedidaById(@PathVariable Long id) {
        return ResponseEntity.ok(unidadMedidaService.getUnidadMedidaById(id));
    }

    @PostMapping
    public ResponseEntity<UnidadMedidaDTO> saveUnidadMedida(@RequestBody UnidadMedidaDTO unidadMedidaDTO) {
        return ResponseEntity.ok(unidadMedidaService.saveUnidadMedida(unidadMedidaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadMedidaDTO> updateUnidadMedida(@PathVariable Long id,
                                                              @RequestBody UnidadMedidaDTO unidadMedidaDTO) {
        return ResponseEntity.ok(unidadMedidaService.updateUnidadMedida(id, unidadMedidaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUnidadMedida(@PathVariable Long id) {
        unidadMedidaService.deleteUnidadMedida(id);
        return ResponseEntity.noContent().build();
    }
}

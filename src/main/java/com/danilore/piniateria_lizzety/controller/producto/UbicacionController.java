package com.danilore.piniateria_lizzety.controller.producto;

import com.danilore.piniateria_lizzety.dto.producto.UbicacionDTO;
import com.danilore.piniateria_lizzety.service.producto.UbicacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ubicaciones")
public class UbicacionController {

    @Autowired
    private UbicacionService ubicacionService;

    @GetMapping
    public ResponseEntity<Page<UbicacionDTO>> listarTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(ubicacionService.listarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UbicacionDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ubicacionService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<UbicacionDTO> guardar(@RequestBody UbicacionDTO ubicacionDTO) {
        return ResponseEntity.ok(ubicacionService.guardar(ubicacionDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UbicacionDTO> editar(@PathVariable Long id, @RequestBody UbicacionDTO ubicacionDTO) {
        return ResponseEntity.ok(ubicacionService.editar(id, ubicacionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ubicacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

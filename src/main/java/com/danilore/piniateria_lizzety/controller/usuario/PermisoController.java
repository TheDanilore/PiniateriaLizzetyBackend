package com.danilore.piniateria_lizzety.controller.usuario;

import com.danilore.piniateria_lizzety.dto.usuario.PermisoDTO;
import com.danilore.piniateria_lizzety.service.usuario.PermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/permisos")
public class PermisoController {

    @Autowired
    private PermisoService permisoService;

    @GetMapping
    public ResponseEntity<Page<PermisoDTO>> getAll(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(permisoService.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermisoDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(permisoService.getById(id));
    }

    @PostMapping
    public ResponseEntity<PermisoDTO> save(@RequestBody PermisoDTO permisoDTO) {
        return ResponseEntity.ok(permisoService.save(permisoDTO));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<PermisoDTO> update(@PathVariable int id,
            @RequestBody PermisoDTO permisoDTO) {
        return ResponseEntity.ok(permisoService.update(id, permisoDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        permisoService.deleteById(id);
    }

    @GetMapping("/buscar-descripcion")
    public ResponseEntity<PermisoDTO> getByDescripcion(@RequestParam String descripcion) {
        return ResponseEntity.ok(permisoService.getByDescripcion(descripcion));
    }

    // Listar usarios por nombres o email o id
    @GetMapping("/buscar")
    public ResponseEntity<Page<PermisoDTO>> buscarPorCriterio(
            @RequestParam String criterio,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(permisoService.buscarPorCriterio(criterio, page, size));
    }
}

package com.danilore.piniateria_lizzety.controller.persona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import com.danilore.piniateria_lizzety.dto.persona.DistritoDTO;
import com.danilore.piniateria_lizzety.service.persona.DistritoService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/distritos")
public class DistritoController {

    @Autowired
    private DistritoService distritoService;

    @GetMapping
    public ResponseEntity<Page<DistritoDTO>> getAll(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(distritoService.getAll(page, size));
    }

    @GetMapping("/provincia/{id}")
    public Page<DistritoDTO> listarDistritosPorProvincia(
            @PathVariable Integer id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size) {
        return distritoService.listarPorProvincia(id, PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DistritoDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(distritoService.getById(id));
    }

    @PostMapping
    public ResponseEntity<DistritoDTO> save(@RequestBody DistritoDTO distritoDTO) {
        return ResponseEntity.ok(distritoService.save(distritoDTO));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<DistritoDTO> update(@PathVariable int id,
            @RequestBody DistritoDTO distritoDTO) {
        return ResponseEntity.ok(distritoService.update(id, distritoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        distritoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/descripcion/{descripcion}")
    public DistritoDTO buscarPorDescripcion(@PathVariable String descripcion) {
        return DistritoDTO.fromEntity(distritoService.buscarPorDescripcion(descripcion));
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<DistritoDTO>> buscarPorCriterio(
            @RequestParam String criterio,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(distritoService.buscarPorCriterio(criterio, page, size));
    }
}

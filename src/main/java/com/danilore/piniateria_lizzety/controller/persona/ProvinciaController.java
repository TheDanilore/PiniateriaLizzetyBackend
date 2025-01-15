package com.danilore.piniateria_lizzety.controller.persona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.danilore.piniateria_lizzety.dto.persona.ProvinciaDTO;
import com.danilore.piniateria_lizzety.model.persona.Provincia;
import com.danilore.piniateria_lizzety.service.persona.ProvinciaService;

@RestController
@RequestMapping("/api/provincias")
public class ProvinciaController {

    @Autowired
    private ProvinciaService provinciaService;

    @GetMapping
    public ResponseEntity<Page<ProvinciaDTO>> getAll(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(provinciaService.getAll(page, size));
    }

    @GetMapping("/departamento/{id}")
    public Page<ProvinciaDTO> listarProvinciasPorDepartamento(
            @PathVariable Integer id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size) {
        return provinciaService.listarPorDepartamento(id, PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProvinciaDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(provinciaService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ProvinciaDTO> save(@RequestBody ProvinciaDTO provinciaDTO) {
        return ResponseEntity.ok(provinciaService.save(provinciaDTO));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<ProvinciaDTO> update(@PathVariable int id,
            @RequestBody ProvinciaDTO provinciaDTO) {
        return ResponseEntity.ok(provinciaService.update(id, provinciaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        provinciaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/descripcion/{descripcion}")
    public ProvinciaDTO buscarPorDescripcion(@PathVariable String descripcion) {
        Provincia provincia = provinciaService.buscarPorDescripcion(descripcion);
        return ProvinciaDTO.fromEntity(provincia);
    }

    public ResponseEntity<Page<ProvinciaDTO>> buscarPorCriterio(
            @RequestParam String criterio,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(provinciaService.buscarPorCriterio(criterio, page, size));
    }
}

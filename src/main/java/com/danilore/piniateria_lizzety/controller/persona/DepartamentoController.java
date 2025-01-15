package com.danilore.piniateria_lizzety.controller.persona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.danilore.piniateria_lizzety.dto.persona.DepartamentoDTO;
import com.danilore.piniateria_lizzety.model.persona.Departamento;
import com.danilore.piniateria_lizzety.service.persona.DepartamentoService;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping
    public ResponseEntity<Page<DepartamentoDTO>> getAll(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(departamentoService.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartamentoDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(departamentoService.getById(id));
    }

    @PostMapping
    public ResponseEntity<DepartamentoDTO> save(@RequestBody DepartamentoDTO departamentoDTO) {
        return ResponseEntity.ok(departamentoService.save(departamentoDTO));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<DepartamentoDTO> update(@PathVariable int id,
            @RequestBody DepartamentoDTO departamentoDTO) {
        return ResponseEntity.ok(departamentoService.update(id, departamentoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        departamentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/descripcion/{descripcion}")
    public DepartamentoDTO buscarPorDescripcion(@PathVariable String descripcion) {
        Departamento departamento = departamentoService.buscarPorDescripcion(descripcion);
        return DepartamentoDTO.fromEntity(departamento);
    }

    public ResponseEntity<Page<DepartamentoDTO>> buscarPorCriterio(
            @RequestParam String criterio,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(departamentoService.buscarPorCriterio(criterio, page, size));
    }

}

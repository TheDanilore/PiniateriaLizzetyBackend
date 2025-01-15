package com.danilore.piniateria_lizzety.controller.usuario;

import com.danilore.piniateria_lizzety.dto.usuario.RolDTO;
import com.danilore.piniateria_lizzety.model.EstadoEnum;
import com.danilore.piniateria_lizzety.model.usuario.Rol;
import com.danilore.piniateria_lizzety.service.usuario.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping
    public ResponseEntity<Page<RolDTO>> getAll(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(rolService.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(rolService.getById(id));
    }

    @PostMapping
    public ResponseEntity<RolDTO> save(@RequestBody RolDTO rolDTO) {
        return ResponseEntity.ok(rolService.save(rolDTO));
    }

    // Editar un usuario existente
    @PutMapping("/editar/{id}")
    public ResponseEntity<RolDTO> update(@PathVariable int id,
            @RequestBody RolDTO rolDTO) {
        return ResponseEntity.ok(rolService.update(id, rolDTO));
    }

    @PatchMapping("/cambiar-estado/{id}")
    public RolDTO cambiarEstado(@PathVariable int id, @RequestParam EstadoEnum nuevoEstado) {
        Rol rol = rolService.cambiarEstado(id, nuevoEstado);
        return RolDTO.fromEntity(rol); // Convertir Rol a RolDTO
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        rolService.deleteById(id);
    }

    @GetMapping("/buscar-descripcion")
    public ResponseEntity<RolDTO> getByDescripcion(@RequestParam String descripcion) {
        return ResponseEntity.ok(rolService.getByDescripcion(descripcion));
    }

    // Listar usarios por nombres o email o id
    @GetMapping("/buscar")
    public ResponseEntity<Page<RolDTO>> buscarPorCriterio(
            @RequestParam String criterio,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(rolService.buscarPorCriterio(criterio, page, size));
    }

}

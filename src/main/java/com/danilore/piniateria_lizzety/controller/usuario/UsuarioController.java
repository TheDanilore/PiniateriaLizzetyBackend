package com.danilore.piniateria_lizzety.controller.usuario;

import com.danilore.piniateria_lizzety.dto.usuario.UsuarioDTO;
import com.danilore.piniateria_lizzety.model.EstadoEnum;
import com.danilore.piniateria_lizzety.model.usuario.Usuario;
import com.danilore.piniateria_lizzety.service.usuario.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; // Importa las clases para la anotación de los métodos
import org.springframework.web.multipart.MultipartFile;

@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    // Listar todos los usuarios
    @GetMapping
    public ResponseEntity<Page<UsuarioDTO>> getAll(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(usuarioService.getAll(page, size));
    }

    // Buscar un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.getById(id));
    }

    // Guardar un nuevo usuario
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UsuarioDTO> save(
            @RequestPart("usuario") String usuarioJson,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        UsuarioDTO savedUsuario = usuarioService.save(usuarioJson, file);
        return ResponseEntity.ok(savedUsuario);
    }

    // Editar un usuario existente
    @PutMapping("/editar/{id}")
    public ResponseEntity<UsuarioDTO> update(
            @PathVariable Long id,
            @RequestPart("usuario") String usuarioJson,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        UsuarioDTO updatedUsuario = usuarioService.update(id, usuarioJson, file);
        return ResponseEntity.ok(updatedUsuario);
    }

    // Cambiar el estado de un usuario
    @PatchMapping("/cambiar-estado/{id}")
    public UsuarioDTO cambiarEstado(@PathVariable Long id, @RequestParam EstadoEnum nuevoEstado) {
        Usuario usuario = usuarioService.cambiarEstado(id, nuevoEstado);
        return UsuarioDTO.fromEntity(usuario); // Convertir Usuario a UsuarioDTO
    }

    // Eliminar un usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar un usuario por email
    @GetMapping("/buscar-email")
    public UsuarioDTO buscarPorEmail(@RequestParam String email) {
        Usuario usuario = usuarioService.buscarPorEmail(email);
        return UsuarioDTO.fromEntity(usuario); // Convertir Usuario a UsuarioDTO
    }

    // Listar usarios por nombres o email o id
    @GetMapping("/buscar")
    public ResponseEntity<Page<UsuarioDTO>> buscarPorCriterio(
            @RequestParam String criterio,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(usuarioService.buscarPorCriterio(criterio, page, size));
    }

}

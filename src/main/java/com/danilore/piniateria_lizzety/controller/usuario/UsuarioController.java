package com.danilore.piniateria_lizzety.controller.usuario;

import com.danilore.piniateria_lizzety.dto.usuario.UsuarioDTO;
import com.danilore.piniateria_lizzety.model.EstadoEnum;
import com.danilore.piniateria_lizzety.model.usuario.Usuario;
import com.danilore.piniateria_lizzety.service.usuario.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired; // Importa la clase Autowired
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; // Importa las clases para la anotación de los métodos
import org.springframework.web.multipart.MultipartFile;

@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private static final String UPLOAD_DIR = "uploads/"; // Carpeta donde se guardarán las imágenes

    @Autowired // Inyección de dependencias
    private UsuarioService usuarioService; // Servicio para la entidad Usuario

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
    public ResponseEntity<?> save(
            @RequestPart("usuario") String usuarioJson,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            // Convertir JSON a UsuarioDTO
            ObjectMapper objectMapper = new ObjectMapper();
            UsuarioDTO usuarioDTO = objectMapper.readValue(usuarioJson, UsuarioDTO.class);
    
            // Guardar imagen si existe
            String avatarUrl = null;
            if (file != null && !file.isEmpty()) {
                avatarUrl = saveImage(file);
            }
    
            // Guardar usuario en la BD
            UsuarioDTO savedUsuario = usuarioService.save(usuarioDTO, avatarUrl);
            return ResponseEntity.ok(savedUsuario);
    
        } catch (Exception e) {
            e.printStackTrace(); // 🔴 Esto mostrará el error exacto en la consola
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage()); // Devolver el mensaje de error en la respuesta
        }
    }
    
    // Método para guardar la imagen en el servidor y devolver la ruta
    private String saveImage(MultipartFile file) throws IOException {
        // Crear carpeta si no existe
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Guardar el archivo con un nombre único
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIR + fileName);
        Files.write(path, file.getBytes());

        // Retornar la ruta relativa de la imagen
        return "/" + UPLOAD_DIR + fileName;
    }

    // Editar un usuario existente
    @PutMapping("/editar/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id,
            @RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.update(id, usuarioDTO));
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

    @PostMapping("/upload-avatar")
    public ResponseEntity<String> uploadAvatar(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El archivo está vacío.");
        }

        try {
            // Crear carpeta si no existe
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Guardar la imagen en el servidor
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.write(path, file.getBytes());

            // Devolver la ruta relativa para guardar en la base de datos
            return ResponseEntity.ok("/" + UPLOAD_DIR + fileName);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir la imagen.");
        }
    }

}

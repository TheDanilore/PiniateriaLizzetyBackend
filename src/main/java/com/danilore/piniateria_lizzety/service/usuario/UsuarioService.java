package com.danilore.piniateria_lizzety.service.usuario;

import com.danilore.piniateria_lizzety.dto.usuario.RolDTO;
import com.danilore.piniateria_lizzety.dto.usuario.UsuarioDTO;
import com.danilore.piniateria_lizzety.exception.DAOException;
import com.danilore.piniateria_lizzety.model.EstadoEnum;
import com.danilore.piniateria_lizzety.model.usuario.Usuario;
import com.danilore.piniateria_lizzety.repository.usuario.UsuarioRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.transaction.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper;
    private static final String UPLOAD_DIR = "uploads/";

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    // Autenticación
    public Usuario autenticar(String email, String password) throws DAOException {
        // Cargar usuario por email y roles
        Usuario usuario = usuarioRepository.findByEmailWithRoles(email)
                .orElseThrow(() -> new DAOException("Usuario no encontrado."));

        if (usuario.getEstado() != EstadoEnum.ACTIVO) {
            throw new DAOException("Usuario no está activo.");
        }

        if (!BCrypt.checkpw(password, usuario.getPassword())) {
            throw new DAOException("Contraseña incorrecta.");
        }

        // Los roles ya deberían estar cargados aquí si la consulta funciona
        // correctamente.
        return usuario;
    }

    // Listar todos los usuarios

    public Page<UsuarioDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Usuario> usuarioPage = usuarioRepository.findAll(pageable);
        return usuarioPage.map(UsuarioDTO::fromEntity);
    }

    public UsuarioDTO getById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        return UsuarioDTO.fromEntity(usuario);
    }

    // Guardar un nuevo usuario
    @Transactional
    public UsuarioDTO save(String usuarioJson, MultipartFile file) {
        try {
            UsuarioDTO usuarioDTO = objectMapper.readValue(usuarioJson, UsuarioDTO.class);
            Usuario usuario = usuarioDTO.toEntity();

            if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
                throw new DAOException("El email ya está registrado.");
            }
            if (usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
                throw new DAOException("La contraseña es obligatoria.");
            }

            usuario.setPassword(BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt()));
            usuario.setEstado(EstadoEnum.ACTIVO);
            usuario.setRoles(usuario.getRoles() == null ? new HashSet<>() : usuario.getRoles());

            if (file != null && !file.isEmpty()) {
                usuario.setAvatar(saveImage(file));
            }

            Usuario savedUsuario = usuarioRepository.save(usuario);
            return UsuarioDTO.fromEntity(savedUsuario);

        } catch (JsonProcessingException e) {
            throw new DAOException("Error al procesar JSON: " + e.getMessage());
        }
    }

    @Transactional
    public UsuarioDTO update(Long id, String usuarioJson, MultipartFile file) {
        try {
            UsuarioDTO usuarioDTO = objectMapper.readValue(usuarioJson, UsuarioDTO.class);
            Usuario usuarioExistente = usuarioRepository.findById(id)
                    .orElseThrow(() -> new DAOException("Usuario no encontrado con ID: " + id));

            if (usuarioRepository.findByEmail(usuarioDTO.getEmail()).isPresent()
                    && !usuarioExistente.getId().equals(id)) {
                throw new DAOException("El email ya está registrado.");
            }

            usuarioExistente.setNombre(usuarioDTO.getNombre());
            usuarioExistente.setEmail(usuarioDTO.getEmail());

            // Si hay una nueva contraseña, la actualizamos
            if (usuarioDTO.getPassword() != null && !usuarioDTO.getPassword().isEmpty()) {
                usuarioExistente.setPassword(BCrypt.hashpw(usuarioDTO.getPassword(), BCrypt.gensalt()));
            }

            usuarioExistente
                    .setRoles(usuarioDTO.getRoles() != null
                            ? usuarioDTO.getRoles().stream().map(RolDTO::toEntity).collect(Collectors.toSet())
                            : new HashSet<>());

            // Si hay una nueva imagen, actualizarla
            if (file != null && !file.isEmpty()) {
                usuarioExistente.setAvatar(saveImage(file));
            }

            Usuario savedUsuario = usuarioRepository.save(usuarioExistente);
            return UsuarioDTO.fromEntity(savedUsuario);

        } catch (JsonProcessingException e) {
            throw new DAOException("Error al procesar JSON: " + e.getMessage());
        }
    }

    private String saveImage(MultipartFile file) {
        try {
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.write(path, file.getBytes());

            return "/uploads/" + fileName;
        } catch (IOException e) {
            throw new DAOException("Error al guardar la imagen: " + e.getMessage());
        }
    }

    // Cambiar el estado de un usuario
    public Usuario cambiarEstado(Long id, EstadoEnum nuevoEstado) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new DAOException("Usuario no encontrado con el ID: " + id));

        usuario.setEstado(nuevoEstado);
        return usuarioRepository.save(usuario);
    }

    // Eliminar usuario por ID
    public void deleteById(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new DAOException("Usuario no encontrado con el ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    // Buscar usuario por email
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new DAOException("Usuario no encontrado"));
    }

    // Listar usuario por nombre, email o ID
    public Page<UsuarioDTO> buscarPorCriterio(String criterio, int page, int size) {
        if (criterio == null || criterio.isBlank()) {
            throw new IllegalArgumentException("El criterio de búsqueda no puede estar vacío");
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("nombre").ascending());
        Page<Usuario> usuarioPage = usuarioRepository.buscarPorCriterio(criterio, pageable);
        return usuarioPage.map(UsuarioDTO::fromEntity);
    }
}

package com.danilore.piniateria_lizzety.service.usuario;

import com.danilore.piniateria_lizzety.dto.usuario.UsuarioDTO;
import com.danilore.piniateria_lizzety.exception.DAOException;
import com.danilore.piniateria_lizzety.model.EstadoEnum;
import com.danilore.piniateria_lizzety.model.usuario.Usuario;
import com.danilore.piniateria_lizzety.repository.usuario.UsuarioRepository;

import jakarta.transaction.Transactional;

import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

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
    public UsuarioDTO save(UsuarioDTO usuarioDTO, String avatarUrl) {
        Usuario usuario = usuarioDTO.toEntity();

        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new DAOException("El email ya está registrado.");
        }
        if (usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
            throw new DAOException("La contraseña es obligatoria.");
        }

        // Verifica si los roles son nulos o vacíos y los inicializa
        if (usuario.getRoles() == null || usuario.getRoles().isEmpty()) {
            usuario.setRoles(new HashSet<>());
        }

        // Encriptar la contraseña
        usuario.setPassword(BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt()));
        usuario.setEstado(EstadoEnum.ACTIVO); // Estado por defecto

        // Guardar la URL de la imagen si existe
        if (avatarUrl != null) {
            usuario.setAvatar(avatarUrl);
        }

        Usuario savedUsuario = usuarioRepository.save(usuario);
        return UsuarioDTO.fromEntity(savedUsuario);
    }

    public UsuarioDTO update(Long id, UsuarioDTO usuarioDTO) {
        // Convertir el DTO en una entidad Usuario
        Usuario usuarioActualizado = usuarioDTO.toEntity();

        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new DAOException("Usuario no encontrado con ID: " + id));

        usuarioExistente.setNombre(usuarioActualizado.getNombre());
        usuarioExistente.setEmail(usuarioActualizado.getEmail());

        if (usuarioActualizado.getPassword() != null && !usuarioActualizado.getPassword().isEmpty()) {
            usuarioExistente.setPassword(BCrypt.hashpw(usuarioActualizado.getPassword(), BCrypt.gensalt()));
        }
        usuarioExistente
                .setRoles(usuarioActualizado.getRoles() != null ? usuarioActualizado.getRoles() : new HashSet<>());

        // Convertir la entidad actualizada nuevamente a DTO y devolverla
        return UsuarioDTO.fromEntity(usuarioRepository.save(usuarioExistente));
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

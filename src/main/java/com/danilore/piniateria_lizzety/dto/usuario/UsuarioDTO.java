package com.danilore.piniateria_lizzety.dto.usuario;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import com.danilore.piniateria_lizzety.model.EstadoEnum;
import com.danilore.piniateria_lizzety.model.usuario.Usuario;

public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String email;
    private String password; // Incluye la contraseña encriptada
    private EstadoEnum estado;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private List<RolDTO> roles; // Cambiar a Set<RolDTO> si prefieres conjuntos

    public UsuarioDTO(Long id, String nombre, String email, String password, EstadoEnum estado,
            LocalDateTime created_at, LocalDateTime updated_at, List<RolDTO> roles) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.estado = estado;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.roles = roles;
    }

    public UsuarioDTO() {
    }

    // Convertir Usuario a UsuarioDTO
    public static UsuarioDTO fromEntity(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        dto.setPassword(usuario.getPassword()); // Incluye la contraseña encriptada
        dto.setEstado(usuario.getEstado());
        dto.setCreated_at(usuario.getCreated_at());
        dto.setUpdated_at(usuario.getUpdated_at());
        dto.setRoles(usuario.getRoles().stream()
                .map(RolDTO::fromEntity)
                .collect(Collectors.toList()));
        return dto;
    }

    // Convertir UsuarioDTO a Usuario
    public Usuario toEntity() {
        Usuario usuario = new Usuario();
        usuario.setId(this.id);
        usuario.setNombre(this.nombre);
        usuario.setPassword(this.password);
        usuario.setEmail(this.email);
        usuario.setCreated_at(this.created_at);
        usuario.setUpdated_at(this.updated_at);

        usuario.setRoles(this.roles != null
                ? this.roles.stream()
                        .map(RolDTO::toEntity) // Ahora válido porque implementaste `toEntity` en RolDTO
                        .collect(Collectors.toSet()) // Cambiar a toSet si es necesario
                : new HashSet<>());

        return usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EstadoEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoEnum estado) {
        this.estado = estado;
    }

    public List<RolDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RolDTO> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

}
package com.danilore.piniateria_lizzety.dto.usuario;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import com.danilore.piniateria_lizzety.model.EstadoEnum;
import com.danilore.piniateria_lizzety.model.usuario.Usuario;

public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String email;
    private String password; // Incluye la contraseña encriptada
    private String avatar;
    private EstadoEnum estado;
    private Set<RolDTO> roles = new HashSet<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UsuarioDTO(Long id, String nombre, String email, String password, String avatar, EstadoEnum estado,
            Set<RolDTO> roles, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.estado = estado;
        this.roles = roles;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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
        dto.setAvatar(usuario.getAvatar());
        dto.setEstado(usuario.getEstado());
        dto.setCreatedAt(usuario.getCreatedAt());
        dto.setUpdatedAt(usuario.getUpdatedAt());
        dto.setRoles(usuario.getRoles().stream()
                .map(RolDTO::fromEntity)
                .collect(Collectors.toSet()));
        return dto;
    }

    // Convertir UsuarioDTO a Usuario
    public Usuario toEntity() {
        Usuario usuario = new Usuario();
        usuario.setId(this.id);
        usuario.setNombre(this.nombre);
        usuario.setPassword(this.password);
        usuario.setEmail(this.email);
        usuario.setAvatar(this.avatar);
        usuario.setEstado(this.estado);
        usuario.setCreatedAt(this.createdAt);
        usuario.setUpdatedAt(this.updatedAt);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public EstadoEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoEnum estado) {
        this.estado = estado;
    }

    public Set<RolDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RolDTO> roles) {
        this.roles = roles;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

   

    

}
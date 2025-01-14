package com.danilore.piniateria_lizzety.dto.usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import com.danilore.piniateria_lizzety.model.EstadoEnum;
import com.danilore.piniateria_lizzety.model.usuario.Rol;

public class RolDTO {
    private int id;
    private String descripcion;
    private EstadoEnum estado;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<PermisoDTO> permisos; // Verifica que esta lista esté poblada



    public RolDTO(int id, String descripcion, EstadoEnum estado, LocalDateTime createdAt, LocalDateTime updatedAt,
            List<PermisoDTO> permisos) {
        this.id = id;
        this.descripcion = descripcion;
        this.estado = estado;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.permisos = permisos;
    }

    public RolDTO() {
    }

    // Convertir Rol a RolDTO
    public static RolDTO fromEntity(Rol rol) {
        RolDTO dto = new RolDTO();
        dto.setId(rol.getId());
        dto.setDescripcion(rol.getDescripcion());
        dto.setEstado(rol.getEstado());
        dto.setEstado(rol.getEstado());
        dto.setCreatedAt(rol.getCreatedAt());
        dto.setUpdatedAt(rol.getUpdatedAt());
        dto.setPermisos(rol.getPermisos().stream()
                .map(PermisoDTO::fromEntity)
                .collect(Collectors.toList()));
        return dto;
    }

    public Rol toEntity() {
        Rol rol = new Rol();
        rol.setId(this.id);
        rol.setDescripcion(this.descripcion);
        rol.setEstado(this.estado);
        rol.setCreatedAt(this.createdAt);
        rol.setUpdatedAt(this.updatedAt);
        rol.setPermisos(this.permisos != null
                ? this.permisos.stream()
                        .map(PermisoDTO::toEntity)
                        .collect(Collectors.toSet())
                : new HashSet<>());
        return rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoEnum estado) {
        this.estado = estado;
    }

    public List<PermisoDTO> getPermisos() {
        return permisos != null ? permisos : new ArrayList<>();
    }

    public void setPermisos(List<PermisoDTO> permisos) {
        this.permisos = permisos;
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

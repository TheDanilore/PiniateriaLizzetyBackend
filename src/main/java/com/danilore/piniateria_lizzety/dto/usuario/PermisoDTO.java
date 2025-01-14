package com.danilore.piniateria_lizzety.dto.usuario;

import java.time.LocalDateTime;
import java.util.Objects;
import com.danilore.piniateria_lizzety.model.usuario.Permiso;

public class PermisoDTO {
    private int id;
    private String descripcion;
    private String accion;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PermisoDTO(int id, String descripcion, String accion, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.descripcion = descripcion;
        this.accion = accion;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public PermisoDTO(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public PermisoDTO() {
    }

    public static PermisoDTO fromEntity(Permiso permiso) {
        PermisoDTO dto = new PermisoDTO();
        dto.setId(permiso.getId());
        dto.setDescripcion(permiso.getDescripcion());
        dto.setAccion(permiso.getAccion());
        dto.setCreatedAt(permiso.getCreatedAt());
        dto.setUpdatedAt(permiso.getUpdatedAt());
        return dto;
    }

    public Permiso toEntity() {
        Permiso permiso = new Permiso();
        permiso.setId(this.id);
        permiso.setDescripcion(this.descripcion);
        permiso.setAccion(this.accion);
        permiso.setCreatedAt(this.createdAt);
        permiso.setUpdatedAt(this.updatedAt);
        return permiso;
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

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PermisoDTO that = (PermisoDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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

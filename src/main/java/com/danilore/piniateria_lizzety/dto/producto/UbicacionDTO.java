package com.danilore.piniateria_lizzety.dto.producto;

import java.time.LocalDateTime;

import com.danilore.piniateria_lizzety.model.producto.Ubicacion;

import jakarta.persistence.*;

@Entity
@Table(name = "ubicacion")
public class UbicacionDTO {

    private Long id;
    private String codigo;
    private String descripcion;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UbicacionDTO(Long id, String codigo, String descripcion, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UbicacionDTO() {
    }

    public static UbicacionDTO fromEntity(Ubicacion ubicacion) {
        UbicacionDTO dto = new UbicacionDTO();
        dto.setId(ubicacion.getId());
        dto.setCodigo(ubicacion.getCodigo());
        dto.setDescripcion(ubicacion.getDescripcion());
        dto.setCreatedAt(ubicacion.getCreatedAt());
        dto.setUpdatedAt(ubicacion.getUpdatedAt());
        return dto;
    }

    public Ubicacion toEntity() {
        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setId(this.id);
        ubicacion.setCodigo(this.codigo);
        ubicacion.setDescripcion(this.descripcion);
        ubicacion.setCreatedAt(this.createdAt);
        ubicacion.setUpdatedAt(this.updatedAt);
        return ubicacion;
    }
    
    //getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}

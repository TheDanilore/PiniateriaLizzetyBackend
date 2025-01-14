package com.danilore.piniateria_lizzety.dto.inventario;

import java.time.LocalDateTime;

import com.danilore.piniateria_lizzety.model.inventario.Color;

public class ColorDTO {

    private Long id;
    private String descripcion;
    private String codigo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ColorDTO() {
    }

    public ColorDTO(Long id, String descripcion, String codigo, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static ColorDTO fromEntity(Color color) {
        ColorDTO dto = new ColorDTO();
        dto.setId(color.getId());
        dto.setDescripcion(color.getDescripcion());
        dto.setCodigo(color.getCodigo());
        dto.setCreatedAt(color.getCreatedAt());
        dto.setUpdatedAt(color.getUpdatedAt());
        return dto;
    }

    public Color toEntity() {
        Color color = new Color();
        color.setId(this.id);
        color.setDescripcion(this.descripcion);
        color.setCodigo(this.codigo);
        color.setCreatedAt(this.createdAt);
        color.setUpdatedAt(this.updatedAt);
        return color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

package com.danilore.piniateria_lizzety.dto.inventario;

import java.time.LocalDateTime;

import com.danilore.piniateria_lizzety.model.inventario.Longitud;

public class LongitudDTO {

    private Long id;
    private String descripcion;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public LongitudDTO(Long id, String descripcion, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.descripcion = descripcion;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public LongitudDTO() {
    }

    public static LongitudDTO fromEntity(Longitud longitud) {
        LongitudDTO dto = new LongitudDTO();
        dto.setId(longitud.getId());
        dto.setDescripcion(longitud.getDescripcion());
        dto.setCreatedAt(longitud.getCreatedAt());
        dto.setUpdatedAt(longitud.getUpdatedAt());
        return dto;
    }

    public Longitud toEntity() {
        Longitud longitud = new Longitud();
        longitud.setId(this.id);
        longitud.setDescripcion(this.descripcion);
        longitud.setCreatedAt(this.createdAt);
        longitud.setUpdatedAt(this.updatedAt);
        return longitud;
    }

    // getters and setters

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

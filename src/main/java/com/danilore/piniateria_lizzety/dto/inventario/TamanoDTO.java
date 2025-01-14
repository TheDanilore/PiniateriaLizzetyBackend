package com.danilore.piniateria_lizzety.dto.inventario;

import java.time.LocalDateTime;
import com.danilore.piniateria_lizzety.model.inventario.Tamano;

public class TamanoDTO {

    private Long id;
    private String descripcion;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TamanoDTO(Long id, String descripcion, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.descripcion = descripcion;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public TamanoDTO() {
    }

    public static TamanoDTO fromEntity(Tamano tamano) {
        TamanoDTO dto = new TamanoDTO();
        dto.setId(tamano.getId());
        dto.setDescripcion(tamano.getDescripcion());
        dto.setCreatedAt(tamano.getCreatedAt());
        dto.setUpdatedAt(tamano.getUpdatedAt());
        return dto;
    }

    public Tamano toEntity() {
        Tamano tamano = new Tamano();
        tamano.setId(this.id);
        tamano.setDescripcion(this.descripcion);
        tamano.setCreatedAt(this.createdAt);
        tamano.setUpdatedAt(this.updatedAt);
        return tamano;
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

package com.danilore.piniateria_lizzety.dto.persona;

import java.time.LocalDateTime;

import com.danilore.piniateria_lizzety.model.persona.TipoDocumentoIdentidad;

public class TipoDocumentoIdentidadDTO {
    private String id;
    private String abreviatura;
    private String descripcion;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TipoDocumentoIdentidadDTO(String id, String abreviatura, String descripcion, LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.abreviatura = abreviatura;
        this.descripcion = descripcion;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public TipoDocumentoIdentidadDTO() {
    }

    public static TipoDocumentoIdentidadDTO fromEntity(TipoDocumentoIdentidad tipoDocumentoIdentidad) {
        TipoDocumentoIdentidadDTO dto = new TipoDocumentoIdentidadDTO();
        dto.setId(tipoDocumentoIdentidad.getId());
        dto.setAbreviatura(tipoDocumentoIdentidad.getAbreviatura());
        dto.setDescripcion(tipoDocumentoIdentidad.getDescripcion());
        dto.setCreatedAt(tipoDocumentoIdentidad.getCreatedAt());
        dto.setUpdatedAt(tipoDocumentoIdentidad.getUpdatedAt());
        return dto;
    }

    public TipoDocumentoIdentidad toEntity() {
        TipoDocumentoIdentidad tipoDocumentoIdentidad = new TipoDocumentoIdentidad();
        tipoDocumentoIdentidad.setId(this.id);
        tipoDocumentoIdentidad.setAbreviatura(this.abreviatura);
        tipoDocumentoIdentidad.setDescripcion(this.descripcion);
        tipoDocumentoIdentidad.setCreatedAt(this.createdAt);
        tipoDocumentoIdentidad.setUpdatedAt(this.updatedAt);
        return tipoDocumentoIdentidad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
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

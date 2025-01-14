package com.danilore.piniateria_lizzety.dto.producto;

import java.time.LocalDateTime;

import com.danilore.piniateria_lizzety.model.producto.UnidadMedida;

public class UnidadMedidaDTO {

    private Long id;
    private String descripcion;
    private String abreviatura;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UnidadMedidaDTO(Long id, String descripcion, String abreviatura, LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.descripcion = descripcion;
        this.abreviatura = abreviatura;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UnidadMedidaDTO() {
    }

    public static UnidadMedidaDTO fromEntity(UnidadMedida unidadMedida) {
        UnidadMedidaDTO dto = new UnidadMedidaDTO();
        dto.setId(unidadMedida.getId());
        dto.setDescripcion(unidadMedida.getDescripcion());
        dto.setAbreviatura(unidadMedida.getAbreviatura());
        dto.setCreatedAt(unidadMedida.getCreatedAt());
        dto.setUpdatedAt(unidadMedida.getUpdatedAt());
        return dto;
    }

    public UnidadMedida toEntity() {
        UnidadMedida unidadMedida = new UnidadMedida();
        unidadMedida.setId(this.id);
        unidadMedida.setDescripcion(this.descripcion);
        unidadMedida.setAbreviatura(this.abreviatura);
        unidadMedida.setCreatedAt(this.createdAt);
        unidadMedida.setUpdatedAt(this.updatedAt);
        return unidadMedida;
    }
    
    //getters and setters

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

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
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

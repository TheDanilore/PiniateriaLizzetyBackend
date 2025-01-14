package com.danilore.piniateria_lizzety.dto.producto;

import java.time.LocalDateTime;
import com.danilore.piniateria_lizzety.model.producto.CategoriaProducto;

public class CategoriaProductoDTO {

    private Long id;
    private String descripcion;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CategoriaProductoDTO(Long id, String descripcion, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.descripcion = descripcion;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public CategoriaProductoDTO() {
    }

    public static CategoriaProductoDTO fromEntity(CategoriaProducto categoriaProducto) {
        CategoriaProductoDTO dto = new CategoriaProductoDTO();
        dto.setId(categoriaProducto.getId());
        dto.setDescripcion(categoriaProducto.getDescripcion());
        dto.setCreatedAt(categoriaProducto.getCreatedAt());
        dto.setUpdatedAt(categoriaProducto.getUpdatedAt());
        return dto;
    }

    public CategoriaProducto toEntity() {
        CategoriaProducto categoriaProducto = new CategoriaProducto();
        categoriaProducto.setId(this.id);
        categoriaProducto.setDescripcion(this.descripcion);
        categoriaProducto.setCreatedAt(this.createdAt);
        categoriaProducto.setUpdatedAt(this.updatedAt);
        return categoriaProducto;
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

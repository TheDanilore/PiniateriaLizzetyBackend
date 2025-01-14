package com.danilore.piniateria_lizzety.dto.inventario;

import java.time.LocalDateTime;

import com.danilore.piniateria_lizzety.dto.producto.ProductoDTO;
import com.danilore.piniateria_lizzety.model.inventario.Imagen;

public class ImagenDTO {

    private Long id;
    private String url;
    private String altText;
    private ProductoDTO producto;
    private InventarioDTO inventario;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ImagenDTO() {
    }

    public static ImagenDTO fromEntity(Imagen imagen) {
        ImagenDTO dto = new ImagenDTO();
        dto.setId(imagen.getId());
        dto.setUrl(imagen.getUrl());
        dto.setAltText(imagen.getAltText());

        if (imagen.getProducto() != null) {
            dto.setProducto(ProductoDTO.fromEntity(imagen.getProducto()));
        }

        if (imagen.getInventario() != null) {
            dto.setInventario(InventarioDTO.fromEntity(imagen.getInventario()));
        }

        dto.setCreatedAt(imagen.getCreatedAt());
        dto.setUpdatedAt(imagen.getUpdatedAt());
        return dto;
    }

    public Imagen toEntity() {
        Imagen imagen = new Imagen();
        imagen.setId(this.id);
        imagen.setUrl(this.url);
        imagen.setAltText(this.altText);

        if (this.producto != null) {
            imagen.setProducto(this.producto.toEntity());
        }

        if (this.inventario != null) {
            imagen.setInventario(this.inventario.toEntity());
        }

        imagen.setCreatedAt(this.createdAt);
        imagen.setUpdatedAt(this.updatedAt);
        return imagen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    public InventarioDTO getInventario() {
        return inventario;
    }

    public void setInventario(InventarioDTO inventario) {
        this.inventario = inventario;
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

    // getters and setters


}

package com.danilore.piniateria_lizzety.dto.inventario;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.danilore.piniateria_lizzety.dto.producto.ProductoDTO;
import com.danilore.piniateria_lizzety.model.inventario.Inventario;

public class InventarioDTO {

    private Long id;
    private ProductoDTO producto;
    private VariacionDTO variacion;
    private BigDecimal precioUnitario;
    private Long cantidad;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public InventarioDTO(Long id, ProductoDTO producto, VariacionDTO variacion, BigDecimal precioUnitario,
            Long cantidad, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.producto = producto;
        this.variacion = variacion;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public InventarioDTO() {
    }

    public static InventarioDTO fromEntity(Inventario inventario) {
        InventarioDTO dto = new InventarioDTO();
        dto.setId(inventario.getId());

        if (inventario.getProducto() != null) {
            dto.setProducto(ProductoDTO.fromEntity(inventario.getProducto()));
        }

        if (inventario.getVariacion() != null) {
            dto.setVariacion(VariacionDTO.fromEntity(inventario.getVariacion()));
        }

        dto.setPrecioUnitario(inventario.getPrecioUnitario());
        dto.setCantidad(inventario.getCantidad());
        dto.setCreatedAt(inventario.getCreatedAt());
        dto.setUpdatedAt(inventario.getUpdatedAt());
        return dto;
    }

    public Inventario toEntity() {
        Inventario inventario = new Inventario();
        inventario.setId(this.id);
        if (this.producto != null) {
            inventario.setProducto(this.producto.toEntity());
        }

        if (this.variacion != null) {
            inventario.setVariacion(this.variacion.toEntity());
        }

        inventario.setPrecioUnitario(this.precioUnitario);
        inventario.setCantidad(this.cantidad);
        inventario.setCreatedAt(this.createdAt);
        inventario.setUpdatedAt(this.updatedAt);
        inventario.setUpdatedAt(this.updatedAt);
        // No añadir variaciones directamente aquí para evitar bucles
        return inventario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
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

    public VariacionDTO getVariacion() {
        return variacion;
    }

    public void setVariacion(VariacionDTO variacion) {
        this.variacion = variacion;
    }


}

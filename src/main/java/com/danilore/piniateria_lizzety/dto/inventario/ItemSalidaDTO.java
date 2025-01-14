package com.danilore.piniateria_lizzety.dto.inventario;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.danilore.piniateria_lizzety.dto.producto.ProductoDTO;
import com.danilore.piniateria_lizzety.model.inventario.ItemSalida;

public class ItemSalidaDTO {

    private Long id;
    private SalidaProductoDTO salidaProducto;
    private ProductoDTO producto;
    private InventarioDTO inventario;
    private Long cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal igv;
    private BigDecimal costoTotal;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ItemSalidaDTO(Long id, SalidaProductoDTO salidaProducto, ProductoDTO producto, InventarioDTO inventario,
            Long cantidad, BigDecimal precioUnitario, BigDecimal igv, BigDecimal costoTotal, LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.salidaProducto = salidaProducto;
        this.producto = producto;
        this.inventario = inventario;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.igv = igv;
        this.costoTotal = costoTotal;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ItemSalidaDTO() {
    }

    public static ItemSalidaDTO fromEntity(ItemSalida itemSalida) {
        ItemSalidaDTO dto = new ItemSalidaDTO();
        dto.setId(itemSalida.getId());

        if (itemSalida.getSalidaProducto() != null) {
            dto.setSalidaProducto(SalidaProductoDTO.fromEntity(itemSalida.getSalidaProducto()));
        }

        if (itemSalida.getProducto() != null) {
            dto.setProducto(ProductoDTO.fromEntity(itemSalida.getProducto()));
        }

        if (itemSalida.getInventario() != null) {
            dto.setInventario(InventarioDTO.fromEntity(itemSalida.getInventario()));
        }

        dto.setCantidad(itemSalida.getCantidad());
        dto.setPrecioUnitario(itemSalida.getPrecioUnitario());
        dto.setIgv(itemSalida.getIgv());
        dto.setCostoTotal(itemSalida.getCostoTotal());
        dto.setCreatedAt(itemSalida.getCreatedAt());
        dto.setUpdatedAt(itemSalida.getUpdatedAt());
        return dto;
    }

    public ItemSalida toEntity() {
        ItemSalida itemSalida = new ItemSalida();
        itemSalida.setId(this.id);

        if (this.salidaProducto != null) {
            itemSalida.setSalidaProducto(this.salidaProducto.toEntity());
        }

        if (this.producto != null) {
            itemSalida.setProducto(this.producto.toEntity());
        }

        if (this.inventario != null) {
            itemSalida.setInventario(this.inventario.toEntity());
        }

        itemSalida.setCantidad(this.cantidad);
        itemSalida.setPrecioUnitario(this.precioUnitario);
        itemSalida.setIgv(this.igv);
        itemSalida.setCostoTotal(this.costoTotal);
        itemSalida.setCreatedAt(this.createdAt);
        itemSalida.setUpdatedAt(this.updatedAt);
        return itemSalida;
    }

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SalidaProductoDTO getSalidaProducto() {
        return salidaProducto;
    }

    public void setSalidaProducto(SalidaProductoDTO salidaProducto) {
        this.salidaProducto = salidaProducto;
    }

    public InventarioDTO getInventario() {
        return inventario;
    }

    public void setInventario(InventarioDTO inventario) {
        this.inventario = inventario;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getIgv() {
        return igv;
    }

    public void setIgv(BigDecimal igv) {
        this.igv = igv;
    }

    public BigDecimal getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(BigDecimal costoTotal) {
        this.costoTotal = costoTotal;
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

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

}

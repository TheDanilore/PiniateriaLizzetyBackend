package com.danilore.piniateria_lizzety.dto.inventario;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.danilore.piniateria_lizzety.dto.producto.ProductoDTO;
import com.danilore.piniateria_lizzety.model.inventario.ItemEntrada;

public class ItemEntradaDTO {

    private Long id;
    private EntradaProductoDTO entradaProducto;
    private ProductoDTO producto;
    private InventarioDTO inventario;
    private Long cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal igv;
    private BigDecimal costoTotal;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ItemEntradaDTO() {
    }

    public static ItemEntradaDTO fromEntity(ItemEntrada itemEntrada) {
        ItemEntradaDTO dto = new ItemEntradaDTO();
        dto.setId(itemEntrada.getId());

        if (itemEntrada.getEntradaProducto() != null) {
            dto.setEntradaProducto(EntradaProductoDTO.fromEntity(itemEntrada.getEntradaProducto()));
        }

        if (itemEntrada.getProducto() != null) {
            dto.setProducto(ProductoDTO.fromEntity(itemEntrada.getProducto()));
        }

        if (itemEntrada.getInventario() != null) {
            dto.setInventario(InventarioDTO.fromEntity(itemEntrada.getInventario()));
        }

        dto.setCantidad(itemEntrada.getCantidad());
        dto.setPrecioUnitario(itemEntrada.getPrecioUnitario());
        dto.setIgv(itemEntrada.getIgv());
        dto.setCostoTotal(itemEntrada.getCostoTotal());
        dto.setCreatedAt(itemEntrada.getCreatedAt());
        dto.setUpdatedAt(itemEntrada.getUpdatedAt());
        return dto;
    }

    public ItemEntrada toEntity() {
        ItemEntrada itemEntrada = new ItemEntrada();
        itemEntrada.setId(this.id);

        if (this.entradaProducto != null) {
            itemEntrada.setEntradaProducto(this.entradaProducto.toEntity());
        }

        if (this.producto != null) {
            itemEntrada.setProducto(this.producto.toEntity());
        }

        if (this.inventario != null) {
            itemEntrada.setInventario(this.inventario.toEntity());
        }
        
        itemEntrada.setCantidad(this.cantidad);
        itemEntrada.setPrecioUnitario(this.precioUnitario);
        itemEntrada.setIgv(this.igv);
        itemEntrada.setCostoTotal(this.costoTotal);
        itemEntrada.setCreatedAt(this.createdAt);
        itemEntrada.setUpdatedAt(this.updatedAt);
        return itemEntrada;
    }

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EntradaProductoDTO getEntradaProducto() {
        return entradaProducto;
    }

    public void setEntradaProducto(EntradaProductoDTO entradaProducto) {
        this.entradaProducto = entradaProducto;
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

package com.danilore.piniateria_lizzety.dto.inventario;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import com.danilore.piniateria_lizzety.dto.producto.ProductoDTO;
import com.danilore.piniateria_lizzety.model.inventario.Inventario;

public class InventarioDTO {

    private Long id;
    private ProductoDTO producto;
    private BigDecimal precioUnitario;
    private Long cantidad;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<VariacionesDTO> variaciones;

    public InventarioDTO(Long id, ProductoDTO producto, BigDecimal precioUnitario, Long cantidad,
            LocalDateTime createdAt, LocalDateTime updatedAt, Set<VariacionesDTO> variaciones) {
        this.id = id;
        this.producto = producto;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.variaciones = variaciones;
    }

    public InventarioDTO() {
    }

    public static InventarioDTO fromEntity(Inventario inventario) {
        InventarioDTO dto = new InventarioDTO();
        dto.setId(inventario.getId());

        if (inventario.getProducto() != null) {
            dto.setProducto(ProductoDTO.fromEntity(inventario.getProducto()));
        }

        dto.setPrecioUnitario(inventario.getPrecioUnitario());
        dto.setCantidad(inventario.getCantidad());
        dto.setCreatedAt(inventario.getCreatedAt());
        dto.setUpdatedAt(inventario.getUpdatedAt());
        dto.setVariaciones(inventario.getVariaciones().stream()
                .map(VariacionesDTO::fromEntity)
                .collect(Collectors.toSet()));
        return dto;
    }

    public Inventario toEntity() {
        Inventario inventario = new Inventario();
        inventario.setId(this.id);
        inventario.setPrecioUnitario(this.precioUnitario);
        inventario.setCantidad(this.cantidad);
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

    public Set<VariacionesDTO> getVariaciones() {
        return variaciones;
    }

    public void setVariaciones(Set<VariacionesDTO> variaciones) {
        this.variaciones = variaciones;
    }

}

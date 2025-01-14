package com.danilore.piniateria_lizzety.model.inventario;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.danilore.piniateria_lizzety.model.producto.Producto;

import jakarta.persistence.*;

@Entity
@Table(name = "inventario")
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // para tener el id del producto
    @ManyToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "id", nullable = false)
    private Producto producto;

    // Inventario se relaciona con longitud, muchos tamano pueden estar en un
    // inventario
    @ManyToOne
    @JoinColumn(name = "color_id", referencedColumnName = "id", nullable = true)
    private Color color;

    // Inventario se relaciona con longitud, muchos tamano pueden estar en un
    // inventario
    @ManyToOne
    @JoinColumn(name = "longitud_id", referencedColumnName = "id", nullable = true)
    private Longitud longitud;

    // Inventario se relaciona con tamano, muchos tamano pueden estar en un
    // inventario
    @ManyToOne
    @JoinColumn(name = "tamano_id", referencedColumnName = "id", nullable = true)
    private Tamano tamano;

    @Column(name = "precio_unitario", nullable = false)
    private BigDecimal precioUnitario;

    @Column(nullable = false)
    private Long cantidad;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Inventario(Long id, Producto producto, Color color, Longitud longitud, Tamano tamano, BigDecimal precioUnitario,
            Long cantidad) {
        this.id = id;
        this.producto = producto;
        this.color = color;
        this.longitud = longitud;
        this.tamano = tamano;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
    }

    public Inventario() {
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Longitud getLongitud() {
        return longitud;
    }

    public void setLongitud(Longitud longitud) {
        this.longitud = longitud;
    }

    public Tamano getTamano() {
        return tamano;
    }

    public void setTamano(Tamano tamano) {
        this.tamano = tamano;
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

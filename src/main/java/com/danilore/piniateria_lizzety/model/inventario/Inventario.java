package com.danilore.piniateria_lizzety.model.inventario;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "inventario")
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "producto", referencedColumnName = "id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "color", referencedColumnName = "id")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "longitud", referencedColumnName = "id")
    private Longitud longitud;

    @ManyToOne
    @JoinColumn(name = "tamano", referencedColumnName = "id")
    private Tamano tamano;

    private double precioUnitario;

    private Long cantidad;

    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public Inventario(Long id, Producto producto, Color color, Longitud longitud, Tamano tamano, double precioUnitario,
            Long cantidad, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.producto = producto;
        this.color = color;
        this.longitud = longitud;
        this.tamano = tamano;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Inventario() {
    }

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

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

}

package com.danilore.piniateria_lizzety.model.inventario;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "item_entrada")
public class ItemEntrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entrada_producto", referencedColumnName = "id")
    private EntradaProducto entradaProducto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventario", referencedColumnName = "id")
    private Inventario inventario;
    
    private Long cantidad;
    private double precioUnitario;
    private double igv;
    private double costoTotal;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public ItemEntrada(Long id, EntradaProducto entradaProducto, Inventario inventario, Long cantidad,
            double precioUnitario, double igv, double costoTotal) {
        this.id = id;
        this.entradaProducto = entradaProducto;
        this.inventario = inventario;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.igv = igv;
        this.costoTotal = costoTotal;
    }

    public ItemEntrada() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EntradaProducto getEntradaProducto() {
        return entradaProducto;
    }

    public void setEntradaProducto(EntradaProducto entradaProducto) {
        this.entradaProducto = entradaProducto;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

}

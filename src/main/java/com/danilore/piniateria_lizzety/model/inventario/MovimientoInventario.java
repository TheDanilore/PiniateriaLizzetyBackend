package com.danilore.piniateria_lizzety.model.inventario;

import java.time.LocalDateTime;

import com.danilore.piniateria_lizzety.model.usuario.Usuario;

import jakarta.persistence.*;

@Entity
@Table(name = "movimiento_inventario")
public class MovimientoInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinColumn(name = "inventario", referencedColumnName = "id")
    private Inventario inventario;

    @Enumerated(EnumType.STRING)
    private TipoMovimientoEnum tipoMovimiento;

    private Long cantidad;

    private Long cantidadAnterior;

    private Long cantidadActual;

    // Un usuario va a estar asociado a un movimiento en especifico
    @OneToOne
    @JoinColumn(name = "usuario", referencedColumnName = "id")
    private Usuario usuario;

    private String observacion;

    private String fecha;

    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public MovimientoInventario(Long id, Inventario inventario, TipoMovimientoEnum tipoMovimiento, Long cantidad,
            Long cantidadAnterior, Long cantidadActual, Usuario usuario, String observacion, String fecha,
            LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.inventario = inventario;
        this.tipoMovimiento = tipoMovimiento;
        this.cantidad = cantidad;
        this.cantidadAnterior = cantidadAnterior;
        this.cantidadActual = cantidadActual;
        this.usuario = usuario;
        this.observacion = observacion;
        this.fecha = fecha;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public MovimientoInventario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public TipoMovimientoEnum getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimientoEnum tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public Long getCantidadAnterior() {
        return cantidadAnterior;
    }

    public void setCantidadAnterior(Long cantidadAnterior) {
        this.cantidadAnterior = cantidadAnterior;
    }

    public Long getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(Long cantidadActual) {
        this.cantidadActual = cantidadActual;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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

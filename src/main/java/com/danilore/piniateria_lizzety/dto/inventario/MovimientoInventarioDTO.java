package com.danilore.piniateria_lizzety.dto.inventario;

import java.time.LocalDateTime;
import com.danilore.piniateria_lizzety.dto.usuario.UsuarioDTO;
import com.danilore.piniateria_lizzety.model.inventario.MovimientoInventario;
import com.danilore.piniateria_lizzety.model.inventario.enums.TipoMovimientoEnum;

public class MovimientoInventarioDTO {

    private Long id;
    private InventarioDTO inventario;
    private TipoMovimientoEnum tipoMovimiento;
    private Long cantidad;
    private Long cantidadAnterior;
    private Long cantidadActual;
    private UsuarioDTO usuario;
    private String observacion;
    private String fecha;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public MovimientoInventarioDTO(Long id, InventarioDTO inventario, TipoMovimientoEnum tipoMovimiento, Long cantidad,
            Long cantidadAnterior, Long cantidadActual, UsuarioDTO usuario, String observacion, String fecha,
            LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.inventario = inventario;
        this.tipoMovimiento = tipoMovimiento;
        this.cantidad = cantidad;
        this.cantidadAnterior = cantidadAnterior;
        this.cantidadActual = cantidadActual;
        this.usuario = usuario;
        this.observacion = observacion;
        this.fecha = fecha;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public MovimientoInventarioDTO() {
    }

    public static MovimientoInventarioDTO fromEntity(MovimientoInventario movimientoInventario) {
        MovimientoInventarioDTO dto = new MovimientoInventarioDTO();
        dto.setId(movimientoInventario.getId());

        if (movimientoInventario.getInventario() != null) {
            dto.setInventario(InventarioDTO.fromEntity(movimientoInventario.getInventario()));
        }

        dto.setTipoMovimiento(movimientoInventario.getTipoMovimiento());
        dto.setCantidad(movimientoInventario.getCantidad());
        dto.setCantidadAnterior(movimientoInventario.getCantidadAnterior());
        dto.setCantidadActual(movimientoInventario.getCantidadActual());

        if (movimientoInventario.getUsuario() != null) {
            dto.setUsuario(UsuarioDTO.fromEntity(movimientoInventario.getUsuario()));
        }

        dto.setObservacion(movimientoInventario.getObservacion());
        dto.setFecha(movimientoInventario.getFecha());
        dto.setCreatedAt(movimientoInventario.getCreatedAt());
        dto.setUpdatedAt(movimientoInventario.getUpdatedAt());
        return dto;
    }

    public MovimientoInventario toEntity() {
        MovimientoInventario movimientoInventario = new MovimientoInventario();
        movimientoInventario.setId(this.id);

        if (this.inventario != null) {
            movimientoInventario.setInventario(this.inventario.toEntity());
        }

        movimientoInventario.setCantidad(this.cantidad);
        movimientoInventario.setCantidadAnterior(this.cantidadAnterior);
        movimientoInventario.setCantidadActual(this.cantidadActual);

        if (this.usuario != null) {
            movimientoInventario.setUsuario(this.usuario.toEntity());
        }

        movimientoInventario.setObservacion(this.observacion);
        movimientoInventario.setFecha(this.fecha);
        movimientoInventario.setCreatedAt(this.createdAt);
        movimientoInventario.setUpdatedAt(this.updatedAt);
        return movimientoInventario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InventarioDTO getInventario() {
        return inventario;
    }

    public void setInventario(InventarioDTO inventario) {
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

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    // getters and setters

    
}

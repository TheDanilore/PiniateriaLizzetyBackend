package com.danilore.piniateria_lizzety.model.inventario;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.danilore.piniateria_lizzety.model.inventario.enums.TipoSalidaEnum;

import jakarta.persistence.*;

@Entity
@Table(name = "salida_producto")
public class SalidaProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String guiaSalida;

    @Enumerated(EnumType.STRING)
    private TipoSalidaEnum tipoSalidaEnum;

    private String destino;
    private LocalDate fecha;
    private String observacion;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public SalidaProducto(Long id, String guiaSalida, TipoSalidaEnum tipoSalidaEnum, String destino, LocalDate fecha,
            String observacion) {
        this.id = id;
        this.guiaSalida = guiaSalida;
        this.tipoSalidaEnum = tipoSalidaEnum;
        this.destino = destino;
        this.fecha = fecha;
        this.observacion = observacion;
    }

    public SalidaProducto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGuiaSalida() {
        return guiaSalida;
    }

    public void setGuiaSalida(String guiaSalida) {
        this.guiaSalida = guiaSalida;
    }

    public TipoSalidaEnum getTipoSalidaEnum() {
        return tipoSalidaEnum;
    }

    public void setTipoSalidaEnum(TipoSalidaEnum tipoSalidaEnum) {
        this.tipoSalidaEnum = tipoSalidaEnum;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

}

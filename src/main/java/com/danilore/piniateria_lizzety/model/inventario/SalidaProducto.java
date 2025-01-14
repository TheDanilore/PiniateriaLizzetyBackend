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
    
    @Column(name = "guia_salida", nullable = false, unique = true)
    private String guiaSalida;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_salida", nullable = false)
    private TipoSalidaEnum tipoSalida;

    @Column(nullable = false)
    private String destino;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false, length = 500)
    private String observacion;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public SalidaProducto(Long id, String guiaSalida, TipoSalidaEnum tipoSalida, String destino, LocalDate fecha,
            String observacion) {
        this.id = id;
        this.guiaSalida = guiaSalida;
        this.tipoSalida = tipoSalida;
        this.destino = destino;
        this.fecha = fecha;
        this.observacion = observacion;
    }

    public SalidaProducto() {
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
    
    //getters and setters

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

    public TipoSalidaEnum getTipoSalida() {
        return tipoSalida;
    }

    public void setTipoSalida(TipoSalidaEnum tipoSalida) {
        this.tipoSalida = tipoSalida;
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

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}

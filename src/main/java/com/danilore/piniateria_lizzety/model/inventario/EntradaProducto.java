package com.danilore.piniateria_lizzety.model.inventario;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "entrada_producto")
public class EntradaProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String guia_remision;

    @Enumerated(EnumType.STRING)
    private TipoEntradaEnum tipoEntrada;

    private String procedencia;
    private LocalDate fecha;
    private String observaciones;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public EntradaProducto(Long id, String guia_remision, TipoEntradaEnum tipoEntrada, String procedencia,
            LocalDate fecha, String observaciones, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.guia_remision = guia_remision;
        this.tipoEntrada = tipoEntrada;
        this.procedencia = procedencia;
        this.fecha = fecha;
        this.observaciones = observaciones;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public EntradaProducto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGuia_remision() {
        return guia_remision;
    }

    public void setGuia_remision(String guia_remision) {
        this.guia_remision = guia_remision;
    }

    public TipoEntradaEnum getTipoEntrada() {
        return tipoEntrada;
    }

    public void setTipoEntrada(TipoEntradaEnum tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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

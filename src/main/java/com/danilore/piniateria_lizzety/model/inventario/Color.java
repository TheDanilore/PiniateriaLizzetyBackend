package com.danilore.piniateria_lizzety.model.inventario;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "color")
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String descripcion;
    private String codigo;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public Color(Long id, String descripcion, String codigo, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Color() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

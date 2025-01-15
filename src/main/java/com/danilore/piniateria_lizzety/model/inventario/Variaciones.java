package com.danilore.piniateria_lizzety.model.inventario;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "variaciones")
public class Variaciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "inventario_id", referencedColumnName = "id",  nullable = false)
    @JsonIgnore // Evitar referencia cíclica
    private Inventario inventario;

    @ManyToOne
    @JoinColumn(name = "color_id", referencedColumnName = "id", nullable = false)
    private Color color;

    @ManyToOne
    @JoinColumn(name = "longitud_id", referencedColumnName = "id")
    private Longitud longitud;

    @ManyToOne
    @JoinColumn(name = "tamano_id", referencedColumnName = "id")
    private Tamano tamano;


    public Variaciones(Inventario inventario, Color color, Longitud longitud, Tamano tamano) {
        this.inventario = inventario;
        this.color = color;
        this.longitud = longitud;
        this.tamano = tamano;
    }

    public Variaciones(Long id, Inventario inventario, Color color, Longitud longitud, Tamano tamano) {
        this.id = id;
        this.inventario = inventario;
        this.color = color;
        this.longitud = longitud;
        this.tamano = tamano;
    }

    public Variaciones() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }


}

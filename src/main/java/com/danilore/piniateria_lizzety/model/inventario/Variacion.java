package com.danilore.piniateria_lizzety.model.inventario;

import java.util.Set;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "variaciones")
public class Variacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "color_id", referencedColumnName = "id", nullable = false)
    private Color color;

    @ManyToOne
    @JoinColumn(name = "longitud_id", referencedColumnName = "id")
    private Longitud longitud;

    @ManyToOne
    @JoinColumn(name = "tamano_id", referencedColumnName = "id")
    private Tamano tamano;

    // Relación con Variaciones (uno a muchos)
    @OneToMany(mappedBy = "variacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Inventario> inventario;

    public Variacion(Long id, Color color, Longitud longitud, Tamano tamano, Set<Inventario> inventario) {
        this.id = id;
        this.color = color;
        this.longitud = longitud;
        this.tamano = tamano;
        this.inventario = inventario;
    }

    public Variacion() {
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

    public Set<Inventario> getInventario() {
        return inventario;
    }

    public void setInventario(Set<Inventario> inventario) {
        this.inventario = inventario;
    }

    

}

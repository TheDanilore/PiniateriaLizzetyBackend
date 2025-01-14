package com.danilore.piniateria_lizzety.model.persona;

import jakarta.persistence.*;

@Entity
@Table(name = "distrito")
public class Distrito {
    @Id
    @Column(name = "id_distrito")
    private int idDistrito;

    @Column(nullable = false)
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_provincia", referencedColumnName = "id_provincia", nullable = false)
    private Provincia provincia; // FK

    @Column(nullable = false, unique = true)
    private String ubigeo;

    public Distrito(int idDistrito, String descripcion, Provincia provincia, String ubigeo) {
        this.idDistrito = idDistrito;
        this.descripcion = descripcion;
        this.provincia = provincia;
        this.ubigeo = ubigeo;
    }

    public Distrito() {
    }

    public int getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(int idDistrito) {
        this.idDistrito = idDistrito;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }

    
}

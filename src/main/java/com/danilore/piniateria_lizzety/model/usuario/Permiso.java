package com.danilore.piniateria_lizzety.model.usuario;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "permiso")
public class Permiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String descripcion;
    private String accion;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    @ManyToMany(mappedBy = "permisos")
    @JsonIgnore // Ignora la serialización de esta relación
    private Set<Rol> roles = new HashSet<>();

    public Permiso(int id, String descripcion, String accion, LocalDateTime created_at, LocalDateTime updated_at,
            Set<Rol> roles) {
        this.id = id;
        this.descripcion = descripcion;
        this.accion = accion;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.roles = roles;
    }

    public Permiso() {
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
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

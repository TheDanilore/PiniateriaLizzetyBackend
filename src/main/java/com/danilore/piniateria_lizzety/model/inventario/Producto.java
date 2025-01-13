package com.danilore.piniateria_lizzety.model.inventario;

import java.time.LocalDateTime;

import com.danilore.piniateria_lizzety.model.EstadoEnum;

import jakarta.persistence.*;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "categoria_producto", referencedColumnName = "id")
    private CategoriaProducto categoriaProducto;

    @ManyToOne
    @JoinColumn(name = "unidad_medida", referencedColumnName = "id")
    private UnidadMedida unidadMedida;

    @ManyToOne
    @JoinColumn(name = "proveedor", referencedColumnName = "id")
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "ubicacion", referencedColumnName = "id")
    private Ubicacion ubicacion;

    @Enumerated(EnumType.STRING)
    private EstadoEnum estado;

    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public Producto() {
    }

    public Producto(Long id, String nombre, String descripcion, CategoriaProducto categoriaProducto,
            UnidadMedida unidadMedida, Proveedor proveedor, Ubicacion ubicacion, EstadoEnum estado,
            LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoriaProducto = categoriaProducto;
        this.unidadMedida = unidadMedida;
        this.proveedor = proveedor;
        this.ubicacion = ubicacion;
        this.estado = estado;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CategoriaProducto getCategoriaProducto() {
        return categoriaProducto;
    }

    public void setCategoriaProducto(CategoriaProducto categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public EstadoEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoEnum estado) {
        this.estado = estado;
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

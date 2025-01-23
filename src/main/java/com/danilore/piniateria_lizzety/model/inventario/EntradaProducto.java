package com.danilore.piniateria_lizzety.model.inventario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.danilore.piniateria_lizzety.model.inventario.enums.TipoEntradaEnum;
import com.danilore.piniateria_lizzety.model.producto.Proveedor;
import com.danilore.piniateria_lizzety.model.usuario.Usuario;
import jakarta.persistence.*;

@Entity
@Table(name = "entrada_producto")
public class EntradaProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "proveedor_id", referencedColumnName = "id", nullable = true)
    private Proveedor proveedor;

    @Column(name = "guia_remision", nullable = false, unique = true)
    private String guiaRemision;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_entrada", nullable = false)
    private TipoEntradaEnum tipoEntrada;

    @Column(nullable = false)
    private String procedencia;

    @Column(nullable = false)
    private LocalDate fecha;

    // Un usuario va a estar asociado a una entrada en especifico
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private String observacion;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public EntradaProducto(Long id, Proveedor proveedor, String guiaRemision, TipoEntradaEnum tipoEntrada,
            String procedencia, LocalDate fecha, Usuario usuario, String observacion) {
        this.id = id;
        this.proveedor = proveedor;
        this.guiaRemision = guiaRemision;
        this.tipoEntrada = tipoEntrada;
        this.procedencia = procedencia;
        this.fecha = fecha;
        this.usuario = usuario;
        this.observacion = observacion;
    }

    public EntradaProducto() {
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public void markAsDeleted() {
        this.deletedAt = LocalDateTime.now();
    }
    
    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGuiaRemision() {
        return guiaRemision;
    }

    public void setGuiaRemision(String guia_remision) {
        this.guiaRemision = guia_remision;
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

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }



}

package com.danilore.piniateria_lizzety.dto.inventario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.danilore.piniateria_lizzety.dto.producto.ProveedorDTO;
import com.danilore.piniateria_lizzety.dto.usuario.UsuarioDTO;
import com.danilore.piniateria_lizzety.model.inventario.EntradaProducto;
import com.danilore.piniateria_lizzety.model.inventario.enums.TipoEntradaEnum;

public class EntradaProductoDTO {

    private Long id;
    private ProveedorDTO proveedor;
    private String guiaRemision;
    private TipoEntradaEnum tipoEntrada;
    private String procedencia;
    private LocalDate fecha;
    private UsuarioDTO usuario;
    private String observacion;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public EntradaProductoDTO(Long id, ProveedorDTO proveedor, String guiaRemision, TipoEntradaEnum tipoEntrada,
            String procedencia, LocalDate fecha, UsuarioDTO usuario, String observacion, LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.proveedor = proveedor;
        this.guiaRemision = guiaRemision;
        this.tipoEntrada = tipoEntrada;
        this.procedencia = procedencia;
        this.fecha = fecha;
        this.usuario = usuario;
        this.observacion = observacion;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public EntradaProductoDTO() {
    }

    public static EntradaProductoDTO fromEntity(EntradaProducto entradaProducto) {
        EntradaProductoDTO dto = new EntradaProductoDTO();
        dto.setId(entradaProducto.getId());

        if (entradaProducto.getProveedor() != null) {
            dto.setProveedor(ProveedorDTO.fromEntity(entradaProducto.getProveedor()));
        }

        dto.setGuiaRemision(entradaProducto.getGuiaRemision());
        dto.setTipoEntrada(entradaProducto.getTipoEntrada());
        dto.setProcedencia(entradaProducto.getProcedencia());
        dto.setFecha(entradaProducto.getFecha());
        dto.setObservacion(entradaProducto.getObservacion());
        if (entradaProducto.getUsuario() != null) {
            dto.setUsuario(UsuarioDTO.fromEntity(entradaProducto.getUsuario()));
        }
        dto.setCreatedAt(entradaProducto.getCreatedAt());
        dto.setUpdatedAt(entradaProducto.getUpdatedAt());
        return dto;
    }

    public EntradaProducto toEntity() {
        EntradaProducto entradaProducto = new EntradaProducto();
        entradaProducto.setId(this.id);

        if (this.proveedor != null) {
            entradaProducto.setProveedor(this.proveedor.toEntity());
        }
        entradaProducto.setGuiaRemision(this.guiaRemision);
        entradaProducto.setTipoEntrada(this.tipoEntrada);
        entradaProducto.setProcedencia(this.procedencia);
        entradaProducto.setFecha(this.fecha);

        if (this.usuario != null) {
            entradaProducto.setUsuario(this.usuario.toEntity());
        }

        entradaProducto.setObservacion(this.observacion);
        entradaProducto.setCreatedAt(this.createdAt);
        entradaProducto.setUpdatedAt(this.updatedAt);
        return entradaProducto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProveedorDTO getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorDTO proveedor) {
        this.proveedor = proveedor;
    }

    public String getGuiaRemision() {
        return guiaRemision;
    }

    public void setGuiaRemision(String guiaRemision) {
        this.guiaRemision = guiaRemision;
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

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    // getters and setters

}

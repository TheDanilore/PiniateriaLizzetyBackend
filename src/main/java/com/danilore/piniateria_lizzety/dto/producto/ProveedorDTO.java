package com.danilore.piniateria_lizzety.dto.producto;

import java.time.LocalDateTime;

import com.danilore.piniateria_lizzety.model.EstadoEnum;
import com.danilore.piniateria_lizzety.model.producto.Proveedor;

public class ProveedorDTO {

    private Long id;
    private Long ruc;
    private String razonSocial;
    private String direccion;
    private String telefono;
    private EstadoEnum estado;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProveedorDTO(Long id, Long ruc, String razonSocial, String direccion, String telefono, EstadoEnum estado,
            LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.direccion = direccion;
        this.telefono = telefono;
        this.estado = estado;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ProveedorDTO() {
    }

    public static ProveedorDTO fromEntity(Proveedor proveedor) {
        ProveedorDTO dto = new ProveedorDTO();
        dto.setId(proveedor.getId());
        dto.setRuc(proveedor.getRuc());
        dto.setRazonSocial(proveedor.getRazonSocial());
        dto.setDireccion(proveedor.getDireccion());
        dto.setTelefono(proveedor.getTelefono());
        dto.setEstado(proveedor.getEstado());
        dto.setCreatedAt(proveedor.getCreatedAt());
        dto.setUpdatedAt(proveedor.getUpdatedAt());
        return dto;
    }

    public Proveedor toEntity() {
        Proveedor proveedor = new Proveedor();
        proveedor.setId(this.id);
        proveedor.setRuc(this.ruc);
        proveedor.setRazonSocial(this.razonSocial);
        proveedor.setDireccion(this.direccion);
        proveedor.setTelefono(this.telefono);
        proveedor.setEstado(this.estado);
        proveedor.setCreatedAt(this.createdAt);
        proveedor.setUpdatedAt(this.updatedAt);
        return proveedor;
    }
    
    //getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRuc() {
        return ruc;
    }

    public void setRuc(Long ruc) {
        this.ruc = ruc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public EstadoEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoEnum estado) {
        this.estado = estado;
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

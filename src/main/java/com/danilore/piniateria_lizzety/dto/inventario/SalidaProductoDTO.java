package com.danilore.piniateria_lizzety.dto.inventario;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.danilore.piniateria_lizzety.dto.usuario.UsuarioDTO;
import com.danilore.piniateria_lizzety.model.inventario.SalidaProducto;
import com.danilore.piniateria_lizzety.model.inventario.enums.TipoSalidaEnum;

public class SalidaProductoDTO {

    private Long id;
    private String guiaSalida;
    private TipoSalidaEnum tipoSalida;
    private String destino;
    private LocalDate fecha;
    private UsuarioDTO usuario;
    private String observacion;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public SalidaProductoDTO(Long id, String guiaSalida, TipoSalidaEnum tipoSalida, String destino, LocalDate fecha,
            UsuarioDTO usuario, String observacion, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.guiaSalida = guiaSalida;
        this.tipoSalida = tipoSalida;
        this.destino = destino;
        this.fecha = fecha;
        this.usuario = usuario;
        this.observacion = observacion;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public SalidaProductoDTO() {
    }

    public static SalidaProductoDTO fromEntity(SalidaProducto salidaProducto) {
        SalidaProductoDTO dto = new SalidaProductoDTO();
        dto.setId(salidaProducto.getId());
        dto.setGuiaSalida(salidaProducto.getGuiaSalida());
        dto.setTipoSalida(salidaProducto.getTipoSalida());
        dto.setDestino(salidaProducto.getDestino());
        dto.setFecha(salidaProducto.getFecha());

        if (salidaProducto.getUsuario() != null) {
            dto.setUsuario(UsuarioDTO.fromEntity(salidaProducto.getUsuario()));
        }

        dto.setObservacion(salidaProducto.getObservacion());
        dto.setCreatedAt(salidaProducto.getCreatedAt());
        dto.setUpdatedAt(salidaProducto.getUpdatedAt());
        return dto;
    }

    public SalidaProducto toEntity() {
        SalidaProducto salidaProducto = new SalidaProducto();
        salidaProducto.setId(this.id);
        salidaProducto.setGuiaSalida(this.guiaSalida);
        salidaProducto.setTipoSalida(this.tipoSalida);
        salidaProducto.setDestino(this.destino);
        salidaProducto.setFecha(this.fecha);

        if (this.usuario != null) {
            salidaProducto.setUsuario(this.usuario.toEntity());
        }

        salidaProducto.setObservacion(this.observacion);
        salidaProducto.setCreatedAt(this.createdAt);
        salidaProducto.setUpdatedAt(this.updatedAt);
        return salidaProducto;
    }

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGuiaSalida() {
        return guiaSalida;
    }

    public void setGuiaSalida(String guiaSalida) {
        this.guiaSalida = guiaSalida;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
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

    public TipoSalidaEnum getTipoSalida() {
        return tipoSalida;
    }

    public void setTipoSalida(TipoSalidaEnum tipoSalida) {
        this.tipoSalida = tipoSalida;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

}

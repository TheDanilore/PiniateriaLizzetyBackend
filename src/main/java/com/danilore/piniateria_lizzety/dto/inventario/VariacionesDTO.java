package com.danilore.piniateria_lizzety.dto.inventario;

import com.danilore.piniateria_lizzety.model.inventario.Variaciones;

public class VariacionesDTO {
    private Long id;
    private InventarioDTO inventario;
    private ColorDTO color;
    private LongitudDTO longitud;
    private TamanoDTO tamano;


    public VariacionesDTO(Long id, InventarioDTO inventario, ColorDTO color, LongitudDTO longitud, TamanoDTO tamano) {
        this.id = id;
        this.inventario = inventario;
        this.color = color;
        this.longitud = longitud;
        this.tamano = tamano;
    }

    public VariacionesDTO() {
    }

    public static VariacionesDTO fromEntity(Variaciones variaciones) {
        VariacionesDTO dto = new VariacionesDTO();
        dto.setId(variaciones.getId());

        if (variaciones.getInventario() != null) {
            dto.setInventario(InventarioDTO.fromEntity(variaciones.getInventario()));
        }

        if (variaciones.getColor() != null) {
            dto.setColor(ColorDTO.fromEntity(variaciones.getColor()));
        }

        if (variaciones.getLongitud() != null) {
            dto.setLongitud(LongitudDTO.fromEntity(variaciones.getLongitud()));
        }

        if (variaciones.getTamano() != null) {
            dto.setTamano(TamanoDTO.fromEntity(variaciones.getTamano()));
        }

        return dto;
    }

    public Variaciones toEntity() {
        Variaciones variaciones = new Variaciones();
        variaciones.setId(this.id);

        if (this.inventario != null) {
            variaciones.setInventario(this.inventario.toEntity());
        }
        if (this.color != null) {
            variaciones.setColor(this.color.toEntity());
        }
        if (this.longitud != null) {
            variaciones.setLongitud(this.longitud.toEntity());
        }
        if (this.tamano != null) {
            variaciones.setTamano(this.tamano.toEntity());
        }

        return variaciones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ColorDTO getColor() {
        return color;
    }

    public void setColor(ColorDTO color) {
        this.color = color;
    }

    public LongitudDTO getLongitud() {
        return longitud;
    }

    public void setLongitud(LongitudDTO longitud) {
        this.longitud = longitud;
    }

    public TamanoDTO getTamano() {
        return tamano;
    }

    public void setTamano(TamanoDTO tamano) {
        this.tamano = tamano;
    }

    public InventarioDTO getInventario() {
        return inventario;
    }

    public void setInventario(InventarioDTO inventario) {
        this.inventario = inventario;
    }


}

package com.danilore.piniateria_lizzety.dto.inventario;

import com.danilore.piniateria_lizzety.model.inventario.Variacion;

public class VariacionDTO {
    private Long id;
    private ColorDTO color;
    private LongitudDTO longitud;
    private TamanoDTO tamano;

    public VariacionDTO(Long id, ColorDTO color, LongitudDTO longitud, TamanoDTO tamano) {
        this.id = id;
        this.color = color;
        this.longitud = longitud;
        this.tamano = tamano;
    }

    public VariacionDTO() {
    }

    public static VariacionDTO fromEntity(Variacion variaciones) {
        VariacionDTO dto = new VariacionDTO();
        dto.setId(variaciones.getId());

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

    public Variacion toEntity() {
        Variacion variaciones = new Variacion();
        variaciones.setId(this.id);

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


}

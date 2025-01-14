package com.danilore.piniateria_lizzety.model.inventario.enums;

public enum TipoMovimientoEnum {

    Entrada,
    Salida,
    Ajuste,
    ReversionSalida,
    ReversionEntrada;

    public static TipoMovimientoEnum fromString(String estado) {
        for (TipoMovimientoEnum e : values()) {
            if (e.name().equalsIgnoreCase(estado)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Estado no válido: " + estado);
    }
}

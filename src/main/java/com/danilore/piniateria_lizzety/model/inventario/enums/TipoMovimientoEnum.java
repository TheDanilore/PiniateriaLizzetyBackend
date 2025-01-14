package com.danilore.piniateria_lizzety.model.inventario.enums;

public enum TipoMovimientoEnum {

    ENTRADA,
    SALIDA,
    AJUSTE,
    REVERSION_SALIDA,
    REVERSION_ENTRADA;

    // Método para convertir una cadena en un enum
    public static TipoMovimientoEnum fromString(String estado) {
        for (TipoMovimientoEnum e : values()) {
            if (e.name().equalsIgnoreCase(estado.replace(" ", "_"))) {
                return e;
            }
        }
        throw new IllegalArgumentException("Estado no válido: " + estado);
    }
}

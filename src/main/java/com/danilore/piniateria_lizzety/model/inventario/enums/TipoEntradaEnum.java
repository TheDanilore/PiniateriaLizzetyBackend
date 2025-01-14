package com.danilore.piniateria_lizzety.model.inventario.enums;

public enum TipoEntradaEnum {
    COMPRAS,
    DONACION,
    OTROS;

    // Método para convertir una cadena en un enum
    public static TipoEntradaEnum fromString(String estado) {
        for (TipoEntradaEnum e : values()) {
            if (e.name().equalsIgnoreCase(estado.replace(" ", "_"))) {
                return e;
            }
        }
        throw new IllegalArgumentException("Estado no válido: " + estado);
    }

}

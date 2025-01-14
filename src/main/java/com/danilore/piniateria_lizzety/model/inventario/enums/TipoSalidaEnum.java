package com.danilore.piniateria_lizzety.model.inventario.enums;

public enum TipoSalidaEnum {

    VENTAS,
    DONACION,
    OTROS;

    // Método para convertir una cadena en un enum
    public static TipoSalidaEnum fromString(String estado) {
        for (TipoSalidaEnum e : values()) {
            if (e.name().equalsIgnoreCase(estado.replace(" ", "_"))) {
                return e;
            }
        }
        throw new IllegalArgumentException("Estado no válido: " + estado);
    }
}

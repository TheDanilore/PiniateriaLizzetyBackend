package com.danilore.piniateria_lizzety.model;

public enum EstadoEnum {
    ACTIVO,
    INACTIVO;

    // Método para convertir una cadena en un enum
    public static EstadoEnum fromString(String estado) {
        for (EstadoEnum e : values()) {
            if (e.name().equalsIgnoreCase(estado.replace(" ", "_"))) {
                return e;
            }
        }
        throw new IllegalArgumentException("Estado no válido: " + estado);
    }
}

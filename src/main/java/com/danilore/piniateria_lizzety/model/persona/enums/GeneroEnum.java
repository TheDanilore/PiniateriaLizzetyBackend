package com.danilore.piniateria_lizzety.model.persona.enums;

public enum GeneroEnum {
    MASCULINO,
    FEMENINO,
    OTRO;

    // Método para convertir una cadena en un enum
    public static GeneroEnum fromString(String estado) {
        for (GeneroEnum e : values()) {
            if (e.name().equalsIgnoreCase(estado.replace(" ", "_"))) {
                return e;
            }
        }
        throw new IllegalArgumentException("Estado no válido: " + estado);
    }
}

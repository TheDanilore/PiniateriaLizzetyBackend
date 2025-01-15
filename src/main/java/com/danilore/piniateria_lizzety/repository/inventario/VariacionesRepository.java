package com.danilore.piniateria_lizzety.repository.inventario;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.danilore.piniateria_lizzety.model.inventario.Color;
import com.danilore.piniateria_lizzety.model.inventario.Inventario;
import com.danilore.piniateria_lizzety.model.inventario.Longitud;
import com.danilore.piniateria_lizzety.model.inventario.Tamano;
import com.danilore.piniateria_lizzety.model.inventario.Variaciones;

@Repository
public interface VariacionesRepository extends JpaRepository<Variaciones, Long> {
        // Buscar por combinación completa: Inventario, Color, Longitud y Tamaño
        Optional<Variaciones> findByInventarioAndColorAndLongitudAndTamano(
                        Inventario inventario,
                        Color color,
                        Longitud longitud,
                        Tamano tamano);

        // Buscar considerando valores nulos en Longitud o Tamaño
        @Query("""
                            SELECT v FROM Variaciones v
                            WHERE v.inventario = :inventario
                              AND v.color = :color
                              AND (:longitud IS NULL OR v.longitud = :longitud)
                              AND (:tamano IS NULL OR v.tamano = :tamano)
                        """)
        Optional<Variaciones> findByAttributes(
                        @Param("inventario") Inventario inventario,
                        @Param("color") Color color,
                        @Param("longitud") Longitud longitud,
                        @Param("tamano") Tamano tamano);

        // Buscar múltiples combinaciones exactas
        @Query("""
                            SELECT v FROM Variaciones v
                            WHERE v.color = :color
                              AND v.longitud = :longitud
                              AND v.tamano = :tamano
                        """)
        List<Variaciones> findExistingVariations(
                        @Param("color") Color color,
                        @Param("longitud") Longitud longitud,
                        @Param("tamano") Tamano tamano);

        // Métodos simplificados para búsquedas parciales
        Optional<Variaciones> findByColor(Color color);

        Optional<Variaciones> findByColorAndTamano(Color color, Tamano tamano);

        Optional<Variaciones> findByColorAndLongitud(Color color, Longitud longitud);

}

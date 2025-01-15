package com.danilore.piniateria_lizzety.repository.inventario;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.danilore.piniateria_lizzety.model.inventario.Color;
import com.danilore.piniateria_lizzety.model.inventario.Inventario;
import com.danilore.piniateria_lizzety.model.inventario.Longitud;
import com.danilore.piniateria_lizzety.model.inventario.Tamano;
import com.danilore.piniateria_lizzety.model.inventario.Variacion;

@Repository
public interface VariacionRepository extends JpaRepository<Variacion, Long> {
         @Query("SELECT v FROM Variacion v WHERE v.color = :color AND v.longitud = :longitud AND v.tamano = :tamano")
    Optional<Variacion> findByAttributes(@Param("color") Color color, 
                                         @Param("longitud") Longitud longitud, 
                                         @Param("tamano") Tamano tamano);
        
        // Métodos simplificados para búsquedas parciales
        Optional<Variacion> findByColor(Color color);
        Optional<Variacion> findByTamano(Tamano tamano);
        Optional<Variacion> findByLongitud(Longitud longitud);
        Optional<Variacion> findByInventario(Inventario inventario);


        Optional<Variacion> findByColorAndTamano(Color color, Tamano tamano);

        Optional<Variacion> findByColorAndLongitud(Color color, Longitud longitud);

        Optional<Variacion> findByColorAndLongitudAndTamano(Color color, Longitud longitud, Tamano tamano);


}

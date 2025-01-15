package com.danilore.piniateria_lizzety.service.inventario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.danilore.piniateria_lizzety.dto.inventario.VariacionDTO;
import com.danilore.piniateria_lizzety.exception.DAOException;
import com.danilore.piniateria_lizzety.model.inventario.Variacion;
import com.danilore.piniateria_lizzety.repository.inventario.VariacionRepository;

@Service
public class VariacionService {

    @Autowired
    private VariacionRepository variacionesRepository;

    public VariacionDTO save(VariacionDTO variacionesDTO) {
        Variacion variacion = variacionesDTO.toEntity();


        Variacion savedvariaciones = variacionesRepository.save(variacion);
        return VariacionDTO.fromEntity(savedvariaciones);
    }

    public VariacionDTO update(Long id, VariacionDTO variacionesDTO) {
        // Convertir el DTO en una entidad
        Variacion variacionesActualizada = variacionesDTO.toEntity();

        Variacion variacionesExistente = variacionesRepository.findById(id)
                .orElseThrow(() -> new DAOException("Variacion no encontrado con ID: " + id));

        variacionesExistente.setInventario(variacionesActualizada.getInventario());
        variacionesExistente.setColor(variacionesActualizada.getColor());
        variacionesExistente.setLongitud(variacionesActualizada.getLongitud());
        variacionesExistente.setTamano(variacionesActualizada.getTamano());

        // Convertir la entidad actualizada nuevamente a DTO y devolverla
        return VariacionDTO.fromEntity(variacionesRepository.save(variacionesExistente));
    }
}

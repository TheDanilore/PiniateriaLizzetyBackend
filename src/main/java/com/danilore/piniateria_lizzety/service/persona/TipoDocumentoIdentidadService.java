package com.danilore.piniateria_lizzety.service.persona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.danilore.piniateria_lizzety.dto.persona.TipoDocumentoIdentidadDTO;
import com.danilore.piniateria_lizzety.exception.DAOException;
import com.danilore.piniateria_lizzety.model.persona.TipoDocumentoIdentidad;
import com.danilore.piniateria_lizzety.repository.persona.TipoDocumentoIdentidadRepository;

@Service
public class TipoDocumentoIdentidadService {

    @Autowired
    private TipoDocumentoIdentidadRepository tipoDocumentoIdentidadRepository;

    public Page<TipoDocumentoIdentidad> listarTodos(Pageable pageable) {
        return tipoDocumentoIdentidadRepository.findAll(pageable);
    }

    public TipoDocumentoIdentidad guardar(TipoDocumentoIdentidad tipoDocumentoIdentidad) {
        if (tipoDocumentoIdentidadRepository.findByAbreviatura(tipoDocumentoIdentidad.getAbreviatura()).isPresent()) {
            throw new DAOException("La abreviatura ya está registrado.");
        }

        if (tipoDocumentoIdentidadRepository.findByDescripcion(tipoDocumentoIdentidad.getDescripcion()).isPresent()) {
            throw new DAOException("La descripcion ya está registrado.");
        }

        return tipoDocumentoIdentidadRepository.save(tipoDocumentoIdentidad);
    }

    public TipoDocumentoIdentidad editar(String id, TipoDocumentoIdentidad tipoDocumentoIdentidadActualizado) {
        TipoDocumentoIdentidad tipoDocumentoIdentidadExistente = tipoDocumentoIdentidadRepository.findById(id)
                .orElseThrow(() -> new DAOException("Tipo de documento de identidad no encontrado."));

        tipoDocumentoIdentidadExistente.setAbreviatura(tipoDocumentoIdentidadActualizado.getAbreviatura());
        tipoDocumentoIdentidadExistente.setDescripcion(tipoDocumentoIdentidadActualizado.getDescripcion());

        return tipoDocumentoIdentidadRepository.save(tipoDocumentoIdentidadExistente);
    }

    public TipoDocumentoIdentidadDTO buscarPorIdDTO(String id) {
        TipoDocumentoIdentidad tipoDocumentoIdentidad = tipoDocumentoIdentidadRepository.findById(id)
                .orElseThrow(() -> new DAOException("Tipo de documento de identidad no encontrado."));
        return TipoDocumentoIdentidadDTO.fromEntity(tipoDocumentoIdentidad);
    }

    public void eliminar(String id) {
        TipoDocumentoIdentidad tipoDocumentoIdentidad = tipoDocumentoIdentidadRepository.findById(id)
                .orElseThrow(() -> new DAOException("Tipo de documento de identidad no encontrado."));

        tipoDocumentoIdentidadRepository.delete(tipoDocumentoIdentidad);
    }

    public TipoDocumentoIdentidad buscarPorAbreviatura(String abreviatura) {
        return tipoDocumentoIdentidadRepository.findByAbreviatura(abreviatura)
                .orElseThrow(() -> new DAOException("Tipo de documento de identidad no encontrado."));
    }

    public TipoDocumentoIdentidad buscarPorDescripcion(String descripcion) {
        return tipoDocumentoIdentidadRepository.findByDescripcion(descripcion)
                .orElseThrow(() -> new DAOException("Tipo de documento de identidad no encontrado."));
    }

}

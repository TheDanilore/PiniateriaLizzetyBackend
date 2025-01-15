package com.danilore.piniateria_lizzety.service.persona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.danilore.piniateria_lizzety.dto.persona.TipoDocumentoIdentidadDTO;
import com.danilore.piniateria_lizzety.exception.DAOException;
import com.danilore.piniateria_lizzety.model.persona.TipoDocumentoIdentidad;
import com.danilore.piniateria_lizzety.repository.persona.TipoDocumentoIdentidadRepository;

@Service
public class TipoDocumentoIdentidadService {

    @Autowired
    private TipoDocumentoIdentidadRepository tipoDocumentoIdentidadRepository;

    public Page<TipoDocumentoIdentidadDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TipoDocumentoIdentidad> departamentoPage = tipoDocumentoIdentidadRepository.findAll(pageable);

        return departamentoPage.map(TipoDocumentoIdentidadDTO::fromEntity);
    }

    public TipoDocumentoIdentidadDTO getById(String id) {
        TipoDocumentoIdentidad tipoDocumentoIdentidad = tipoDocumentoIdentidadRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tipo de Documento de Identidad no encontrado"));
        return TipoDocumentoIdentidadDTO.fromEntity(tipoDocumentoIdentidad);
    }

    public TipoDocumentoIdentidadDTO save(TipoDocumentoIdentidadDTO tipoDocumentoIdentidadDTO) {
        TipoDocumentoIdentidad tipoDocumentoIdentidad = tipoDocumentoIdentidadDTO.toEntity();

        if (tipoDocumentoIdentidadRepository.findByDescripcion(tipoDocumentoIdentidad.getDescripcion()).isPresent()) {
            throw new DAOException("La descripción ya está registrado.");
        }
        if (tipoDocumentoIdentidadRepository.findByAbreviatura(tipoDocumentoIdentidad.getAbreviatura()).isPresent()) {
            throw new DAOException("La abreviatura ya está registrado.");
        }

        TipoDocumentoIdentidad savedTipoDocumentoIdentidad = tipoDocumentoIdentidadRepository
                .save(tipoDocumentoIdentidad);
        return TipoDocumentoIdentidadDTO.fromEntity(savedTipoDocumentoIdentidad);
    }

    public TipoDocumentoIdentidadDTO update(String id, TipoDocumentoIdentidadDTO tipoDocumentoIdentidadDTO) {
        // Convertir el DTO en una entidad Usuario
        TipoDocumentoIdentidad tipoActualizado = tipoDocumentoIdentidadDTO.toEntity();

        TipoDocumentoIdentidad tipoExistente = tipoDocumentoIdentidadRepository.findById(id)
                .orElseThrow(() -> new DAOException("Tipo Documento de Identidad no encontrado con ID: " + id));

        tipoExistente.setAbreviatura(tipoActualizado.getAbreviatura());
        tipoExistente.setDescripcion(tipoActualizado.getDescripcion());

        // Convertir la entidad actualizada nuevamente a DTO y devolverla
        return TipoDocumentoIdentidadDTO.fromEntity(tipoDocumentoIdentidadRepository.save(tipoExistente));
    }

    public void deleteById(String id) {
        if (!tipoDocumentoIdentidadRepository.existsById(id)) {
            throw new DAOException("Tipo Documento de Identidad no encontrado");
        }
        tipoDocumentoIdentidadRepository.deleteById(id);
    }

    public TipoDocumentoIdentidad buscarPorAbreviatura(String abreviatura) {
        return tipoDocumentoIdentidadRepository.findByAbreviatura(abreviatura)
                .orElseThrow(() -> new DAOException("Tipo de documento de identidad no encontrado."));
    }

    public TipoDocumentoIdentidad buscarPorDescripcion(String descripcion) {
        return tipoDocumentoIdentidadRepository.findByDescripcion(descripcion)
                .orElseThrow(() -> new DAOException("Tipo de documento de identidad no encontrado."));
    }

    // Listar por descripcion o ID
    public Page<TipoDocumentoIdentidadDTO> buscarPorCriterio(String criterio, int page, int size) {
        if (criterio == null || criterio.isBlank()) {
            throw new IllegalArgumentException("El criterio de búsqueda no puede estar vacío");
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("descripcion").ascending());
        Page<TipoDocumentoIdentidad> tipoDocumentoPage = tipoDocumentoIdentidadRepository.buscarPorCriterio(criterio, pageable);
        return tipoDocumentoPage.map(TipoDocumentoIdentidadDTO::fromEntity);
    }

}

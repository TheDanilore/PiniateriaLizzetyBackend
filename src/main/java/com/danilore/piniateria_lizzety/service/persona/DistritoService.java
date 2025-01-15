package com.danilore.piniateria_lizzety.service.persona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.danilore.piniateria_lizzety.dto.persona.DistritoDTO;
import com.danilore.piniateria_lizzety.exception.DAOException;
import com.danilore.piniateria_lizzety.model.persona.Distrito;
import com.danilore.piniateria_lizzety.repository.persona.DistritoRepository;

@Service
public class DistritoService {

    @Autowired
    private DistritoRepository distritoRepository;

    public Page<DistritoDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Distrito> distritoPage = distritoRepository.findAll(pageable);

        return distritoPage.map(DistritoDTO::fromEntity);
    }

    public Page<DistritoDTO> listarPorProvincia(Integer id, Pageable pageable) {
        return distritoRepository.findByProvinciaIdProvincia(id, pageable)
                .map(DistritoDTO::fromEntity);
    }

    public DistritoDTO getById(int id) {
        Distrito distrito = distritoRepository.findById(id)
                .orElseThrow(() -> new DAOException("Distrito no encontrado"));
        return DistritoDTO.fromEntity(distrito);
    }

    public DistritoDTO save(DistritoDTO distritoDTO) {
        Distrito distrito = distritoDTO.toEntity();

        if (distritoRepository.findByDescripcion(distrito.getDescripcion()).isPresent()) {
            throw new DAOException("La descripción ya está registrado.");
        }

        Distrito savedDistrito = distritoRepository.save(distrito);
        return DistritoDTO.fromEntity(savedDistrito);
    }

    public DistritoDTO update(int id, DistritoDTO distritoDTO) {
        // Convertir el DTO en una entidad Usuario
        Distrito distritoActualizado = distritoDTO.toEntity();

        Distrito distritoExistente = distritoRepository.findById(id)
                .orElseThrow(() -> new DAOException("Distrito no encontrado con ID: " + id));

        distritoExistente.setDescripcion(distritoActualizado.getDescripcion());

        // Convertir la entidad actualizada nuevamente a DTO y devolverla
        return DistritoDTO.fromEntity(distritoRepository.save(distritoExistente));
    }

    public void deleteById(int id) {
        if (!distritoRepository.existsById(id)) {
            throw new DAOException("Distrito no encontrado");
        }
        distritoRepository.deleteById(id);
    }

    public Distrito buscarPorDescripcion(String descripcion) {
        return distritoRepository.findByDescripcion(descripcion)
                .orElseThrow(() -> new RuntimeException("Distrito no encontrado."));
    }

    // Listar usuario por descripcion o ID
    public Page<DistritoDTO> buscarPorCriterio(String criterio, int page, int size) {
        if (criterio == null || criterio.isBlank()) {
            throw new IllegalArgumentException("El criterio de búsqueda no puede estar vacío");
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("descripcion").ascending());
        Page<Distrito> distritoPage = distritoRepository.buscarPorCriterio(criterio, pageable);
        return distritoPage.map(DistritoDTO::fromEntity);
    }

}

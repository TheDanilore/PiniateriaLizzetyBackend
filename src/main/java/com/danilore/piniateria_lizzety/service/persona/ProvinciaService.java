package com.danilore.piniateria_lizzety.service.persona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.danilore.piniateria_lizzety.dto.persona.ProvinciaDTO;
import com.danilore.piniateria_lizzety.exception.DAOException;
import com.danilore.piniateria_lizzety.model.persona.Provincia;
import com.danilore.piniateria_lizzety.repository.persona.ProvinciaRepository;

@Service
public class ProvinciaService {

    @Autowired
    private ProvinciaRepository provinciaRepository;

    public Page<ProvinciaDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Provincia> provinciaPage = provinciaRepository.findAll(pageable);

        return provinciaPage.map(ProvinciaDTO::fromEntity);
    }

    public Page<ProvinciaDTO> listarPorDepartamento(Integer id, Pageable pageable) {
        return provinciaRepository.findByDepartamentoIdDepartamento(id, pageable)
                .map(ProvinciaDTO::fromEntity);
    }

    public ProvinciaDTO getById(int id) {
        Provincia provincia = provinciaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Provincia no encontrado"));
        return ProvinciaDTO.fromEntity(provincia);
    }

    public ProvinciaDTO save(ProvinciaDTO provinciaDTO) {
        Provincia provincia = provinciaDTO.toEntity();

        if (provinciaRepository.findByDescripcion(provincia.getDescripcion()).isPresent()) {
            throw new DAOException("La descripción ya está registrado.");
        }

        Provincia savedProvincia = provinciaRepository.save(provincia);
        return ProvinciaDTO.fromEntity(savedProvincia);
    }

    public ProvinciaDTO update(int id, ProvinciaDTO provinciaDTO) {
        // Convertir el DTO en una entidad Usuario
        Provincia provinciaActualizado = provinciaDTO.toEntity();

        Provincia provinciaExistente = provinciaRepository.findById(id)
                .orElseThrow(() -> new DAOException("Provincia no encontrado con ID: " + id));

        provinciaExistente.setDescripcion(provinciaActualizado.getDescripcion());

        // Convertir la entidad actualizada nuevamente a DTO y devolverla
        return ProvinciaDTO.fromEntity(provinciaRepository.save(provinciaExistente));
    }

    public void deleteById(int id) {
        if (!provinciaRepository.existsById(id)) {
            throw new DAOException("Provincia no encontrado");
        }
        provinciaRepository.deleteById(id);
    }

    public Provincia buscarPorDescripcion(String descripcion) {
        return provinciaRepository.findByDescripcion(descripcion)
                .orElseThrow(() -> new RuntimeException("Provincia no encontrado."));
    }

    public Provincia buscarPorIdConDepartamento(Integer id) {
        return provinciaRepository.findByIdConDepartamento(id)
                .orElseThrow(() -> new RuntimeException("Provincia no encontrado."));
    }

    // Listar usuario por descripcion o ID
    public Page<ProvinciaDTO> buscarPorCriterio(String criterio, int page, int size) {
        if (criterio == null || criterio.isBlank()) {
            throw new IllegalArgumentException("El criterio de búsqueda no puede estar vacío");
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("descripcion").ascending());
        Page<Provincia> provinciaPage = provinciaRepository.buscarPorCriterio(criterio, pageable);
        return provinciaPage.map(ProvinciaDTO::fromEntity);
    }
}

package com.danilore.piniateria_lizzety.service.persona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.danilore.piniateria_lizzety.dto.persona.DepartamentoDTO;
import com.danilore.piniateria_lizzety.exception.DAOException;
import com.danilore.piniateria_lizzety.model.persona.Departamento;
import com.danilore.piniateria_lizzety.repository.persona.DepartamentoRepository;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public Page<DepartamentoDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Departamento> usuarioPage = departamentoRepository.findAll(pageable);

        return usuarioPage.map(DepartamentoDTO::fromEntity);
    }

    public DepartamentoDTO getById(int id) {
        Departamento departamento = departamentoRepository.findById(id)
                .orElseThrow(() -> new DAOException("Departamento no encontrado"));
        return DepartamentoDTO.fromEntity(departamento);
    }

    public DepartamentoDTO save(DepartamentoDTO departamentoDTO) {
        Departamento departamento = departamentoDTO.toEntity();

        if (departamentoRepository.findByDescripcion(departamento.getDescripcion()).isPresent()) {
            throw new DAOException("La descripción ya está registrado.");
        }

        Departamento savedDepartamento = departamentoRepository.save(departamento);
        return DepartamentoDTO.fromEntity(savedDepartamento);
    }

    public DepartamentoDTO update(int id, DepartamentoDTO departamentoDTO) {
        // Convertir el DTO en una entidad Usuario
        Departamento departamentoActualizado = departamentoDTO.toEntity();

        Departamento departamentoExistente = departamentoRepository.findById(id)
                .orElseThrow(() -> new DAOException("Departamento no encontrado con ID: " + id));

        departamentoExistente.setDescripcion(departamentoActualizado.getDescripcion());

        // Convertir la entidad actualizada nuevamente a DTO y devolverla
        return DepartamentoDTO.fromEntity(departamentoRepository.save(departamentoExistente));
    }

    public void deleteById(int id) {
        if (!departamentoRepository.existsById(id)) {
            throw new DAOException("Departamento no encontrado");
        }
        departamentoRepository.deleteById(id);
    }

    public Departamento buscarPorDescripcion(String descripcion) {
        return departamentoRepository.findByDescripcion(descripcion)
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado."));
    }

    // Listar por descripcion o ID
    public Page<DepartamentoDTO> buscarPorCriterio(String criterio, int page, int size) {
        if (criterio == null || criterio.isBlank()) {
            throw new IllegalArgumentException("El criterio de búsqueda no puede estar vacío");
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("descripcion").ascending());
        Page<Departamento> departamentoPage = departamentoRepository.buscarPorCriterio(criterio, pageable);
        return departamentoPage.map(DepartamentoDTO::fromEntity);
    }

}

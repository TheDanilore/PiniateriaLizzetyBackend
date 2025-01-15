package com.danilore.piniateria_lizzety.service.inventario;

import com.danilore.piniateria_lizzety.dto.inventario.EntradaProductoDTO;
import com.danilore.piniateria_lizzety.exception.DAOException;
import com.danilore.piniateria_lizzety.model.inventario.EntradaProducto;
import com.danilore.piniateria_lizzety.repository.inventario.EntradaProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EntradaProductoService {

    @Autowired
    private EntradaProductoRepository entradaProductoRepository;

    public Page<EntradaProductoDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EntradaProducto> entradaPage = entradaProductoRepository.findAll(pageable);

        return entradaPage.map(EntradaProductoDTO::fromEntity);
    }

    public EntradaProductoDTO getById(Long id) {
        EntradaProducto entrada = entradaProductoRepository.findById(id)
                .orElseThrow(() -> new DAOException("Entrada no encontrado con ID: " + id));
        return EntradaProductoDTO.fromEntity(entrada);
    }

    public EntradaProductoDTO save(EntradaProductoDTO salidaProductoDTO) {
        EntradaProducto salida = salidaProductoDTO.toEntity();

        if (entradaProductoRepository.findByGuiaRemision(salida.getGuiaRemision()).isPresent()) {
            throw new DAOException("La guia de remisión ya está registrado.");
        }

        EntradaProducto savedSalida = entradaProductoRepository.save(salida);
        return EntradaProductoDTO.fromEntity(savedSalida);
    }

    public EntradaProductoDTO update(Long id, EntradaProductoDTO salidaProductoDTO) {
        EntradaProducto salidaActualizado = salidaProductoDTO.toEntity();

        EntradaProducto salidaExistente = entradaProductoRepository.findById(id)
                .orElseThrow(() -> new DAOException("Salida no encontrado con ID: " + id));

        salidaExistente.setProveedor(salidaActualizado.getProveedor());
        salidaExistente.setGuiaRemision(salidaActualizado.getGuiaRemision());
        salidaExistente.setTipoEntrada(salidaActualizado.getTipoEntrada());
        salidaExistente.setProcedencia(salidaActualizado.getProcedencia());
        salidaExistente.setFecha(salidaActualizado.getFecha());
        salidaExistente.setObservacion(salidaActualizado.getObservacion());

        return EntradaProductoDTO.fromEntity(entradaProductoRepository.save(salidaExistente));
    }

    public void deleteById(Long id) {
        if (!entradaProductoRepository.existsById(id)) {
            throw new DAOException("Salida no encontrado con ID: " + id);
        }
        entradaProductoRepository.deleteById(id);
    }

    // Listar por descripcion o ID
    public Page<EntradaProductoDTO> buscarPorCriterio(String criterio, int page, int size) {
        if (criterio == null || criterio.isBlank()) {
            throw new DAOException("El criterio de búsqueda no puede estar vacío");
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("guiaRemision").ascending());
        Page<EntradaProducto> entradaPage = entradaProductoRepository.buscarPorCriterio(criterio, pageable);
        return entradaPage.map(EntradaProductoDTO::fromEntity);
    }

}

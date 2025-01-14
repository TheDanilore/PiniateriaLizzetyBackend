package com.danilore.piniateria_lizzety.service.inventario;

import com.danilore.piniateria_lizzety.dto.inventario.SalidaProductoDTO;
import com.danilore.piniateria_lizzety.exception.ResourceNotFoundException;
import com.danilore.piniateria_lizzety.model.inventario.SalidaProducto;
import com.danilore.piniateria_lizzety.repository.inventario.SalidaProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SalidaProductoService {

    @Autowired
    private SalidaProductoRepository salidaProductoRepository;

    public Page<SalidaProductoDTO> listarTodos(Pageable pageable) {
        return salidaProductoRepository.findAll(pageable).map(SalidaProductoDTO::fromEntity);
    }

    public SalidaProductoDTO buscarPorId(Long id) {
        SalidaProducto salidaProducto = salidaProductoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Salida de Producto no encontrada con ID: " + id));
        return SalidaProductoDTO.fromEntity(salidaProducto);
    }

    public SalidaProductoDTO guardar(SalidaProductoDTO salidaProductoDTO) {
        SalidaProducto salidaProducto = salidaProductoDTO.toEntity();
        SalidaProducto salidaGuardada = salidaProductoRepository.save(salidaProducto);
        return SalidaProductoDTO.fromEntity(salidaGuardada);
    }

    public SalidaProductoDTO editar(Long id, SalidaProductoDTO salidaProductoDTO) {
        SalidaProducto salidaExistente = salidaProductoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Salida de Producto no encontrada con ID: " + id));

        salidaExistente.setGuiaSalida(salidaProductoDTO.getGuiaSalida());
        salidaExistente.setTipoSalida(salidaProductoDTO.getTipoSalida());
        salidaExistente.setDestino(salidaProductoDTO.getDestino());
        salidaExistente.setFecha(salidaProductoDTO.getFecha());
        salidaExistente.setObservacion(salidaProductoDTO.getObservacion());
        salidaExistente.setUpdatedAt(salidaProductoDTO.getUpdatedAt());

        SalidaProducto salidaEditada = salidaProductoRepository.save(salidaExistente);
        return SalidaProductoDTO.fromEntity(salidaEditada);
    }

    public void eliminar(Long id) {
        if (!salidaProductoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Salida de Producto no encontrada con ID: " + id);
        }
        salidaProductoRepository.deleteById(id);
    }
}

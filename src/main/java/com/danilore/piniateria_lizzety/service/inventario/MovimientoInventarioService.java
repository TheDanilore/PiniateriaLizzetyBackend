package com.danilore.piniateria_lizzety.service.inventario;

import com.danilore.piniateria_lizzety.dto.inventario.MovimientoInventarioDTO;
import com.danilore.piniateria_lizzety.exception.DAOException;
import com.danilore.piniateria_lizzety.repository.inventario.MovimientoInventarioRepository;
import com.danilore.piniateria_lizzety.model.inventario.MovimientoInventario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MovimientoInventarioService {

    @Autowired
    private MovimientoInventarioRepository movimientoInventarioRepository;

    public Page<MovimientoInventarioDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MovimientoInventario> movimientoPage = movimientoInventarioRepository.findAll(pageable);

        return movimientoPage.map(MovimientoInventarioDTO::fromEntity);
    }

    public MovimientoInventarioDTO getById(Long id) {
        MovimientoInventario movimiento = movimientoInventarioRepository.findById(id)
                .orElseThrow(() -> new DAOException("Movimiento no encontrado con ID: " + id));
        return MovimientoInventarioDTO.fromEntity(movimiento);
    }

    public MovimientoInventarioDTO save(MovimientoInventarioDTO dto) {
        MovimientoInventario movimiento = dto.toEntity();

        MovimientoInventario savedMovimiento = movimientoInventarioRepository.save(movimiento);
        return MovimientoInventarioDTO.fromEntity(savedMovimiento);
    }

    public MovimientoInventarioDTO update(Long id, MovimientoInventarioDTO movimientoDTO) {
        MovimientoInventario movimientoActualizado = movimientoDTO.toEntity();

        MovimientoInventario movimientoExistente = movimientoInventarioRepository.findById(id)
                .orElseThrow(() -> new DAOException("Movimiento de Inevntario no encontrado con ID: " + id));

        movimientoExistente.setInventario(movimientoActualizado.getInventario());
        movimientoExistente.setTipoMovimiento(movimientoActualizado.getTipoMovimiento());
        movimientoExistente.setCantidad(movimientoActualizado.getCantidad());
        movimientoExistente.setCantidadAnterior(movimientoActualizado.getCantidadAnterior());
        movimientoExistente.setCantidadActual(movimientoActualizado.getCantidadActual());
        movimientoExistente.setUsuario(movimientoActualizado.getUsuario());
        movimientoExistente.setObservacion(movimientoActualizado.getObservacion());
        movimientoExistente.setFecha(movimientoActualizado.getFecha());

        return MovimientoInventarioDTO.fromEntity(movimientoInventarioRepository.save(movimientoExistente));
    }

    public void deleteById(Long id) {
        if (!movimientoInventarioRepository.existsById(id)) {
            throw new DAOException("Movimiento no encontrado con ID: " + id);
        }
        movimientoInventarioRepository.deleteById(id);
    }

    // Listar por descripcion o ID
    public Page<MovimientoInventarioDTO> buscarPorCriterio(String criterio, int page, int size) {
        if (criterio == null || criterio.isBlank()) {
            throw new DAOException("El criterio de búsqueda no puede estar vacío");
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("inventario").ascending());
        Page<MovimientoInventario> movimientoPage = movimientoInventarioRepository.buscarPorCriterio(criterio, pageable);
        return movimientoPage.map(MovimientoInventarioDTO::fromEntity);
    }

}

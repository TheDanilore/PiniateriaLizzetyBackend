package com.danilore.piniateria_lizzety.service.inventario;

import com.danilore.piniateria_lizzety.dto.inventario.SalidaProductoDTO;
import com.danilore.piniateria_lizzety.exception.DAOException;
import com.danilore.piniateria_lizzety.exception.ResourceNotFoundException;
import com.danilore.piniateria_lizzety.model.inventario.SalidaProducto;
import com.danilore.piniateria_lizzety.repository.inventario.InventarioRepository;
import com.danilore.piniateria_lizzety.repository.inventario.ItemEntradaRepository;
import com.danilore.piniateria_lizzety.repository.inventario.ItemSalidaRepository;
import com.danilore.piniateria_lizzety.repository.inventario.MovimientoInventarioRepository;
import com.danilore.piniateria_lizzety.repository.inventario.SalidaProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SalidaProductoService {

    @Autowired
    private SalidaProductoRepository salidaProductoRepository;

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private MovimientoInventarioRepository movimientoInventarioRepository;

    @Autowired
    private ItemSalidaRepository itemSalidaRepository;

    public Page<SalidaProductoDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SalidaProducto> salidaPage = salidaProductoRepository.findAll(pageable);

        return salidaPage.map(SalidaProductoDTO::fromEntity);
    }

    public SalidaProductoDTO getById(Long id) {
        SalidaProducto salidaProducto = salidaProductoRepository.findById(id)
                .orElseThrow(() -> new DAOException("Salida de Producto no encontrada con ID: " + id));
        return SalidaProductoDTO.fromEntity(salidaProducto);
    }

    public SalidaProductoDTO save(SalidaProductoDTO salidaProductoDTO) {
        SalidaProducto salidaProducto = salidaProductoDTO.toEntity();

        if (salidaProductoRepository.findByGuiaSalida(salidaProducto.getGuiaSalida()).isPresent()) {
            throw new DAOException("La guia de salida ya está registrado.");
        }

        SalidaProducto salidaGuardada = salidaProductoRepository.save(salidaProducto);
        return SalidaProductoDTO.fromEntity(salidaGuardada);
    }

    public SalidaProductoDTO update(Long id, SalidaProductoDTO salidaProductoDTO) {
        SalidaProducto salidaActualizada = salidaProductoDTO.toEntity();

        SalidaProducto salidaExistente = salidaProductoRepository.findById(id)
                .orElseThrow(() -> new DAOException("Salida de Producto no encontrada con ID: " + id));

        salidaExistente.setGuiaSalida(salidaActualizada.getGuiaSalida());
        salidaExistente.setTipoSalida(salidaActualizada.getTipoSalida());
        salidaExistente.setDestino(salidaActualizada.getDestino());
        salidaExistente.setFecha(salidaActualizada.getFecha());
        salidaExistente.setObservacion(salidaActualizada.getObservacion());

        return SalidaProductoDTO.fromEntity(salidaProductoRepository.save(salidaExistente));
    }

    public void deleteById(Long id) {
        if (!salidaProductoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Salida de Producto no encontrada con ID: " + id);
        }
        salidaProductoRepository.deleteById(id);
    }

    // Listar por guia de salida, o ID
    public Page<SalidaProductoDTO> buscarPorCriterio(String criterio, int page, int size) {
        if (criterio == null || criterio.isBlank()) {
            throw new DAOException("El criterio de búsqueda no puede estar vacío");
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("guiaSalida").ascending());
        Page<SalidaProducto> salidaPage = salidaProductoRepository.buscarPorCriterio(criterio, pageable);
        return salidaPage.map(SalidaProductoDTO::fromEntity);
    }

}

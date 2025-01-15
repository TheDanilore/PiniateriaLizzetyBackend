package com.danilore.piniateria_lizzety.service.inventario;

import com.danilore.piniateria_lizzety.dto.inventario.InventarioDTO;
import com.danilore.piniateria_lizzety.exception.DAOException;
import com.danilore.piniateria_lizzety.model.inventario.Inventario;
import com.danilore.piniateria_lizzety.repository.inventario.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    public Page<InventarioDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Inventario> inventarioPage = inventarioRepository.findAll(pageable);

        return inventarioPage.map(InventarioDTO::fromEntity);
    }

    public InventarioDTO getById(Long id) {
        Inventario inventario = inventarioRepository.findById(id)
                .orElseThrow(() -> new DAOException("Tamaño no encontrado con ID: " + id));
        return InventarioDTO.fromEntity(inventario);
    }

    public InventarioDTO save(InventarioDTO inventarioDTO) {
        Inventario inventario = inventarioDTO.toEntity();

        Inventario savedInventario = inventarioRepository.save(inventario);
        return InventarioDTO.fromEntity(savedInventario);
    }

    public InventarioDTO update(Long id, InventarioDTO inventarioDTO) {
        Inventario inventarioActualizado = inventarioDTO.toEntity();

        Inventario inventarioExistente = inventarioRepository.findById(id)
                .orElseThrow(() -> new DAOException("Inventario no encontrado con ID: " + id));

        inventarioExistente.setProducto(inventarioActualizado.getProducto());
        inventarioExistente.setColor(inventarioActualizado.getColor());
        inventarioExistente.setLongitud(inventarioActualizado.getLongitud());
        inventarioExistente.setTamano(inventarioActualizado.getTamano());
        inventarioExistente.setPrecioUnitario(inventarioActualizado.getPrecioUnitario());
        inventarioExistente.setCantidad(inventarioActualizado.getCantidad());

        return InventarioDTO.fromEntity(inventarioRepository.save(inventarioExistente));

    }

    public void deleteById(Long id) {
        if (!inventarioRepository.existsById(id)) {
            throw new DAOException("Inventario no encontrado con ID: " + id);
        }
        inventarioRepository.deleteById(id);
    }

    public Page<InventarioDTO> buscarPorCriterio(String criterio, int page, int size) {
        if (criterio == null || criterio.isBlank()) {
            throw new DAOException("El criterio de búsqueda no puede estar vacío");
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("producto").ascending());
        Page<Inventario> inventarioPage = inventarioRepository.buscarPorCriterio(criterio, pageable);
        return inventarioPage.map(InventarioDTO::fromEntity);
    }

}

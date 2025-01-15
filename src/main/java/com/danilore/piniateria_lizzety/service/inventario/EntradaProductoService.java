package com.danilore.piniateria_lizzety.service.inventario;

import com.danilore.piniateria_lizzety.dto.inventario.EntradaProductoDTO;
import com.danilore.piniateria_lizzety.dto.inventario.ItemEntradaDTO;
import com.danilore.piniateria_lizzety.exception.DAOException;
import com.danilore.piniateria_lizzety.model.inventario.EntradaProducto;
import com.danilore.piniateria_lizzety.model.inventario.Inventario;
import com.danilore.piniateria_lizzety.model.inventario.ItemEntrada;
import com.danilore.piniateria_lizzety.model.inventario.MovimientoInventario;
import com.danilore.piniateria_lizzety.model.inventario.enums.TipoMovimientoEnum;
import com.danilore.piniateria_lizzety.repository.inventario.EntradaProductoRepository;
import com.danilore.piniateria_lizzety.repository.inventario.InventarioRepository;
import com.danilore.piniateria_lizzety.repository.inventario.ItemEntradaRepository;
import com.danilore.piniateria_lizzety.repository.inventario.MovimientoInventarioRepository;

import jakarta.transaction.Transactional;

import java.util.List;

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

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private MovimientoInventarioRepository movimientoInventarioRepository;

    private ItemEntradaRepository itemEntradaRepository;

    public Page<EntradaProductoDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("fecha").descending());
        Page<EntradaProducto> entradaPage = entradaProductoRepository.findAll(pageable);

        return entradaPage.map(EntradaProductoDTO::fromEntity);
    }

    public EntradaProductoDTO getById(Long id) {
        EntradaProducto entrada = entradaProductoRepository.findById(id)
                .orElseThrow(() -> new DAOException("Entrada no encontrado con ID: " + id));
        return EntradaProductoDTO.fromEntity(entrada);
    }

    @Transactional
    public EntradaProductoDTO save(EntradaProductoDTO entradaProductoDTO, List<ItemEntradaDTO> itemsEntradaDTO) {
        // Convertir DTO a entidad
        EntradaProducto entrada = entradaProductoDTO.toEntity();

        // Validar si la guía de remisión ya existe
        if (entradaProductoRepository.findByGuiaRemision(entrada.getGuiaRemision()).isPresent()) {
            throw new DAOException("La guía de remisión ya está registrada.");
        }

        // Guardar la entrada principal
        EntradaProducto savedEntrada = entradaProductoRepository.save(entrada);

        // Procesar cada item de la entrada
        for (ItemEntradaDTO itemDTO : itemsEntradaDTO) {
            // Vincular el item con la entrada guardada
            itemDTO.setEntradaProducto(EntradaProductoDTO.fromEntity(savedEntrada));

            // Convertir item a entidad
            ItemEntrada item = itemDTO.toEntity();


            // Guardar el item de la entrada
            ItemEntrada savedItem = itemEntradaRepository.save(item);

            // Obtener o actualizar el inventario correspondiente
            Inventario inventario = obtenerOActualizarInventario(itemDTO);

            // Crear un movimiento de inventario asociado
            MovimientoInventario movimiento = new MovimientoInventario();
            movimiento.setInventario(inventario);
            movimiento.setTipoMovimiento(TipoMovimientoEnum.ENTRADA);
            movimiento.setCantidad(itemDTO.getCantidad());
            movimiento.setCantidadAnterior(inventario.getCantidad());
            movimiento.setCantidadActual(inventario.getCantidad() + itemDTO.getCantidad());
            movimiento.setUsuario(entrada.getUsuario()); // Asegúrate de pasar el usuario correctamente
            movimiento.setObservacion("Entrada de producto: " + savedEntrada.getGuiaRemision());
            movimiento.setFecha(itemDTO.getCreatedAt());

            // Actualizar la cantidad en inventario
            inventario.setCantidad(inventario.getCantidad() + itemDTO.getCantidad());
            inventarioRepository.save(inventario);

            // Guardar el movimiento de inventario
            movimientoInventarioRepository.save(movimiento);
        }

        // Retornar la entrada guardada convertida a DTO
        return EntradaProductoDTO.fromEntity(savedEntrada);
    }

    private Inventario obtenerOActualizarInventario(ItemEntradaDTO itemDTO) {
        return inventarioRepository.findByProductoIdAndVariacion(
                itemDTO.getProducto().getId(),
                itemDTO.getInventario().getVariacion().getColor().getId(),
                itemDTO.getInventario().getVariacion().getLongitud().getId(),
                itemDTO.getInventario().getVariacion().getTamano().getId()
        ).orElseGet(() -> {
            Inventario nuevoInventario = new Inventario();
            ItemEntrada item = itemDTO.toEntity();

            nuevoInventario.setProducto(item.getProducto());
            nuevoInventario.setVariacion(item.getInventario().getVariacion());
            nuevoInventario.setCantidad(0L);
            nuevoInventario.setPrecioUnitario(itemDTO.getPrecioUnitario());
            return inventarioRepository.save(nuevoInventario);
        });
    }
    
    

    public EntradaProductoDTO update(Long id, EntradaProductoDTO entradaProductoDTO) {
        EntradaProducto entradaActualizada = entradaProductoDTO.toEntity();

        EntradaProducto entradaExistente = entradaProductoRepository.findById(id)
                .orElseThrow(() -> new DAOException("Entrada no encontrada con ID: " + id));

        entradaExistente.setProveedor(entradaActualizada.getProveedor());
        entradaExistente.setGuiaRemision(entradaActualizada.getGuiaRemision());
        entradaExistente.setTipoEntrada(entradaActualizada.getTipoEntrada());
        entradaExistente.setProcedencia(entradaActualizada.getProcedencia());
        entradaExistente.setFecha(entradaActualizada.getFecha());
        entradaExistente.setObservacion(entradaActualizada.getObservacion());

        return EntradaProductoDTO.fromEntity(entradaProductoRepository.save(entradaExistente));
    }

    public void deleteById(Long id) {
        if (!entradaProductoRepository.existsById(id)) {
            throw new DAOException("Entrada no encontrada con ID: " + id);
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

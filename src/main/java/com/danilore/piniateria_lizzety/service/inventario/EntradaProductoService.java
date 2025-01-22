package com.danilore.piniateria_lizzety.service.inventario;

import com.danilore.piniateria_lizzety.dto.inventario.EntradaProductoDTO;
import com.danilore.piniateria_lizzety.dto.inventario.ItemEntradaDTO;
import com.danilore.piniateria_lizzety.exception.DAOException;
import com.danilore.piniateria_lizzety.model.inventario.EntradaProducto;
import com.danilore.piniateria_lizzety.model.inventario.Inventario;
import com.danilore.piniateria_lizzety.model.inventario.ItemEntrada;
import com.danilore.piniateria_lizzety.model.inventario.MovimientoInventario;
import com.danilore.piniateria_lizzety.model.inventario.enums.TipoMovimientoEnum;
import com.danilore.piniateria_lizzety.model.usuario.Usuario;
import com.danilore.piniateria_lizzety.repository.inventario.EntradaProductoRepository;
import com.danilore.piniateria_lizzety.repository.inventario.InventarioRepository;
import com.danilore.piniateria_lizzety.repository.inventario.ItemEntradaRepository;
import com.danilore.piniateria_lizzety.repository.inventario.MovimientoInventarioRepository;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Autowired
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
        if (itemsEntradaDTO == null || itemsEntradaDTO.isEmpty()) {
            throw new DAOException("Debe proporcionar al menos un item para la entrada.");
        }

        // Convertir DTO a entidad
        EntradaProducto entrada = entradaProductoDTO.toEntity();

        // Validar si la guía de remisión ya existe
        if (entradaProductoRepository.findByGuiaRemision(entrada.getGuiaRemision()).isPresent()) {
            throw new DAOException("La guía de remisión ya está registrada.");
        }

        // Guardar la entrada principal
        EntradaProducto savedEntrada = entradaProductoRepository.save(entrada);

        List<MovimientoInventario> movimientos = new ArrayList<>();
        List<Inventario> inventariosActualizados = new ArrayList<>();

        // Procesar cada item de la entrada
        for (ItemEntradaDTO itemDTO : itemsEntradaDTO) {
            // Vincular el item con la entrada guardada
            itemDTO.setEntradaProducto(EntradaProductoDTO.fromEntity(savedEntrada));

            // Convertir item a entidad
            ItemEntrada  item = itemDTO.toEntity();
            itemEntradaRepository.save(item);

            // Obtener o actualizar el inventario correspondiente
            //Solo se obtiene el inventario por id (si existe el inventario)
            //Luego implemento que cree uno nuevo en caso no exista el inventario
            Inventario inventario = inventarioRepository.findById(itemDTO.getInventario().getId())
                .orElseThrow(() -> new DAOException("Inventario no encontrado con ID: " + itemDTO.getInventario().getId()));

            //Inventario inventario = obtenerOActualizarInventario(itemDTO);

            // Crear y configurar el movimiento
            MovimientoInventario movimiento = crearMovimientoInventario(
                    inventario,
                    entrada.getUsuario(),
                    itemDTO.getCantidad(),
                    entrada.getGuiaRemision(),
                    TipoMovimientoEnum.ENTRADA);

            // Actualizar inventario
            inventario.setCantidad(inventario.getCantidad() + itemDTO.getCantidad());
            inventariosActualizados.add(inventario);
            movimientos.add(movimiento);
        }

        // Guardar movimientos e inventarios en lote
        inventarioRepository.saveAll(inventariosActualizados);
        movimientoInventarioRepository.saveAll(movimientos);

        return EntradaProductoDTO.fromEntity(savedEntrada);
    }

    private MovimientoInventario crearMovimientoInventario(Inventario inventario, Usuario usuario, Long cantidad,
            String guiaRemision, TipoMovimientoEnum tipoMovimiento) {
        MovimientoInventario movimiento = new MovimientoInventario();
        movimiento.setInventario(inventario);
        movimiento.setUsuario(usuario);
        movimiento.setTipoMovimiento(tipoMovimiento);
        movimiento.setCantidad(cantidad);
        movimiento.setCantidadAnterior(inventario.getCantidad());
        movimiento.setCantidadActual(inventario.getCantidad() + cantidad);
        movimiento.setObservacion("Entrada de producto, guía: " + guiaRemision);
        movimiento.setFecha(LocalDateTime.now());
        return movimiento;
    }

    private Inventario obtenerOActualizarInventario(ItemEntradaDTO itemDTO) {
        ItemEntrada item = itemDTO.toEntity();

        return inventarioRepository.findByProductoIdAndVariacion(
                itemDTO.getInventario().getId(),
                itemDTO.getInventario().getVariacion().getColor().getId(),
                itemDTO.getInventario().getVariacion().getLongitud().getId(),
                itemDTO.getInventario().getVariacion().getTamano().getId()).orElseGet(() -> {
                    Inventario nuevoInventario = new Inventario();
                    nuevoInventario.setProducto(item.getProducto());
                    nuevoInventario.setVariacion(item.getInventario().getVariacion());
                    nuevoInventario.setCantidad(0L); // Inicializar en 0
                    nuevoInventario.setPrecioUnitario(itemDTO.getPrecioUnitario());
                    return nuevoInventario;
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

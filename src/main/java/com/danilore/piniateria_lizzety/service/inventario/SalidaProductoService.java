package com.danilore.piniateria_lizzety.service.inventario;

import com.danilore.piniateria_lizzety.dto.inventario.ItemSalidaDTO;
import com.danilore.piniateria_lizzety.dto.inventario.SalidaProductoDTO;
import com.danilore.piniateria_lizzety.exception.DAOException;
import com.danilore.piniateria_lizzety.model.inventario.Inventario;
import com.danilore.piniateria_lizzety.model.inventario.ItemSalida;
import com.danilore.piniateria_lizzety.model.inventario.MovimientoInventario;
import com.danilore.piniateria_lizzety.model.inventario.SalidaProducto;
import com.danilore.piniateria_lizzety.model.inventario.enums.TipoMovimientoEnum;
import com.danilore.piniateria_lizzety.model.usuario.Usuario;
import com.danilore.piniateria_lizzety.repository.inventario.InventarioRepository;
import com.danilore.piniateria_lizzety.repository.inventario.ItemSalidaRepository;
import com.danilore.piniateria_lizzety.repository.inventario.MovimientoInventarioRepository;
import com.danilore.piniateria_lizzety.repository.inventario.SalidaProductoRepository;
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
        Pageable pageable = PageRequest.of(page, size, Sort.by("fecha").descending());
        Page<SalidaProducto> salidaPage = salidaProductoRepository.findAllActivas(pageable);

        return salidaPage.map(SalidaProductoDTO::fromEntity);
    }

    @Transactional
    public Page<ItemSalidaDTO> getById(Long id, int page, int size) {
        SalidaProducto salida = salidaProductoRepository.findByIdActiva(id)
                .orElseThrow(() -> new DAOException("Salida de Producto no encontrada con ID: " + id));

        Pageable pageable = PageRequest.of(page, size, Sort.by("salidaProducto.id").ascending());
        Page<ItemSalida> itemPage = itemSalidaRepository.findBySalidaProductoId(salida.getId(), pageable);
        return itemPage.map(ItemSalidaDTO::fromEntity);
    }

    @Transactional
    public SalidaProductoDTO save(SalidaProductoDTO salidaProductoDTO, List<ItemSalidaDTO> itemsSalidaDTO) {
        if (itemsSalidaDTO == null || itemsSalidaDTO.isEmpty()) {
            throw new DAOException("Debe proporcionar al menos un item para la salida.");
        }

        // Convertir DTO a entidad
        SalidaProducto salida = salidaProductoDTO.toEntity();

        // Validar si la guía de salida ya existe
        if (salidaProductoRepository.findByGuiaSalida(salida.getGuiaSalida()).isPresent()) {
            throw new DAOException("La guia de salida ya está registrado.");
        }

        // Guardar la salida principal
        SalidaProducto savedSalida = salidaProductoRepository.save(salida);

        List<MovimientoInventario> movimientos = new ArrayList<>();
        List<Inventario> inventariosActualizados = new ArrayList<>();

        // Procesar cada item de la salida
        for (ItemSalidaDTO itemDTO : itemsSalidaDTO) {
            // Vincular el item con la salida guardada
            itemDTO.setSalidaProducto(SalidaProductoDTO.fromEntity(savedSalida));

            // Convertir item a entidad
            ItemSalida item = itemDTO.toEntity();
            itemSalidaRepository.save(item);

            // Obtener o actualizar el inventario correspondiente
            // Solo se obtiene el inventario por id (si existe el inventario)
            // Luego implemento que cree uno nuevo en caso no exista el inventario
            Inventario inventario = inventarioRepository.findById(itemDTO.getInventario().getId())
                    .orElseThrow(() -> new DAOException(
                            "Inventario no encontrado con ID: " + itemDTO.getInventario().getId()));

            // Inventario inventario = obtenerOCrearNuevoInventario(itemDTO);

            // Validar que la cantidad en el inventario sea mayor o igual a la cantidad
            // saliente
            if (inventario.getCantidad() >= itemDTO.getCantidad()) {
                // Crear y configurar el movimiento
                MovimientoInventario movimiento = crearMovimientoInventario(
                        inventario,
                        salida.getUsuario(),
                        itemDTO.getCantidad(),
                        salida.getGuiaSalida(),
                        TipoMovimientoEnum.SALIDA);

                // Actualizar inventario
                inventario.setCantidad(inventario.getCantidad() - itemDTO.getCantidad());
                inventariosActualizados.add(inventario);
                movimientos.add(movimiento);
            } else {
                throw new DAOException("Error: La cantidad saliente es mayor al stock");
            }

        }

        // Guardar movimientos e inventarios en lote
        inventarioRepository.saveAll(inventariosActualizados);
        movimientoInventarioRepository.saveAll(movimientos);

        return SalidaProductoDTO.fromEntity(savedSalida);
    }

    private MovimientoInventario crearMovimientoInventario(Inventario inventario, Usuario usuario, Long cantidad,
            String guiaSalida, TipoMovimientoEnum tipoMovimiento) {
        MovimientoInventario movimiento = new MovimientoInventario();
        movimiento.setInventario(inventario);
        movimiento.setUsuario(usuario);
        movimiento.setTipoMovimiento(tipoMovimiento);
        movimiento.setCantidad(cantidad);
        movimiento.setCantidadAnterior(inventario.getCantidad());
        movimiento.setCantidadActual(inventario.getCantidad() - cantidad);
        movimiento.setObservacion("Salida de producto, guía: " + guiaSalida);
        movimiento.setFecha(LocalDateTime.now());
        return movimiento;
    }

    @Transactional
    public void deleteById(Long id) {
        SalidaProducto salida = salidaProductoRepository.findById(id)
                .orElseThrow(() -> new DAOException("Salida no encontrada con ID: " + id));

        List<ItemSalida> items = itemSalidaRepository.findBySalidaProductoId(id);

        List<MovimientoInventario> movimientos = new ArrayList<>();
        List<Inventario> inventariosActualizados = new ArrayList<>();

        for (ItemSalida item : items) {
            Inventario inventario = item.getInventario();

            // Crear movimiento de reversión
            MovimientoInventario movimiento = crearMovimientoInventario(
                    inventario,
                    salida.getUsuario(),
                    +item.getCantidad(),
                    salida.getGuiaSalida(),
                    TipoMovimientoEnum.REVERSION_SALIDA);

            // Actualizar inventario
            inventario.setCantidad(inventario.getCantidad() + item.getCantidad());
            inventariosActualizados.add(inventario);
            movimientos.add(movimiento);
            item.markAsDeleted();
        }

        // Guardar movimientos e inventarios actualizados
        movimientoInventarioRepository.saveAll(movimientos);
        inventarioRepository.saveAll(inventariosActualizados);

        // Marcar la salida como eliminada (soft delete)
        //itemSalidaRepository.deleteAll(items);
        //salidaProductoRepository.deleteById(id);
        salida.markAsDeleted(); // Marca como eliminada (soft delete)
        salidaProductoRepository.save(salida);
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

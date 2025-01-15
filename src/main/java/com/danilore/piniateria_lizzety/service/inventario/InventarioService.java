package com.danilore.piniateria_lizzety.service.inventario;

import com.danilore.piniateria_lizzety.dto.inventario.InventarioDTO;
import com.danilore.piniateria_lizzety.dto.inventario.VariacionesDTO;
import com.danilore.piniateria_lizzety.exception.DAOException;
import com.danilore.piniateria_lizzety.model.inventario.Color;
import com.danilore.piniateria_lizzety.model.inventario.Inventario;
import com.danilore.piniateria_lizzety.model.inventario.Longitud;
import com.danilore.piniateria_lizzety.model.inventario.Tamano;
import com.danilore.piniateria_lizzety.model.inventario.Variaciones;
import com.danilore.piniateria_lizzety.model.producto.Producto;
import com.danilore.piniateria_lizzety.repository.inventario.ColorRepository;
import com.danilore.piniateria_lizzety.repository.inventario.InventarioRepository;
import com.danilore.piniateria_lizzety.repository.inventario.LongitudRepository;
import com.danilore.piniateria_lizzety.repository.inventario.TamanoRepository;
import com.danilore.piniateria_lizzety.repository.inventario.VariacionesRepository;
import com.danilore.piniateria_lizzety.repository.producto.ProductoRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Autowired
    private VariacionesRepository variacionesRepository;
    @Autowired
    private ColorRepository colorRepository;
    @Autowired
    private LongitudRepository longitudRepository;
    @Autowired
    private TamanoRepository tamanoRepository;

    private ProductoRepository productoRepository;

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

        // Verificar y persistir Producto
        if (inventario.getProducto().getId() == null) {
            Producto productoPersistido = productoRepository.save(inventario.getProducto());
            inventario.setProducto(productoPersistido);
        }

        // Configurar relaciones de Variaciones
        Set<Variaciones> variaciones = inventario.getVariaciones();
        if (variaciones != null) {
            for (Variaciones variacion : variaciones) {
                variacion.setInventario(inventario); // Relación bidireccional
            }
        }

        // Guardar Inventario junto con sus Variaciones
        Inventario savedInventario = inventarioRepository.save(inventario);
        return InventarioDTO.fromEntity(savedInventario);
    }

    public InventarioDTO update(Long id, InventarioDTO inventarioDTO) {
        Inventario inventarioExistente = inventarioRepository.findById(id)
                .orElseThrow(() -> new DAOException("Inventario no encontrado con ID: " + id));

        // Actualizar atributos básicos
        inventarioExistente.setProducto(inventarioDTO.getProducto().toEntity());
        inventarioExistente.setPrecioUnitario(inventarioDTO.getPrecioUnitario());
        inventarioExistente.setCantidad(inventarioDTO.getCantidad());

        Map<Long, Color> colores = cargarColoresMap(inventarioDTO.getVariaciones());
        Map<Long, Longitud> longitudes = cargarLongitudesMap(inventarioDTO.getVariaciones());
        Map<Long, Tamano> tamanos = cargarTamanosMap(inventarioDTO.getVariaciones());

        Set<Variaciones> nuevasVariaciones = new HashSet<>();
        for (VariacionesDTO variacionDTO : inventarioDTO.getVariaciones()) {
            Variaciones variacion = obtenerVariacionExistente(
                    inventarioExistente,
                    colores.get(variacionDTO.getColor().getId()),
                    longitudes.get(variacionDTO.getLongitud() != null ? variacionDTO.getLongitud().getId() : null),
                    tamanos.get(variacionDTO.getTamano() != null ? variacionDTO.getTamano().getId() : null));

            if (variacion == null) {
                variacion = new Variaciones(
                        inventarioExistente,
                        colores.get(variacionDTO.getColor().getId()),
                        longitudes.get(variacionDTO.getLongitud() != null ? variacionDTO.getLongitud().getId() : null),
                        tamanos.get(variacionDTO.getTamano() != null ? variacionDTO.getTamano().getId() : null));
                variacion = variacionesRepository.save(variacion);
            }
            nuevasVariaciones.add(variacion);
        }

        // Actualizar las variaciones del inventario
        inventarioExistente.getVariaciones().retainAll(nuevasVariaciones);
        inventarioExistente.getVariaciones().addAll(nuevasVariaciones);

        // Guardar cambios
        Inventario inventarioGuardado = inventarioRepository.save(inventarioExistente);
        return InventarioDTO.fromEntity(inventarioGuardado);
    }

    // Métodos auxiliares optimizados
    private Map<Long, Color> cargarColoresMap(Set<VariacionesDTO> variaciones) {
        List<Long> ids = variaciones.stream().map(v -> v.getColor().getId()).distinct().toList();
        return colorRepository.findAllById(ids).stream().collect(Collectors.toMap(Color::getId, c -> c));
    }

    private Map<Long, Longitud> cargarLongitudesMap(Set<VariacionesDTO> variaciones) {
        List<Long> ids = variaciones.stream()
                .filter(v -> v.getLongitud() != null)
                .map(v -> v.getLongitud().getId())
                .distinct().toList();
        return longitudRepository.findAllById(ids).stream().collect(Collectors.toMap(Longitud::getId, l -> l));
    }

    private Map<Long, Tamano> cargarTamanosMap(Set<VariacionesDTO> variaciones) {
        List<Long> ids = variaciones.stream()
                .filter(v -> v.getTamano() != null)
                .map(v -> v.getTamano().getId())
                .distinct().toList();
        return tamanoRepository.findAllById(ids).stream().collect(Collectors.toMap(Tamano::getId, t -> t));
    }

    private Variaciones obtenerVariacionExistente(Inventario inventario, Color color, Longitud longitud,
            Tamano tamano) {
        return variacionesRepository.findByAttributes(inventario, color, longitud, tamano).orElse(null);
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

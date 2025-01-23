package com.danilore.piniateria_lizzety.service.inventario;

import com.danilore.piniateria_lizzety.dto.inventario.ColorDTO;
import com.danilore.piniateria_lizzety.dto.inventario.InventarioDTO;
import com.danilore.piniateria_lizzety.dto.inventario.LongitudDTO;
import com.danilore.piniateria_lizzety.dto.inventario.TamanoDTO;
import com.danilore.piniateria_lizzety.exception.DAOException;
import com.danilore.piniateria_lizzety.model.inventario.Color;
import com.danilore.piniateria_lizzety.model.inventario.Inventario;
import com.danilore.piniateria_lizzety.model.inventario.Longitud;
import com.danilore.piniateria_lizzety.model.inventario.MovimientoInventario;
import com.danilore.piniateria_lizzety.model.inventario.Tamano;
import com.danilore.piniateria_lizzety.model.inventario.Variacion;
import com.danilore.piniateria_lizzety.model.inventario.enums.TipoMovimientoEnum;
import com.danilore.piniateria_lizzety.model.usuario.Usuario;
import com.danilore.piniateria_lizzety.repository.inventario.InventarioRepository;
import com.danilore.piniateria_lizzety.repository.inventario.MovimientoInventarioRepository;
import com.danilore.piniateria_lizzety.repository.inventario.VariacionRepository;
import com.danilore.piniateria_lizzety.repository.usuario.UsuarioRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private VariacionRepository variacionRepository;

    @Autowired
    private MovimientoInventarioRepository movimientoInventarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<InventarioDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Inventario> inventarioPage = inventarioRepository.findAll(pageable);

        return inventarioPage.map(InventarioDTO::fromEntity);
    }

    public InventarioDTO getById(Long id) {
        Inventario inventario = inventarioRepository.findById(id)
                .orElseThrow(() -> new DAOException("Inventario no encontrado con ID: " + id));
        return InventarioDTO.fromEntity(inventario);
    }

    @Transactional
    public InventarioDTO save(InventarioDTO inventarioDTO) {
        if (inventarioDTO.getVariacion() == null) {
            throw new DAOException("La variación no puede ser nula.");
        }

        // Verificar o crear variaciones
        Variacion variacion = findOrCreateVariacion(
                inventarioDTO.getVariacion().getColor(),
                inventarioDTO.getVariacion().getLongitud(),
                inventarioDTO.getVariacion().getTamano());

        // Validar existencia del inventario antes de crearlo
        boolean exists = inventarioRepository.existsByProductoIdAndVariacionId(
                inventarioDTO.getProducto().getId(),
                variacion.getId());

        if (exists) {
            throw new DAOException("El inventario ya existe para el producto y la variación proporcionados.");
        }

        // Crear el registro de inventario
        Inventario inventario = inventarioDTO.toEntity();
        inventario.setVariacion(variacion); // Asignar la variación creada o existente

        Inventario savedInventario = inventarioRepository.save(inventario);

        return InventarioDTO.fromEntity(savedInventario);
    }

    private Variacion findOrCreateVariacion(ColorDTO colorDTO, LongitudDTO longitudDTO, TamanoDTO tamanoDTO) {
        Color color = (colorDTO != null) ? colorDTO.toEntity() : null;
        Longitud longitud = (longitudDTO != null) ? longitudDTO.toEntity() : null;
        Tamano tamano = (tamanoDTO != null) ? tamanoDTO.toEntity() : null;

        Optional<Variacion> existingVariacion = variacionRepository.findByColorAndLongitudAndTamano(color, longitud,
                tamano);

        if (existingVariacion.isPresent()) {
            return existingVariacion.get();
        }

        // Crear una nueva variación si no existe
        Variacion nuevaVariacion = new Variacion();
        nuevaVariacion.setColor(color);
        nuevaVariacion.setLongitud(longitud);
        nuevaVariacion.setTamano(tamano);

        return variacionRepository.save(nuevaVariacion);
    }

    public InventarioDTO update(Long id, InventarioDTO inventarioDTO, Long usuarioId) {
        if (inventarioDTO.getVariacion() == null) {
            throw new DAOException("La variación no puede ser nula.");
        }

        // Buscar el inventario existente
        Inventario inventarioExistente = inventarioRepository.findById(id)
                .orElseThrow(() -> new DAOException("Inventario no encontrado con ID: " + id));

        // Verificar o crear variación
        Variacion variacion = findOrCreateVariacion(
                inventarioDTO.getVariacion().getColor(),
                inventarioDTO.getVariacion().getLongitud(),
                inventarioDTO.getVariacion().getTamano());

        // Validar que no haya duplicados
        boolean exists = inventarioRepository.existsByProductoIdAndVariacionId(
                inventarioDTO.getProducto().getId(),
                variacion.getId());

        if (exists && !inventarioExistente.getVariacion().getId().equals(variacion.getId())) {
            throw new DAOException("El inventario ya existe para el producto y la variación proporcionados.");
        }

        // Actualizar atributos básicos
        inventarioExistente.setProducto(inventarioDTO.getProducto().toEntity());
        inventarioExistente.setPrecioUnitario(inventarioDTO.getPrecioUnitario());

        MovimientoInventario movimiento = new MovimientoInventario();

        Usuario usuario = usuarioRepository.getById(usuarioId);

        if (inventarioExistente.getCantidad() != inventarioDTO.getCantidad()) {
            // Crear y configurar el movimiento
            movimiento.setInventario(inventarioExistente);
            movimiento.setUsuario(usuario);
            movimiento.setTipoMovimiento(TipoMovimientoEnum.AJUSTE);
            movimiento.setCantidad(inventarioDTO.getCantidad());
            movimiento.setCantidadAnterior(inventarioExistente.getCantidad());
            movimiento.setCantidadActual(inventarioDTO.getCantidad());
            movimiento.setObservacion("Ajuste de Inventario, se actualizo el stock directamente");
            movimiento.setFecha(LocalDateTime.now());
        }

        inventarioExistente.setCantidad(inventarioDTO.getCantidad());
        inventarioExistente.setVariacion(variacion); // Actualizar la relación con la nueva variación

        // Guardar los cambios
        Inventario inventarioGuardado = inventarioRepository.save(inventarioExistente);
        movimientoInventarioRepository.save(movimiento);
        return InventarioDTO.fromEntity(inventarioGuardado);
    }


    // private Usuario obtenerUsuarioActual() {
    //     // Obtener la autenticación actual desde el contexto de seguridad
    //     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    //     if (authentication == null || !authentication.isAuthenticated()) {
    //         throw new DAOException("No se pudo obtener el usuario autenticado.");
    //     }

    //     // Suponiendo que el usuario autenticado es una instancia de un usuario
    //     // personalizado
    //     String username = authentication.getName(); // Obtener el nombre de usuario

    //     // Buscar el usuario en tu base de datos (asegúrate de tener un repositorio para
    //     // usuarios)
    //     return usuarioRepository.findByUsername(username)
    //             .orElseThrow(() -> new DAOException("Usuario no encontrado: " + username));
    // }

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

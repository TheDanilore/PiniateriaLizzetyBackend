package com.danilore.piniateria_lizzety.service.producto;

import com.danilore.piniateria_lizzety.dto.producto.ProductoDTO;
import com.danilore.piniateria_lizzety.exception.DAOException;
import com.danilore.piniateria_lizzety.exception.ResourceNotFoundException;
import com.danilore.piniateria_lizzety.model.producto.Producto;
import com.danilore.piniateria_lizzety.repository.producto.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public Page<ProductoDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Producto> proveedorPage = productoRepository.findAll(pageable);

        return proveedorPage.map(ProductoDTO::fromEntity);
    }

    public ProductoDTO getById(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new DAOException("Producto no encontrado con ID: " + id));
        return ProductoDTO.fromEntity(producto);
    }

    public ProductoDTO save(ProductoDTO productoDTO) {
        Producto producto = productoDTO.toEntity();

        if (productoRepository.findByNombre(producto.getNombre()).isPresent()) {
            throw new DAOException("El nombre ya está registrado.");
        }

        Producto productoGuardado = productoRepository.save(producto);
        return ProductoDTO.fromEntity(productoGuardado);
    }

    public ProductoDTO update(Long id, ProductoDTO productoDTO) {
        Producto productoActualizado = productoDTO.toEntity();

        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));

        productoExistente.setNombre(productoActualizado.getNombre());
        productoExistente.setDescripcion(productoActualizado.getDescripcion());

        if (productoActualizado.getCategoriaProducto() != null) {
            productoExistente.setCategoriaProducto(productoActualizado.getCategoriaProducto());
        }

        if (productoActualizado.getUnidadMedida() != null) {
            productoExistente.setUnidadMedida(productoActualizado.getUnidadMedida());
        }
        if (productoActualizado.getProveedor() != null) {
            productoExistente.setProveedor(productoActualizado.getProveedor());
        }
        if (productoActualizado.getUbicacion() != null) {
            productoExistente.setUbicacion(productoActualizado.getUbicacion());
        }

        return ProductoDTO.fromEntity(productoRepository.save(productoExistente));

    }

    public void deleteById(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Producto no encontrado con ID: " + id);
        }
        productoRepository.deleteById(id);
    }

    // Listar por nombre, categoria o ID
    public Page<ProductoDTO> buscarPorCriterio(String criterio, int page, int size) {
        if (criterio == null || criterio.isBlank()) {
            throw new IllegalArgumentException("El criterio de búsqueda no puede estar vacío");
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("nombre").ascending());
        Page<Producto> proveedorPage = productoRepository.buscarPorCriterio(criterio, pageable);
        return proveedorPage.map(ProductoDTO::fromEntity);
    }
}

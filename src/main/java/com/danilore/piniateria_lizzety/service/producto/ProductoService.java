package com.danilore.piniateria_lizzety.service.producto;

import com.danilore.piniateria_lizzety.dto.producto.ProductoDTO;
import com.danilore.piniateria_lizzety.exception.ResourceNotFoundException;
import com.danilore.piniateria_lizzety.model.producto.Producto;
import com.danilore.piniateria_lizzety.repository.producto.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public Page<ProductoDTO> listarTodos(Pageable pageable) {
        return productoRepository.findAll(pageable).map(ProductoDTO::fromEntity);
    }

    public ProductoDTO buscarPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));
        return ProductoDTO.fromEntity(producto);
    }

    public ProductoDTO guardar(ProductoDTO productoDTO) {
        Producto producto = productoDTO.toEntity();
        Producto productoGuardado = productoRepository.save(producto);
        return ProductoDTO.fromEntity(productoGuardado);
    }

    public ProductoDTO editar(Long id, ProductoDTO productoDTO) {
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));

        productoExistente.setNombre(productoDTO.getNombre());
        productoExistente.setDescripcion(productoDTO.getDescripcion());
        if (productoDTO.getCategoriaProducto() != null) {
            productoExistente.setCategoriaProducto(productoDTO.getCategoriaProducto().toEntity());
        }
        if (productoDTO.getUnidadMedida() != null) {
            productoExistente.setUnidadMedida(productoDTO.getUnidadMedida().toEntity());
        }
        if (productoDTO.getProveedor() != null) {
            productoExistente.setProveedor(productoDTO.getProveedor().toEntity());
        }
        if (productoDTO.getUbicacion() != null) {
            productoExistente.setUbicacion(productoDTO.getUbicacion().toEntity());
        }
        productoExistente.setEstado(productoDTO.getEstado());
        productoExistente.setUpdatedAt(productoDTO.getUpdatedAt());

        Producto productoEditado = productoRepository.save(productoExistente);
        return ProductoDTO.fromEntity(productoEditado);
    }

    public void eliminar(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Producto no encontrado con ID: " + id);
        }
        productoRepository.deleteById(id);
    }
}

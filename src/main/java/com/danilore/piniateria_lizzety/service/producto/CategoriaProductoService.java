package com.danilore.piniateria_lizzety.service.producto;

import com.danilore.piniateria_lizzety.dto.producto.CategoriaProductoDTO;
import com.danilore.piniateria_lizzety.exception.ResourceNotFoundException;
import com.danilore.piniateria_lizzety.model.producto.CategoriaProducto;
import com.danilore.piniateria_lizzety.repository.producto.CategoriaProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoriaProductoService {

    @Autowired
    private CategoriaProductoRepository categoriaProductoRepository;

    public Page<CategoriaProductoDTO> listarTodos(Pageable pageable) {
        return categoriaProductoRepository.findAll(pageable).map(CategoriaProductoDTO::fromEntity);
    }

    public CategoriaProductoDTO buscarPorId(Long id) {
        CategoriaProducto categoriaProducto = categoriaProductoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría de producto no encontrada con ID: " + id));
        return CategoriaProductoDTO.fromEntity(categoriaProducto);
    }

    public CategoriaProductoDTO guardar(CategoriaProductoDTO categoriaProductoDTO) {
        CategoriaProducto categoriaProducto = categoriaProductoDTO.toEntity();
        CategoriaProducto categoriaGuardada = categoriaProductoRepository.save(categoriaProducto);
        return CategoriaProductoDTO.fromEntity(categoriaGuardada);
    }

    public CategoriaProductoDTO editar(Long id, CategoriaProductoDTO categoriaProductoDTO) {
        CategoriaProducto categoriaExistente = categoriaProductoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría de producto no encontrada con ID: " + id));

        categoriaExistente.setDescripcion(categoriaProductoDTO.getDescripcion());
        categoriaExistente.setUpdatedAt(categoriaProductoDTO.getUpdatedAt());

        CategoriaProducto categoriaEditada = categoriaProductoRepository.save(categoriaExistente);
        return CategoriaProductoDTO.fromEntity(categoriaEditada);
    }

    public void eliminar(Long id) {
        if (!categoriaProductoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Categoría de producto no encontrada con ID: " + id);
        }
        categoriaProductoRepository.deleteById(id);
    }
}

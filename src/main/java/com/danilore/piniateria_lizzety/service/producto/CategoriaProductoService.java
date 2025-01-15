package com.danilore.piniateria_lizzety.service.producto;

import com.danilore.piniateria_lizzety.dto.producto.CategoriaProductoDTO;
import com.danilore.piniateria_lizzety.exception.DAOException;
import com.danilore.piniateria_lizzety.model.producto.CategoriaProducto;
import com.danilore.piniateria_lizzety.repository.producto.CategoriaProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoriaProductoService {

    @Autowired
    private CategoriaProductoRepository categoriaProductoRepository;

    public Page<CategoriaProductoDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CategoriaProducto> categoriaPage = categoriaProductoRepository.findAll(pageable);

        return categoriaPage.map(CategoriaProductoDTO::fromEntity);
    }

    public CategoriaProductoDTO getById(Long id) {
        CategoriaProducto categoriaProducto = categoriaProductoRepository.findById(id)
                .orElseThrow(() -> new DAOException("Categoría de producto no encontrada con ID: " + id));
        return CategoriaProductoDTO.fromEntity(categoriaProducto);
    }

    public CategoriaProductoDTO save(CategoriaProductoDTO categoriaProductoDTO) {
        CategoriaProducto categoriaProducto = categoriaProductoDTO.toEntity();

        if (categoriaProductoRepository.findByDescripcion(categoriaProducto.getDescripcion()).isPresent()) {
            throw new DAOException("La descripción ya está registrado.");
        }

        CategoriaProducto savedCategoria = categoriaProductoRepository.save(categoriaProducto);
        return CategoriaProductoDTO.fromEntity(savedCategoria);
    }

    public CategoriaProductoDTO update(Long id, CategoriaProductoDTO categoriaProductoDTO) {
        CategoriaProducto categoriaActualizado = categoriaProductoDTO.toEntity();
        
        CategoriaProducto categoriaExistente = categoriaProductoRepository.findById(id)
                .orElseThrow(() -> new DAOException("Categoría de producto no encontrada con ID: " + id));

        categoriaExistente.setDescripcion(categoriaActualizado.getDescripcion());

        return CategoriaProductoDTO.fromEntity(categoriaProductoRepository.save(categoriaExistente));
    }

    public void deleteById(Long id) {
        if (!categoriaProductoRepository.existsById(id)) {
            throw new DAOException("Categoría de producto no encontrada con ID: " + id);
        }
        categoriaProductoRepository.deleteById(id);
    }

        // Listar por ruc,razon social o ID
    public Page<CategoriaProductoDTO> buscarPorCriterio(String criterio, int page, int size) {
        if (criterio == null || criterio.isBlank()) {
            throw new DAOException("El criterio de búsqueda no puede estar vacío");
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("descripcion").ascending());
        Page<CategoriaProducto> proveedorPage = categoriaProductoRepository.buscarPorCriterio(criterio, pageable);
        return proveedorPage.map(CategoriaProductoDTO::fromEntity);
    }
}

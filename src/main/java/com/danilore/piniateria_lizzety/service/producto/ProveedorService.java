package com.danilore.piniateria_lizzety.service.producto;

import com.danilore.piniateria_lizzety.dto.producto.ProveedorDTO;
import com.danilore.piniateria_lizzety.exception.DAOException;
import com.danilore.piniateria_lizzety.model.producto.Proveedor;
import com.danilore.piniateria_lizzety.repository.producto.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public Page<ProveedorDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Proveedor> proveedorPage = proveedorRepository.findAll(pageable);

        return proveedorPage.map(ProveedorDTO::fromEntity);
    }

    public ProveedorDTO getById(Long id) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new DAOException("Proveedor no encontrado con ID: " + id));
        return ProveedorDTO.fromEntity(proveedor);
    }

    public ProveedorDTO save(ProveedorDTO proveedorDTO) {
        Proveedor proveedor = proveedorDTO.toEntity();

        if (proveedorRepository.findByRuc(proveedor.getRuc()).isPresent()) {
            throw new DAOException("El ruc ya está registrado.");
        }
        if (proveedorRepository.findByRazonSocial(proveedor.getRazonSocial()).isPresent()) {
            throw new DAOException("La razon social ya está registrado.");
        }

        Proveedor savedProveedor = proveedorRepository.save(proveedor);
        return ProveedorDTO.fromEntity(savedProveedor);
    }

    public ProveedorDTO update(Long id, ProveedorDTO proveedorDTO) {
        Proveedor proveedorActualizado = proveedorDTO.toEntity();

        Proveedor proveedorExistente = proveedorRepository.findById(id)
                .orElseThrow(() -> new DAOException("Proveedor no encontrado con ID: " + id));

        proveedorExistente.setRuc(proveedorActualizado.getRuc());
        proveedorExistente.setRazonSocial(proveedorActualizado.getRazonSocial());
        proveedorExistente.setDireccion(proveedorActualizado.getDireccion());
        proveedorExistente.setTelefono(proveedorActualizado.getTelefono());

        return ProveedorDTO.fromEntity(proveedorRepository.save(proveedorExistente));
    }

    public void deleteById(Long id) {
        if (!proveedorRepository.existsById(id)) {
            throw new DAOException("Proveedor no encontrado con ID: " + id);
        }
        proveedorRepository.deleteById(id);
    }

    // Listar por ruc,razon social o ID
    public Page<ProveedorDTO> buscarPorCriterio(String criterio, int page, int size) {
        if (criterio == null || criterio.isBlank()) {
            throw new DAOException("El criterio de búsqueda no puede estar vacío");
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("razonSocial").ascending());
        Page<Proveedor> proveedorPage = proveedorRepository.buscarPorCriterio(criterio, pageable);
        return proveedorPage.map(ProveedorDTO::fromEntity);
    }
}

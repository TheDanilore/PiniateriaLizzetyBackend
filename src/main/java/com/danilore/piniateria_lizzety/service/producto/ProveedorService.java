package com.danilore.piniateria_lizzety.service.producto;

import com.danilore.piniateria_lizzety.dto.producto.ProveedorDTO;
import com.danilore.piniateria_lizzety.exception.ResourceNotFoundException;
import com.danilore.piniateria_lizzety.model.producto.Proveedor;
import com.danilore.piniateria_lizzety.repository.producto.ProveedorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public Page<ProveedorDTO> listarTodos(Pageable pageable) {
        return proveedorRepository.findAll(pageable).map(ProveedorDTO::fromEntity);
    }

    public ProveedorDTO buscarPorId(Long id) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con ID: " + id));
        return ProveedorDTO.fromEntity(proveedor);
    }

    public ProveedorDTO guardar(ProveedorDTO proveedorDTO) {
        Proveedor proveedor = proveedorDTO.toEntity();
        Proveedor proveedorGuardado = proveedorRepository.save(proveedor);
        return ProveedorDTO.fromEntity(proveedorGuardado);
    }

    public ProveedorDTO editar(Long id, ProveedorDTO proveedorDTO) {
        Proveedor proveedorExistente = proveedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con ID: " + id));

        proveedorExistente.setRuc(proveedorDTO.getRuc());
        proveedorExistente.setRazonSocial(proveedorDTO.getRazonSocial());
        proveedorExistente.setDireccion(proveedorDTO.getDireccion());
        proveedorExistente.setTelefono(proveedorDTO.getTelefono());
        proveedorExistente.setEstado(proveedorDTO.getEstado());
        proveedorExistente.setUpdatedAt(proveedorDTO.getUpdatedAt());

        Proveedor proveedorEditado = proveedorRepository.save(proveedorExistente);
        return ProveedorDTO.fromEntity(proveedorEditado);
    }

    public void eliminar(Long id) {
        if (!proveedorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Proveedor no encontrado con ID: " + id);
        }
        proveedorRepository.deleteById(id);
    }
}

package com.danilore.piniateria_lizzety.service.persona;

import com.danilore.piniateria_lizzety.exception.DAOException;
import com.danilore.piniateria_lizzety.model.persona.Persona;
import com.danilore.piniateria_lizzety.repository.persona.PersonaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public Page<Persona> listarTodas(Pageable pageable) {
        return personaRepository.findAll(pageable);
    }

    public Persona buscarPorId(Long id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new DAOException("La Persona con ID: " + id + " no existe"));
    }

    public Persona guardar(Persona persona) {
        if (personaRepository.findByCorreo(persona.getCorreo()).isPresent()) {
            throw new DAOException("El correo ya está registrado.");
        }
        if (personaRepository.findByTelefono(persona.getTelefono()).isPresent()) {
            throw new DAOException("La persona ya está registrada.");

        }

        return personaRepository.save(persona);
    }

    public Persona editar(Long id, Persona personaActualizada) {
        Persona personaExistente = personaRepository.findById(id)
                .orElseThrow(() -> new DAOException("Persona no encontrado"));

        personaExistente.setNombres(personaActualizada.getNombres());
        personaExistente.setApellidos(personaActualizada.getApellidos());
        personaExistente.setDireccion(personaActualizada.getDireccion());
        personaExistente.setTelefono(personaActualizada.getTelefono());
        personaExistente.setTipoDocumentoIdentidad(personaActualizada.getTipoDocumentoIdentidad());
        personaExistente.setNumeroDocumento(personaActualizada.getNumeroDocumento());
        personaExistente.setDepartamento(personaActualizada.getDepartamento());
        personaExistente.setProvincia(personaActualizada.getProvincia());
        personaExistente.setDistrito(personaActualizada.getDistrito());
        personaExistente.setCorreo(personaActualizada.getCorreo());
        personaExistente.setFechaNacimiento(personaActualizada.getFechaNacimiento());
        personaExistente.setGenero(personaActualizada.getGenero());
        personaExistente.setLugarNacimiento(personaActualizada.getLugarNacimiento());
        return personaRepository.save(personaExistente);
    }



    public void eliminarPorId(Long id) {
        personaRepository.deleteById(id);
    }

    // Métodos personalizados
    public Persona BuscarPorIdConTipoDocumento(Long id) {
        return personaRepository.findByIdConTipoDocumento(id)
                .orElseThrow(() -> new DAOException("Persona no encontrado"));
    }

    public Persona buscarPorCorreo(String correo) {
        return personaRepository.findByCorreo(correo)
                .orElseThrow(() -> new DAOException("Persona no encontrado"));
    }

    public List<Persona> buscarPorNombres(String nombres) {
        return personaRepository.findByNombres(nombres);
    }

    public List<Persona> buscarPorApellidos(String apellidos) {
        return personaRepository.findByApellidos(apellidos);
    }

    public List<Persona> buscarPorNombresYApellidos(String nombres, String apellidos) {
        return personaRepository.findByNombresAndApellidos(nombres, apellidos);
    }

    public List<Persona> buscarPorFragmentoDeNombres(String fragmento) {
        return personaRepository.findByNombresContaining(fragmento);
    }

    public List<Persona> buscarPorFragmentoDeApellidos(String fragmento) {
        return personaRepository.findByApellidosContaining(fragmento);
    }

    public List<Persona> listarOrdenadasPorNombres() {
        return personaRepository.findAll(Sort.by("nombres").ascending());
    }

    public List<Persona> listarOrdenadasPorApellidos() {
        return personaRepository.findAll(Sort.by(Sort.Direction.ASC, "apellidos"));
    }
}

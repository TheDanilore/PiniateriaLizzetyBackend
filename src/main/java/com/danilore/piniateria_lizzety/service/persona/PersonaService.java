package com.danilore.piniateria_lizzety.service.persona;

import com.danilore.piniateria_lizzety.dto.persona.PersonaDTO;
import com.danilore.piniateria_lizzety.exception.DAOException;
import com.danilore.piniateria_lizzety.model.persona.Persona;
import com.danilore.piniateria_lizzety.repository.persona.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public Page<PersonaDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Persona> personaPage = personaRepository.findAll(pageable);

        return personaPage.map(PersonaDTO::fromEntity);
    }

    public PersonaDTO getById(Long id) {
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrado"));
        return PersonaDTO.fromEntity(persona);
    }

    public PersonaDTO save(PersonaDTO personaDTO) {
        Persona persona = personaDTO.toEntity();

        if (personaRepository.findByCorreo(persona.getCorreo()).isPresent()) {
            throw new DAOException("El correo ya está registrado.");
        }
        if (personaRepository.findByTelefono(persona.getTelefono()).isPresent()) {
            throw new DAOException("El telefono ya está registrado.");

        }

        Persona savedPersona = personaRepository.save(persona);
        return PersonaDTO.fromEntity(savedPersona);
    }

    public PersonaDTO update(Long id, PersonaDTO personaDTO) {
        // Convertir el DTO en una entidad
        Persona personaActualizada = personaDTO.toEntity();

        Persona personaExistente = personaRepository.findById(id)
                .orElseThrow(() -> new DAOException("Persona no encontrado con ID: " + id));

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

        // Convertir la entidad actualizada nuevamente a DTO y devolverla
        return PersonaDTO.fromEntity(personaRepository.save(personaExistente));
    }

    public void deleteById(Long id) {
        if (!personaRepository.existsById(id)) {
            throw new DAOException("Persona no encontrado");
        }
        personaRepository.deleteById(id);
    }

    // Listar usuario por nombres, apellidos, numeroDocumento, correo o ID
    public Page<PersonaDTO> buscarPorCriterio(String criterio, int page, int size) {
        if (criterio == null || criterio.isBlank()) {
            throw new IllegalArgumentException("El criterio de búsqueda no puede estar vacío");
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by("apellidos").ascending());
        Page<Persona> personaPage = personaRepository.buscarPorCriterio(criterio, pageable);
        return personaPage.map(PersonaDTO::fromEntity);
    }

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

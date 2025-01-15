package com.danilore.piniateria_lizzety.controller.persona;

import com.danilore.piniateria_lizzety.dto.persona.PersonaDTO;
import com.danilore.piniateria_lizzety.model.persona.Persona;
import com.danilore.piniateria_lizzety.service.persona.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/api/personas") // Indica la URL base para todos los métodos
public class PersonaController { // Clase controlador para la entidad Persona

    @Autowired // Inyección de dependencias
    private PersonaService personaService; // Servicio para la entidad Persona

    @GetMapping
    public ResponseEntity<Page<PersonaDTO>> getAll(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(personaService.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(personaService.getById(id));
    }

    @PostMapping
    public ResponseEntity<PersonaDTO> save(@RequestBody PersonaDTO personaDTO) {
        return ResponseEntity.ok(personaService.save(personaDTO));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<PersonaDTO> update(@PathVariable Long id,
            @RequestBody PersonaDTO personaDTO) {
        return ResponseEntity.ok(personaService.update(id, personaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        personaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Listar usuario por nombres, apellidos, numeroDocumento, correo o ID
    @GetMapping("/buscar")
    public ResponseEntity<Page<PersonaDTO>> buscarPorCriterio(
            @RequestParam String criterio,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(personaService.buscarPorCriterio(criterio, page, size));
    }

    @GetMapping("/idtipodocumento/{idTipoDocumento}")
    public PersonaDTO BuscarPorIdConTipoDocumento(@PathVariable Long idTipoDocumento) {
        Persona persona = personaService.BuscarPorIdConTipoDocumento(idTipoDocumento);
        return PersonaDTO.fromEntity(persona); // Retorna la persona encontrada
    }

    // Métodos personalizados
    @GetMapping("/correo/{correo}")
    public PersonaDTO buscarPorCorreo(@PathVariable String correo) { // Método para buscar una persona por su correo
        Persona persona = personaService.buscarPorCorreo(correo); // Busca la persona por su correo
        return PersonaDTO.fromEntity(persona); // Retorna la persona encontrada
    }

    @GetMapping("/nombres/{nombres}")
    public List<PersonaDTO> buscarPorNombres(@PathVariable String nombres) {
        return personaService.buscarPorNombres(nombres).stream()
                .map(PersonaDTO::fromEntity) // Convertir cada Usuario a UsuarioDTO
                .collect(Collectors.toList()); // Retorna la lista de personas encontradas
    }

    @GetMapping("/apellidos/{apellidos}")
    public List<PersonaDTO> buscarPorApellidos(@PathVariable String apellidos) { // Método para buscar personas por su
        return personaService.buscarPorApellidos(apellidos).stream()
                .map(PersonaDTO::fromEntity) // Convertir cada Usuario a UsuarioDTO
                .collect(Collectors.toList()); // Retorna la lista de personas encontradas
    }

    @GetMapping("/nombres/{nombres}/apellidos/{apellidos}")
    public List<PersonaDTO> buscarPorNombresYApellidos(@PathVariable String nombres, @PathVariable String apellidos) {
        return personaService.buscarPorNombresYApellidos(nombres, apellidos).stream()
                .map(PersonaDTO::fromEntity) // Convertir cada Usuario a UsuarioDTO
                .collect(Collectors.toList()); // Retorna la lista de personas encontradas
    }

    @GetMapping("/nombres/fragmento/{fragmento}")
    public List<PersonaDTO> buscarPorFragmentoDeNombres(@PathVariable String fragmento) {
        return personaService.buscarPorFragmentoDeNombres(fragmento).stream()
                .map(PersonaDTO::fromEntity) // Convertir cada Usuario a UsuarioDTO
                .collect(Collectors.toList()); // Retorna la lista de personas encontradas
    }

    @GetMapping("/apellidos/fragmento/{fragmento}")
    public List<PersonaDTO> buscarPorFragmentoDeApellidos(@PathVariable String fragmento) {
        return personaService.buscarPorFragmentoDeApellidos(fragmento).stream()
                .map(PersonaDTO::fromEntity) // Convertir cada Usuario a UsuarioDTO
                .collect(Collectors.toList()); // Retorna la lista de personas encontradas
    }

    @GetMapping("/ordenadas/nombres")
    public List<PersonaDTO> listarOrdenadasPorNombres() {
        return personaService.listarOrdenadasPorNombres().stream()
                .map(PersonaDTO::fromEntity) // Convertir cada Usuario a UsuarioDTO
                .collect(Collectors.toList()); // Retorna la lista de personas ordenadas por nombre
    }

    @GetMapping("/ordenadas/apellidos")
    public List<PersonaDTO> listarOrdenadasPorApellidos() {
        return personaService.listarOrdenadasPorApellidos().stream()
                .map(PersonaDTO::fromEntity) // Convertir cada Usuario a UsuarioDTO
                .collect(Collectors.toList()); // Retorna la lista de personas ordenadas por apellido
    }
}

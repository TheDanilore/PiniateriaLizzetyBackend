package com.danilore.piniateria_lizzety.controller.persona;

import com.danilore.piniateria_lizzety.dto.persona.TipoDocumentoIdentidadDTO;
import com.danilore.piniateria_lizzety.model.persona.TipoDocumentoIdentidad;
import com.danilore.piniateria_lizzety.service.persona.TipoDocumentoIdentidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/api/tipodocumentoidentidad")
public class TipoDocumentoIdentidadController {

    @Autowired // Inyección de dependencias
    private TipoDocumentoIdentidadService tipoDocumentoIdentidadService;

    @GetMapping
    public ResponseEntity<Page<TipoDocumentoIdentidadDTO>> getAll(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(tipoDocumentoIdentidadService.getAll(page, size));
    }

    // Buscar un tipo de documento de identidad por ID
    @GetMapping("/{id}")
    public ResponseEntity<TipoDocumentoIdentidadDTO> getById(@PathVariable String id) {
        return ResponseEntity.ok(tipoDocumentoIdentidadService.getById(id));
    }

    // Guardar un nuevo tipo de documento de identidad
    @PostMapping
    public ResponseEntity<TipoDocumentoIdentidadDTO> save(@RequestBody TipoDocumentoIdentidadDTO tipoDocumentoIdentidadDTO) {
        return ResponseEntity.ok(tipoDocumentoIdentidadService.save(tipoDocumentoIdentidadDTO));
    }

    // Editar un tipo de documento de identidad existente
    @PutMapping("/editar/{id}")
    public ResponseEntity<TipoDocumentoIdentidadDTO> update(@PathVariable String id,
            @RequestBody TipoDocumentoIdentidadDTO tipoDocumentoIdentidadDTO) {
        return ResponseEntity.ok(tipoDocumentoIdentidadService.update(id, tipoDocumentoIdentidadDTO));
    }

    // Eliminar un tipo de documento de identidad
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        tipoDocumentoIdentidadService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar-abreviatura")
    public TipoDocumentoIdentidadDTO buscarPorAbreviatura(@RequestParam String abreviatura) {
        TipoDocumentoIdentidad tipoDocumentoIdentidad = tipoDocumentoIdentidadService.buscarPorAbreviatura(abreviatura);
        return TipoDocumentoIdentidadDTO.fromEntity(tipoDocumentoIdentidad);
    }

    @GetMapping("/buscar-descripcion")
    public TipoDocumentoIdentidadDTO buscarPorDescripcion(@RequestParam String descripcion) {
        TipoDocumentoIdentidad tipoDocumentoIdentidad = tipoDocumentoIdentidadService.buscarPorDescripcion(descripcion);
        return TipoDocumentoIdentidadDTO.fromEntity(tipoDocumentoIdentidad);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<TipoDocumentoIdentidadDTO>> buscarPorCriterio(
            @RequestParam String criterio,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(tipoDocumentoIdentidadService.buscarPorCriterio(criterio, page, size));
    }

}

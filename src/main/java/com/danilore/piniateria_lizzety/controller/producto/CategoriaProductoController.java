package com.danilore.piniateria_lizzety.controller.producto;

import com.danilore.piniateria_lizzety.dto.producto.CategoriaProductoDTO;
import com.danilore.piniateria_lizzety.service.producto.CategoriaProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categorias-producto")
public class CategoriaProductoController {

    @Autowired
    private CategoriaProductoService categoriaProductoService;

    @GetMapping
    public ResponseEntity<Page<CategoriaProductoDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(categoriaProductoService.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaProductoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaProductoService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CategoriaProductoDTO> save(@RequestBody CategoriaProductoDTO categoriaProductoDTO) {
        return ResponseEntity.ok(categoriaProductoService.save(categoriaProductoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaProductoDTO> update(@PathVariable Long id,
            @RequestBody CategoriaProductoDTO categoriaProductoDTO) {
        return ResponseEntity.ok(categoriaProductoService.update(id, categoriaProductoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        categoriaProductoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Page<CategoriaProductoDTO>> buscarPorCriterio(
            @RequestParam String criterio,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(categoriaProductoService.buscarPorCriterio(criterio, page, size));
    }

}

package com.danilore.piniateria_lizzety.controller.producto;

import com.danilore.piniateria_lizzety.dto.producto.CategoriaProductoDTO;
import com.danilore.piniateria_lizzety.service.producto.CategoriaProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categorias-producto")
public class CategoriaProductoController {

    @Autowired
    private CategoriaProductoService categoriaProductoService;

    @GetMapping
    public ResponseEntity<Page<CategoriaProductoDTO>> listarTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(categoriaProductoService.listarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaProductoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaProductoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<CategoriaProductoDTO> guardar(@RequestBody CategoriaProductoDTO categoriaProductoDTO) {
        return ResponseEntity.ok(categoriaProductoService.guardar(categoriaProductoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaProductoDTO> editar(@PathVariable Long id, @RequestBody CategoriaProductoDTO categoriaProductoDTO) {
        return ResponseEntity.ok(categoriaProductoService.editar(id, categoriaProductoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        categoriaProductoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

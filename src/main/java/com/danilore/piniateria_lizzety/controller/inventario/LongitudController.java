package com.danilore.piniateria_lizzety.controller.inventario;

import com.danilore.piniateria_lizzety.dto.inventario.LongitudDTO;
import com.danilore.piniateria_lizzety.service.inventario.LongitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/longitudes")
public class LongitudController {

    @Autowired
    private LongitudService longitudService;

    @GetMapping
    public ResponseEntity<List<LongitudDTO>> getAll(
            @RequestParam(defaultValue = "0") int page, 
            @RequestParam(defaultValue = "10") int size) {
        var pageResult = longitudService.findAll(page, size);
        return ResponseEntity.ok(pageResult.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LongitudDTO> getById(@PathVariable Long id) {
        LongitudDTO dto = longitudService.findById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<LongitudDTO> create(@RequestBody LongitudDTO dto) {
        LongitudDTO createdDto = longitudService.create(dto);
        return ResponseEntity.ok(createdDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LongitudDTO> update(@PathVariable Long id, @RequestBody LongitudDTO dto) {
        LongitudDTO updatedDto = longitudService.update(id, dto);
        return updatedDto != null ? ResponseEntity.ok(updatedDto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        longitudService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

package com.danilore.piniateria_lizzety.controller.usuario;

import com.danilore.piniateria_lizzety.dto.usuario.UsuarioDTO;
import com.danilore.piniateria_lizzety.model.usuario.Usuario;
import com.danilore.piniateria_lizzety.service.usuario.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody Usuario credenciales) {
        Usuario usuarioAutenticado = usuarioService.autenticar(credenciales.getEmail(), credenciales.getPassword());
        UsuarioDTO usuarioDTO = UsuarioDTO.fromEntity(usuarioAutenticado); // Convertir a DTO
        return ResponseEntity.ok(usuarioDTO);
    }

}

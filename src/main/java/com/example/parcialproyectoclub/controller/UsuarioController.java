package com.example.parcialproyectoclub.controller;

import com.example.parcialproyectoclub.model.Usuario;
import com.example.parcialproyectoclub.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
@Tag(name = "Usuarios", description = "API para la gestion de usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    @Operation(summary = "Listar todos los usuarios")
    public List<Usuario> listar() {
        return usuarioService.listarTodos();
    }

    @PostMapping("/crear")
    @Operation(summary = "Crear un nuevo usuario")
    public ResponseEntity<Usuario> crear(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.guardar(usuario));
    }

    @PutMapping("/actualizar/{id}")
    @Operation(summary = "Actualizar un usuario")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @RequestBody Usuario detalles) {
        return usuarioService.obtenerPorId(id)
                .map(usuario -> {
                    usuario.setNombre(detalles.getNombre());
                    usuario.setEmail(detalles.getEmail());
                    usuario.setUsername(detalles.getUsername());
                    if (detalles.getPassword() != null && !detalles.getPassword().isBlank()) {
                        usuario.setPassword(detalles.getPassword());
                    }
                    usuario.setRol(detalles.getRol());
                    return ResponseEntity.ok(usuarioService.guardar(usuario));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar un usuario")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (usuarioService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

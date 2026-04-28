package com.example.parcialproyectoclub.controller;

import com.example.parcialproyectoclub.model.Usuario;
import com.example.parcialproyectoclub.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "API para la gestión de usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

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
        return usuarioService.obtenerPorId(id).map(u -> {
            u.setNombre(detalles.getNombre());
            u.setEmail(detalles.getEmail()); // Asegúrate de que tu modelo tenga 'email'
            return ResponseEntity.ok(usuarioService.guardar(u));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar un usuario")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
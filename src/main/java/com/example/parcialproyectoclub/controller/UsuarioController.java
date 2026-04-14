package com.example.parcialproyectoclub.controller;

import com.example.parcialproyectoclub.model.Usuario;
import com.example.parcialproyectoclub.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "Operaciones de usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "Listar usuarios")
    public List<Usuario> listar() {
        return usuarioService.listarTodos();
    }

    @PostMapping("/registro")
    @Operation(summary = "Registrar usuario")
    public Usuario crear(@RequestBody Usuario usuario) {
        return usuarioService.guardar(usuario);
    }

    @PostMapping("/login")
    @Operation(summary = "Autenticar usuario")
    public String login(@RequestBody Usuario usuario) {
        return usuarioService.autenticar(usuario.getUsername(), usuario.getPassword());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar usuario por ID")
    public void borrar(@PathVariable Long id) {
        usuarioService.eliminar(id);
    }
}

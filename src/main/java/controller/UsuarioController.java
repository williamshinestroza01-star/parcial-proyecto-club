package com.example.parcialproyectoclub.controller;

import com.example.parcialproyectoclub.model.Usuario;
import com.example.parcialproyectoclub.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listarTodos();
    }

    @PostMapping("/registro")
    public Usuario crear(@RequestBody Usuario usuario) {
        return usuarioService.guardar(usuario);
    }

    @PostMapping("/login")
    public String login(@RequestBody Usuario usuario) {
        return usuarioService.autenticar(usuario.getUsername(), usuario.getPassword());
    }

    @DeleteMapping("/{id}")
    public void borrar(@PathVariable Long id) {
        usuarioService.eliminar(id);
    }
}
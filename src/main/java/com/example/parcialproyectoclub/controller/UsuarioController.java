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

    // Listar todos los usuarios (Punto 5 del parcial: CRUD)
    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listarTodos();
    }

    // Registro de usuario (Recibe JSON desde Postman/Swagger)
    @PostMapping("/registro")
    public Usuario crear(@RequestBody Usuario usuario) {
        return usuarioService.guardar(usuario);
    }

    /**
     * LOGIN CORREGIDO
     * Usamos @RequestParam para que funcione con el formulario HTML (login.html)
     * y también se puede probar en Postman usando x-www-form-urlencoded
     */
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        // Asegúrate de que en tu UsuarioService el método se llame 'autenticar' o 'login'
        return usuarioService.autenticar(username, password);
    }

    // Borrar usuario por ID
    @DeleteMapping("/{id}")
    public String borrar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return "Usuario eliminado correctamente";
    }
}
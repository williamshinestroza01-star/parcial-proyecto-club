package com.example.parcialproyectoclub.service;

import com.example.parcialproyectoclub.model.Usuario;
import com.example.parcialproyectoclub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }

    // Lógica de Login simple para 3er semestre
    public String autenticar(String username, String password) {
        return usuarioRepository.findAll().stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst()
                .map(u -> "Login exitoso. Bienvenido " + u.getUsername())
                .orElse("Credenciales incorrectas");
    }
}
package com.example.parcialproyectoclub.controller;

import com.example.parcialproyectoclub.service.ClubService;
import com.example.parcialproyectoclub.service.UsuarioService;
import com.example.parcialproyectoclub.service.JugadorService;
import com.example.parcialproyectoclub.service.EntrenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @Autowired
    private ClubService clubService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JugadorService jugadorService;

    @Autowired
    private EntrenadorService entrenadorService;

    @GetMapping("/")
    public String index(Model model) {
        // 1. Enviamos los clubes
        model.addAttribute("clubes", clubService.listarTodos());

        // 2. Enviamos los usuarios
        model.addAttribute("usuarios", usuarioService.listarTodos());

        // 3. Enviamos los jugadores (Corregido de obtenerTodos a listarTodos)
        model.addAttribute("jugadores", jugadorService.listarTodos());

        // 4. Enviamos los entrenadores
        model.addAttribute("entrenadores", entrenadorService.listarTodos());

        return "index"; // Abre index.html
    }
}
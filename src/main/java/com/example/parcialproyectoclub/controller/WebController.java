package com.example.parcialproyectoclub.controller;

import com.example.parcialproyectoclub.model.Club;
import com.example.parcialproyectoclub.model.Usuario;
import com.example.parcialproyectoclub.service.ClubService;
import com.example.parcialproyectoclub.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class WebController {

    private final UsuarioService usuarioService;
    private final ClubService clubService;

    public WebController(UsuarioService usuarioService, ClubService clubService) {
        this.usuarioService = usuarioService;
        this.clubService = clubService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        model.addAttribute("clubes", clubService.listarTodos());
        model.addAttribute("nuevoUsuario", new Usuario());
        model.addAttribute("nuevoClub", new Club());
        return "index";
    }

    @PostMapping("/usuarios")
    public String crearUsuario(Usuario usuario) {
        usuarioService.guardar(usuario);
        return "redirect:/";
    }

    @PostMapping("/clubes")
    public String crearClub(Club club) {
        clubService.guardar(club);
        return "redirect:/";
    }
}

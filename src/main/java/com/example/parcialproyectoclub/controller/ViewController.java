package com.example.parcialproyectoclub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String mostrarLogin() {
        // Esto busca un archivo llamado login.html en la carpeta templates
        return "login";
    }
}
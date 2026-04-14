package com.example.parcialproyectoclub.controller;

import com.example.parcialproyectoclub.model.Club;
import com.example.parcialproyectoclub.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clubes")
public class ClubController {

    @Autowired
    private ClubService clubService;

    @GetMapping
    public List<Club> listar() {
        return clubService.listarTodos();
    }

    @PostMapping
    public Club crear(@RequestBody Club club) {
        return clubService.guardar(club);
    }
}
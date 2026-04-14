package com.example.parcialproyectoclub.controller;

import com.example.parcialproyectoclub.model.Club;
import com.example.parcialproyectoclub.service.ClubService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clubes")
@Tag(name = "Clubes", description = "Operaciones de clubes")
public class ClubController {

    @Autowired
    private ClubService clubService;

    @GetMapping
    @Operation(summary = "Listar clubes")
    public List<Club> listar() {
        return clubService.listarTodos();
    }

    @PostMapping
    @Operation(summary = "Crear club")
    public Club crear(@RequestBody Club club) {
        return clubService.guardar(club);
    }
}

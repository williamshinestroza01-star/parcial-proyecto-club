package com.example.parcialproyectoclub.controller;

import com.example.parcialproyectoclub.model.Club;
import com.example.parcialproyectoclub.service.ClubService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clubes")
@CrossOrigin(origins = "*")
@Tag(name = "Clubes", description = "API para la gestion de clubes - Parcial")
public class ClubController {

    private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los clubes")
    public List<Club> listar() {
        return clubService.listarTodos();
    }

    @PostMapping("/crear")
    @Operation(summary = "Registrar un nuevo club")
    public ResponseEntity<Club> crear(@RequestBody Club club) {
        return ResponseEntity.ok(clubService.guardar(club));
    }

    @PutMapping("/actualizar/{id}")
    @Operation(summary = "Actualizar un club")
    public ResponseEntity<Club> actualizar(@PathVariable Long id, @RequestBody Club clubDetalles) {
        return clubService.obtenerPorId(id)
                .map(club -> {
                    club.setNombre(clubDetalles.getNombre());
                    return ResponseEntity.ok(clubService.guardar(club));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar un club")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (clubService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        clubService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

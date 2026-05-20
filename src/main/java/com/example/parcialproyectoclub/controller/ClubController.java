package com.example.parcialproyectoclub.controller;

import com.example.parcialproyectoclub.model.Club;
import com.example.parcialproyectoclub.service.ClubService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clubes")
@CrossOrigin(origins = "*")
@Tag(name = "Clubes", description = "API para la gestión de clubes")
public class ClubController {

    private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping
    @Operation(summary = "Listar todos los clubes")
    public List<Club> listar() {
        return clubService.listarTodos();
    }

    @PostMapping("/guardar")
    @Operation(summary = "Crear un nuevo club")
    public ResponseEntity<Club> crear(@RequestBody Club club) {
        Club nuevoClub = clubService.guardar(club);
        return ResponseEntity.ok(nuevoClub);
    }

    @PutMapping("/actualizar/{id}")
    @Operation(summary = "Actualizar un club")
    public ResponseEntity<Club> actualizar(@PathVariable Long id, @RequestBody Club detalles) {
        return clubService.obtenerPorId(id).map(club -> {
            club.setNombre(detalles.getNombre());
            Club actualizado = clubService.guardar(club);
            return ResponseEntity.ok(actualizado);
        }).orElse(ResponseEntity.notFound().build());
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
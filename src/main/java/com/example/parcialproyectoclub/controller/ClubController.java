package com.example.parcialproyectoclub.controller;

import com.example.parcialproyectoclub.model.Club;
import com.example.parcialproyectoclub.service.ClubService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clubes")
@CrossOrigin(origins = "*")
@Tag(name = "Clubes", description = "API para la gestión de clubes - Parcial")
public class ClubController {

    @Autowired
    private ClubService clubService;

    @GetMapping
    public List<Club> listar() {
        return clubService.listarTodos();
    }

    @PostMapping("/crear")
    public ResponseEntity<Club> crear(@RequestBody Club club) {
        Club nuevoClub = clubService.guardar(club);
        return ResponseEntity.ok(nuevoClub);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Club> actualizar(@PathVariable Long id, @RequestBody Club clubDetalles) {
        return clubService.obtenerPorId(id).map(club -> {
            club.setNombre(clubDetalles.getNombre());
            Club actualizado = clubService.guardar(club);
            return ResponseEntity.ok(actualizado);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        clubService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
package com.example.parcialproyectoclub.controller;

import com.example.parcialproyectoclub.model.Entrenador;
import com.example.parcialproyectoclub.service.EntrenadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entrenadores")
@CrossOrigin(origins = "*")
@Tag(name = "Entrenadores", description = "API para la gestión de entrenadores")
public class EntrenadorController {

    private final EntrenadorService entrenadorService;

    public EntrenadorController(EntrenadorService entrenadorService) {
        this.entrenadorService = entrenadorService;
    }

    @GetMapping
    @Operation(summary = "Listar todos los entrenadores")
    public List<Entrenador> listar() {
        return entrenadorService.listarTodos();
    }

    @PostMapping("/guardar")
    @Operation(summary = "Registrar un nuevo entrenador")
    public ResponseEntity<Entrenador> crear(@RequestBody Entrenador entrenador) {
        // Sincronizado: Llama a tu método real guardarEntrenador
        Entrenador nuevoEntrenador = entrenadorService.guardarEntrenador(entrenador);
        return ResponseEntity.ok(nuevoEntrenador);
    }

    @PutMapping("/actualizar/{id}")
    @Operation(summary = "Actualizar un entrenador")
    public ResponseEntity<Entrenador> actualizar(@PathVariable Long id, @RequestBody Entrenador detalles) {
        // Usamos el método modificarEntrenador que ya procesa la lógica del Club
        Entrenador actualizado = entrenadorService.modificarEntrenador(id, detalles);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar un entrenador")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (entrenadorService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        // Sincronizado: Llama a tu método real eliminarEntrenador
        entrenadorService.eliminarEntrenador(id);
        return ResponseEntity.noContent().build();
    }
}
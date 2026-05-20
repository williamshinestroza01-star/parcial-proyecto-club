package com.example.parcialproyectoclub.controller;

import com.example.parcialproyectoclub.model.Jugador;
import com.example.parcialproyectoclub.service.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jugadores")
public class JugadorController {

    @Autowired
    private JugadorService jugadorService;

    // Endpoint para listar todos los jugadores (GET)
    @GetMapping
    public List<Jugador> listarJugadores() {
        return jugadorService.listarTodos();
    }

    // Endpoint para crear un jugador nuevo (POST)
    @PostMapping("/guardar")
    public ResponseEntity<Jugador> crearJugador(@RequestBody Jugador jugador) {
        Jugador nuevoJugador = jugadorService.guardarJugador(jugador);
        return ResponseEntity.ok(nuevoJugador);
    }

    // Endpoint para modificar un jugador existente (PUT) - Cumple el UML
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Jugador> actualizarJugador(@PathVariable Long id, @RequestBody Jugador datosNuevos) {
        Jugador jugadorModificado = jugadorService.modificarJugador(id, datosNuevos);
        if (jugadorModificado != null) {
            return ResponseEntity.ok(jugadorModificado);
        }
        return ResponseEntity.notFound().build();
    }

    // Endpoint para eliminar un jugador (DELETE)
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarJugador(@PathVariable Long id) {
        jugadorService.eliminarJugador(id);
        return ResponseEntity.ok().build();
    }
}
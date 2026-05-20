package com.example.parcialproyectoclub.service;

import com.example.parcialproyectoclub.model.Jugador;
import com.example.parcialproyectoclub.model.Club;
import com.example.parcialproyectoclub.repository.JugadorRepository;
import com.example.parcialproyectoclub.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;

    @Autowired
    private ClubRepository clubRepository; // Inyectamos el repositorio de clubes para poder buscarlos

    // Listar todos los jugadores
    public List<Jugador> listarTodos() {
        return jugadorRepository.findAll();
    }

    // Buscar un jugador por su ID
    public Optional<Jugador> obtenerPorId(Long id) {
        return jugadorRepository.findById(id);
    }

    // Guardar o crear un nuevo jugador asociándole su club real
    public Jugador guardarJugador(Jugador jugador) {
        if (jugador.getClub() != null && jugador.getClub().getId() != null) {
            Optional<Club> clubReal = clubRepository.findById(jugador.getClub().getId());
            if (clubReal.isPresent()) {
                jugador.setClub(clubReal.get()); // Enlazamos el club completo de la base de datos
            } else {
                jugador.setClub(null); // Si el ID no existe, lo deja sin club para no romper el sistema
            }
        }
        return jugadorRepository.save(jugador);
    }

    // Método del UML: Modificar un jugador existente al 100%
    public Jugador modificarJugador(Long id, Jugador datosNuevos) {
        Optional<Jugador> jugadorExistente = jugadorRepository.findById(id);

        if (jugadorExistente.isPresent()) {
            Jugador jugador = jugadorExistente.get();

            // Actualizamos los campos nativos
            jugador.setNombre(datosNuevos.getNombre());
            jugador.setEdad(datosNuevos.getEdad());
            jugador.setNumero(datosNuevos.getNumero());
            jugador.setPosicion(datosNuevos.getPosicion());
            jugador.setCategoria(datosNuevos.getCategoria());

            // Lógica profesional para actualizar el club asociado
            if (datosNuevos.getClub() != null && datosNuevos.getClub().getId() != null) {
                Optional<Club> clubReal = clubRepository.findById(datosNuevos.getClub().getId());
                if (clubReal.isPresent()) {
                    jugador.setClub(clubReal.get());
                } else {
                    jugador.setClub(null);
                }
            } else {
                jugador.setClub(null);
            }

            return jugadorRepository.save(jugador); // Guarda los cambios en Postgres/H2
        }

        return null;
    }

    // Eliminar un jugador por su ID
    public void eliminarJugador(Long id) {
        jugadorRepository.deleteById(id);
    }
}
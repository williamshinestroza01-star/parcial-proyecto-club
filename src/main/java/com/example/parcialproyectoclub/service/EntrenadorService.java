package com.example.parcialproyectoclub.service;

import com.example.parcialproyectoclub.model.Entrenador;
import com.example.parcialproyectoclub.model.Club;
import com.example.parcialproyectoclub.repository.EntrenadorRepository;
import com.example.parcialproyectoclub.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntrenadorService {

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    @Autowired
    private ClubRepository clubRepository;

    // Listar todos los entrenadores usando la misma estructura limpia
    public List<Entrenador> listarTodos() {
        return entrenadorRepository.findAll();
    }

    // Buscar entrenador por ID
    public Optional<Entrenador> obtenerPorId(Long id) {
        return entrenadorRepository.findById(id);
    }

    // Guardar asociando el club real de la BD de forma segura
    public Entrenador guardarEntrenador(Entrenador entrenador) {
        if (entrenador.getClub() != null && entrenador.getClub().getId() != null) {
            Optional<Club> clubReal = clubRepository.findById(entrenador.getClub().getId());
            if (clubReal.isPresent()) {
                entrenador.setClub(clubReal.get());
            } else {
                entrenador.setClub(null);
            }
        }
        return entrenadorRepository.save(entrenador);
    }

    // Modificar entrenador existente al 100% como dicta el UML
    public Entrenador modificarEntrenador(Long id, Entrenador datosNuevos) {
        Optional<Entrenador> entrenadorExistente = entrenadorRepository.findById(id);

        if (entrenadorExistente.isPresent()) {
            Entrenador entrenador = entrenadorExistente.get();
            entrenador.setNombre(datosNuevos.getNombre());
            entrenador.setExperiencia(datosNuevos.getExperiencia());
            entrenador.setEspecialidad(datosNuevos.getEspecialidad());

            // Lógica para actualizar el club asociado
            if (datosNuevos.getClub() != null && datosNuevos.getClub().getId() != null) {
                Optional<Club> clubReal = clubRepository.findById(datosNuevos.getClub().getId());
                if (clubReal.isPresent()) {
                    entrenador.setClub(clubReal.get());
                } else {
                    entrenador.setClub(null);
                }
            } else {
                entrenador.setClub(null);
            }

            return entrenadorRepository.save(entrenador);
        }
        return null;
    }

    // Eliminar entrenador por ID
    public void eliminarEntrenador(Long id) {
        entrenadorRepository.deleteById(id);
    }
}
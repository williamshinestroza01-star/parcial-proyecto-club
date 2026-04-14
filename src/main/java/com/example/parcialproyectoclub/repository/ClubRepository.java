package com.example.parcialproyectoclub.repository;

import com.example.parcialproyectoclub.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Long> {
    // Aquí ya tienes todos los métodos de guardar, borrar y buscar por defecto
}
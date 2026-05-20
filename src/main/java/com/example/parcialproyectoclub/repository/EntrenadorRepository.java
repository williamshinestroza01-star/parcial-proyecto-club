package com.example.parcialproyectoclub.repository;

import com.example.parcialproyectoclub.model.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrenadorRepository extends JpaRepository<Entrenador, Long> {
    // Hereda todos los métodos de CRUD automáticos de Spring Data JPA
}
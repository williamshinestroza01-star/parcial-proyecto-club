package com.example.parcialproyectoclub.repository;

import com.example.parcialproyectoclub.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Long> {
}
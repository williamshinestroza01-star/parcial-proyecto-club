package com.example.parcialproyectoclub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "entrenadores")
public class Entrenador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int experiencia; // Años de experiencia
    private String especialidad; // Táctico, Preparador físico, etc.

    // Relación profesional: Muchos entrenadores pueden pertenecer a un Club
    @ManyToOne
    @JoinColumn(name = "club_id", nullable = true)
    private Club club;

    // Constructor vacío obligatorio para JPA
    public Entrenador() {
    }

    // Constructor con parámetros
    public Entrenador(Long id, String nombre, int experiencia, String especialidad, Club club) {
        this.id = id;
        this.nombre = nombre;
        this.experiencia = experiencia;
        this.especialidad = especialidad;
        this.club = club;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
}
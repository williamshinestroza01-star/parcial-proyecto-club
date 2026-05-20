package com.example.parcialproyectoclub.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int edad;
    private int numero;
    private String posicion;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;
}
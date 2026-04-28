package com.example.parcialproyectoclub.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Data // Esto de Lombok ya te crea los Getters y Setters automáticamente
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;   // Agregamos este para que el Controller lo vea
    private String email;    // Agregamos este también
    private String username;
    private String password;
    private String rol;
}
package com.example.parcialproyectoclub.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clubes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
}
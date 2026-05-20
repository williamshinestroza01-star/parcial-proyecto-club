package com.example.parcialproyectoclub.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "clubes")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    // RELACIÓN NUEVA: Un Club tiene Muchos Jugadores (1 a * en el UML)
    // El "cascade = CascadeType.ALL" hace que si borras un club, se limpien sus jugadores de la base de datos automáticamente.
    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List<Jugador> jugadores;

    // Constructores obligatorios de JPA
    public Club() {
    }

    public Club(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y Setters de las variables básicas
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

    // Getters y Setters nuevos para la lista de jugadores (así el sistema puede leerlos y guardarlos)
    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
}
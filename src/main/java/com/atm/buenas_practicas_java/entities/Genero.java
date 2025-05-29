package com.atm.buenas_practicas_java.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="generos")

public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_genero", columnDefinition = "INTEGER")
    private int idGenero;
    private String nombre;

    @ManyToMany(mappedBy = "generos")
    private List<Objeto> objetos;

    @ManyToMany(mappedBy = "generos")
    private Set<Usuario> usuarios;
}

package com.atm.buenas_practicas_java.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="generos")

public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_genero")
    private int idGenero;
    private String nombre;
}

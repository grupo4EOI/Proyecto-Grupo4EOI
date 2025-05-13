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
@Table(name="objetos")
public class Objeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    @Column(name="fecha_publicacion")
    private Date fechaPublicacion;
    @Column(name="imagen_url")
    private String imagenUrl;
    @Column(name="trailer_url")
    private String trailerUrl;
    @Column(name="duracion_minutos")
    private int duracionMinutos;
    private int temporadas;
    private int episodios;
}

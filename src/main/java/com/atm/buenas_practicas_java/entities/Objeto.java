package com.atm.buenas_practicas_java.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="objetos")
public class Objeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_objeto")
    private Long idObjeto;
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


    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name="id_tipo", nullable = false)
    private Tipo tipo;

    // Relación M:N de las tablas usuarios y objetos
    @OneToMany(mappedBy = "objeto")
    private Set<ObjetoUsuario> usuarios;

    // Relacion 1:N de las tablas objetos y reseñas
    @OneToMany(mappedBy = "objeto")
    private List<Resena> resenas;

    // Relacion 1:N de las tablas objetos y personasObjetos
    @OneToMany(mappedBy = "objeto")
    private Set<PersonaObjeto> personasObjeto;

    @OneToMany(mappedBy = "objeto")
    private Set<ObjetoComunidad> objetosComunidades;

    @OneToMany(mappedBy = "objeto")
    private Set<GeneroObjeto> generosObjeto;
}

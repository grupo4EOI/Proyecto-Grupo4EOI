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
@Table(name = "comunidades")
public class Comunidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INTEGER")
    private Long idComunidad;
    private String nombreComunidad;
    @Column(columnDefinition = "TEXT")
    private String descripcion;

    // Relación con usuarios (tabla intermedia)
    @OneToMany(mappedBy = "comunidad")
    private Set<UsuarioComunidad> usuariosComunidad;

    @OneToMany(mappedBy = "comunidad")
    private List<Publicacion> publicaciones;

    @OneToMany(mappedBy = "comunidad")
    private Set<ObjetoComunidad> objetosComunidades;
}

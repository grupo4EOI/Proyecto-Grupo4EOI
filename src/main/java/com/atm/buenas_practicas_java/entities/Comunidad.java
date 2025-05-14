package com.atm.buenas_practicas_java.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private int idComunidad;
    @Column(name = "nombre_comunidad")
    private String nombreComunidad;
    @Column(columnDefinition = "TEXT")
    private String descripcion;

    // Relación con usuarios (tabla intermedia)
    @OneToMany(mappedBy = "comunidades")
    private Set<UsuarioComunidad> usuariosComunidad;
}

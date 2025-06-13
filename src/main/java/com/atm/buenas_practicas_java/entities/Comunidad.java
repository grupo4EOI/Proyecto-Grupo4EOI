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
    @Column(columnDefinition = "TEXT")
    private String urlImg;


    // Relación con usuarios (tabla intermedia)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_comunidades",
            joinColumns = @JoinColumn(name = "id_comunidad"),
            inverseJoinColumns = @JoinColumn(name = "id_usuario"))
    private Set<Usuario> usuarios;

    @OneToMany(mappedBy = "comunidad", fetch = FetchType.LAZY)
    private List<Publicacion> publicaciones;

    @OneToMany(mappedBy = "comunidad")
    private List<Objeto> objetos;
}

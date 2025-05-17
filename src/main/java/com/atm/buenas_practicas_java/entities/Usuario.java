package com.atm.buenas_practicas_java.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idUsuario;
    private String nombreUsuario;
    private String email;
    private String contrasena;
    private LocalDateTime fechaRegistro;
    private String avatarUrl;
    private String biografia;
    private LocalDateTime ultimaConexion;
    @Column
    private boolean esAdministrador;

    // Relación 1:N con reseñas
    @OneToMany
    @JoinColumn(name = "id_resena")
    private List<Resena> resenas = new ArrayList<>();

    // Relación M:N entre las tablas objetos y usuarios
    @OneToMany(mappedBy = "objetos")
    private Set<ObjetoUsuario> objetos;

    // Relación con comentarios de las publicaciones
    @OneToMany(mappedBy = "usuarios")
    Set<ComentarioPublicacion> comentariosPublicacion;

    // Relación con comunidad (tabla intermedia)
    @OneToMany(mappedBy = "usuarios")
    Set<UsuarioComunidad> usuariosComunidad;
}

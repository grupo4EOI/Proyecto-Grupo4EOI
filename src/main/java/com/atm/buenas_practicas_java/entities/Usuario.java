package com.atm.buenas_practicas_java.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

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
    @NotNull
    private Long idUsuario;
    @NotNull
    private String nombreUsuario;
    @NotNull
    private String email;
    @NotNull
    private String contrasena;
    @Column(columnDefinition = "DATETIME default NOW()")
    private LocalDateTime fechaRegistro;
    private String avatarUrl;
    private String biografia;
    @Column(columnDefinition = "DATETIME default NOW()")
    private LocalDateTime ultimaConexion;
    @Column(columnDefinition = "BOOLEAN default FALSE")
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
    private Set<ComentarioPublicacion> comentariosPublicacion;

    // Relación con comunidad (tabla intermedia)
    @OneToMany(mappedBy = "usuarios")
    private Set<UsuarioComunidad> usuariosComunidad;

    // Relación con la tabla Amistad (M:N autorelación de Usuario)
    @OneToMany(mappedBy = "usuario")
    private Set<Amistad> usuarios;

    @OneToMany(mappedBy = "amigo")
    private Set<Amistad> amigos;

    @OneToMany(mappedBy = "usuario")
    private List<Reaccion> reacciones;

    @OneToMany(mappedBy = "usuario")
    private Set<ComentarioResena> comentariosResenas;

}

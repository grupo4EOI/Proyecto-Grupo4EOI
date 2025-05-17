package com.atm.buenas_practicas_java.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
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
    private Long idUsuario;
    @NotNull
    private String nombreUsuario;
    @NotNull
    private String email;
    @NotNull
    private String contrasena;
    private LocalDateTime fechaRegistro;
    private String avatarUrl;
    private String biografia;
    private LocalDateTime ultimaConexion;
    @Column(columnDefinition = "BOOLEAN default false")
    private boolean esAdministrador;

    // Relación 1:N con reseñas
    @OneToMany(mappedBy = "usuario")
    private List<Resena> resenas;

    // Relación M:N entre las tablas objetos y usuarios
    @OneToMany(mappedBy = "usuario")
    private Set<ObjetoUsuario> objetos;

    // Relación con comentarios de las publicaciones
    @OneToMany(mappedBy = "usuario")
    private Set<ComentarioPublicacion> comentariosPublicacion;

    // Relación con comunidad (tabla intermedia)
    @OneToMany(mappedBy = "usuario")
    private Set<UsuarioComunidad> usuariosComunidad;

    // Relación con la tabla Amistad (M:N autorelación de Usuario)
    @OneToMany
    @JoinColumn(name = "id_usuario")
    private Set<Amistad> usuarios;

    @OneToMany
    @JoinColumn(name = "id_amigo")
    private Set<Amistad> amigos;

    @OneToMany(mappedBy = "usuario")
    private List<Reaccion> reacciones;

    @OneToMany(mappedBy = "usuario")
    private Set<ComentarioResena> comentariosResenas;

    @OneToMany(mappedBy = "usuario")
    private Set<GeneroUsuario> generosUsuario;
}

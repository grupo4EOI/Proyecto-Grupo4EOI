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
    @Column(name = "id_usuario")
    private int idUsuario;
    @Column(name="nombre_usuario")
    private String nombreUsuario;
    private String email;
    private String contrasena;
    @Column(name="fecha_registro")
    private LocalDateTime fechaRegistro;
    @Column(name="avatar_url")
    private String avatarUrl;
    private String biografia;
    @Column(name="ultima_conexion")
    private LocalDateTime ultimaConexion;
    @Column
    private boolean esAdministrador;

    // Relación 1:N con reseñas
    @OneToMany
    @JoinColumn(name = "id_resena")
    private List<Resena> resenas = new ArrayList<>();

    @OneToMany(mappedBy = "objeto")
    private Set<ObjetoUsuario> objetos;

    @OneToMany(mappedBy = "usuario")
    Set<ComentarioPublicacion> comentariosPublicacion;


}

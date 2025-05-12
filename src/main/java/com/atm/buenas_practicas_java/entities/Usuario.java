package com.atm.buenas_practicas_java.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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
}

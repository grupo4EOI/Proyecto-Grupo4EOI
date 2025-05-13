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
@Table(name="generos_usuarios")

public class GeneroUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_genero_usuario")
    private int idGeneroUsuario;

    @ManyToOne
    @JoinColumn(name="id_genero")
    private Genero genero;
    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;

}

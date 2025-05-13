package com.atm.buenas_practicas_java.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="resenas")
public class Resena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resena")
    private int idResena;
    private String titulo;
    private String contenido;
    private float puntuacion;
    private boolean spoiler;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    // Relacion 1:N entre la tabla objetos y reseñas
    @ManyToOne
    @JoinColumn(name = "id_objeto")
    private Objeto objeto;

}

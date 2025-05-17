package com.atm.buenas_practicas_java.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

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
    private Long idResena;
    private String titulo;
    private String contenido;
    private float puntuacion;
    private boolean spoiler;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    // Relacion 1:N entre la tabla objetos y reseñas
    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_objeto", nullable = false)
    private Objeto objeto;

    @OneToMany(mappedBy = "resena")
    private List<Reaccion> reacciones;

    @OneToMany(mappedBy = "resena")
    private Set<ComentarioResena> comentariosResena;

}

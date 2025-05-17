package com.atm.buenas_practicas_java.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reacciones")
public class Reaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReaccion;
    @NotNull
    @Column(nullable = false)
    private Boolean like;

    @NotNull
    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_comentario_publicacion")
    private ComentarioPublicacion comentarioPublicacion;

    @ManyToOne
    @JoinColumn(name = "id_resena")
    private Resena resena;
}

package com.atm.buenas_practicas_java.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
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
    @Column(name = "id_resena", columnDefinition = "INTEGER")
    private int idResena;
    private String titulo;
    @Column(columnDefinition = "TEXT")
    private String contenido;
    private Double puntuacion;
    private boolean spoiler;
    private Boolean abuso = false;

    private LocalDateTime fechaPublicacion = LocalDateTime.now();

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    // Relacion 1:N entre la tabla objetos y reseñas
    @ManyToOne
    @JoinColumn(name = "id_objeto")
    private Objeto objeto;

    @OneToMany(mappedBy = "resena")
    private List<Reaccion> reacciones;

    @OneToMany(mappedBy = "resena", fetch = FetchType.EAGER)
    private List<ComentarioResena> comentariosResena;

}

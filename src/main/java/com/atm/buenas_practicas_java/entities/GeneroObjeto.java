package com.atm.buenas_practicas_java.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="generos_objetos")

public class GeneroObjeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_genero_objeto", columnDefinition = "INTEGER")
    private int idGeneroObjeto;

    // Relación M:N entre la tabla generos y objetos.
    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_genero", nullable = false)
    private Genero genero;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name="id_objeto", nullable = false)
    private Objeto objeto;
}

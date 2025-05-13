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
@Table(name="generos_objetos")

public class GeneroObjeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_genero_objeto")
    private int idGeneroObjeto;

    @ManyToOne
    @JoinColumn(name="id_genero")
    private Genero genero;
    @ManyToOne
    @JoinColumn(name="id_objeto")
    private Objeto objeto;
}

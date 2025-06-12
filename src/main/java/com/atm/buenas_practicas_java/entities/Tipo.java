package com.atm.buenas_practicas_java.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tipos")
public class Tipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_tipo", columnDefinition = "INTEGER")
    private Long idTipo;
    private String nombre;

    // Relación 1:M Tipo con la tabla Objetos
    @OneToMany(mappedBy = "tipo")
    private List<Objeto> objetos;
}

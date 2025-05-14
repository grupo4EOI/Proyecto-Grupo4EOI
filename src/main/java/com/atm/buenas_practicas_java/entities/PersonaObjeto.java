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
@Table(name="personas_objetos")

public class PersonaObjeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_persona_objeto")
    private int idPersonaObjeto;


    @ManyToOne
    @JoinColumn(name="id_persona")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name="id_objeto")
    private Objeto objeto;

}

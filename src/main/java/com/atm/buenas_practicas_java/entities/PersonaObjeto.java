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
@Table(name="personas_objetos")

public class PersonaObjeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_persona_objeto", columnDefinition = "INTEGER")
    private Long idPersonaObjeto;
    //Rol indica si es 1 es director y si es 0 es actor
    private boolean rol;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name="id_persona", nullable = false)
    private Persona persona;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name="id_objeto", nullable = false)
    private Objeto objeto;

}

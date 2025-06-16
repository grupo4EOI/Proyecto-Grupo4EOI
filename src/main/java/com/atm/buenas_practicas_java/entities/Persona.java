package com.atm.buenas_practicas_java.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="personas")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INTEGER")
    private Long idPersona;
    private String nombreCompleto;
    private LocalDate fechaNacimiento;
    @Column(columnDefinition = "TEXT")
    private String biografia;
    @Column(columnDefinition = "TEXT")
    private String fotoUrl;

    //Relacion 1:M personas con personas_objetos
    @OneToMany(mappedBy = "persona")
    private Set<PersonaObjeto> personasObjetos;
}

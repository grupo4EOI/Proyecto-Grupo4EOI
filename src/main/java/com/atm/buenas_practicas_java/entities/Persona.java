package com.atm.buenas_practicas_java.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
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
    @Column(name="id_persona")
    private Long idPersona;
    private String nombre;
    private String apellido;
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;
    @Column(columnDefinition = "TEXT")
    private String biografia;
    @Column(name = "foto_url")
    private String fotoUrl;

    //Relacion 1:M personas con personas_objetos
    @OneToMany(mappedBy = "persona")
    private Set<PersonaObjeto> personasObjetos;
}

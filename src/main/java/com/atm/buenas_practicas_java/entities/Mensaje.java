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
@Table(name="mensajes")
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INTEGER")
    private Long idMensaje;
    @Column(columnDefinition = "TEXT")
    private String contenido;
    private Date fecha;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name="id_amistad", nullable = false)
    private Amistad amistad;

}

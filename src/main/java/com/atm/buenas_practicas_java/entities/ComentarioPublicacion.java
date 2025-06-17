package com.atm.buenas_practicas_java.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="comentarios_publicacion")
public class ComentarioPublicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INTEGER")
    private Long idComentarioPublicacion;

    private LocalDateTime fecha = LocalDateTime.now();

    @Column(columnDefinition = "TEXT")
    private String contenido;

    private Boolean abuso = false;
    private Boolean baneado = false;

    @ManyToOne
    @JoinColumn(name = "id_publicacion")
    private Publicacion publicacion;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_comentario_citado")
    private ComentarioPublicacion comentarioCitado;

    @OneToMany(mappedBy = "comentarioCitado")
    private List<ComentarioPublicacion> comentarios;
}

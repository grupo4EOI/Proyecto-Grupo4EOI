package com.atm.buenas_practicas_java.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="amistades", uniqueConstraints = @UniqueConstraint(columnNames = {"id_usuario", "id_amigo"}))
public class Amistad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" ,columnDefinition = "INTEGER")
    private Long idAmistad;
    private boolean estado;
    private Date fecha;

//    @ManyToOne
//    @JoinColumn(name="id_usuario")
//    private Usuario usuario;
//
//    @ManyToOne
//    @JoinColumn(name="id_amigo")
//    private Usuario amigo;

    @OneToMany(mappedBy = "amistad")
    private List<Mensaje> mensajes;

}

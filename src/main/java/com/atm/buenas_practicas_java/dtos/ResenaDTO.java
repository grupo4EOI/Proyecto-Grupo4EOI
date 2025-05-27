package com.atm.buenas_practicas_java.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResenaDTO {
    private Long idResena;
    private String titulo;
    private String contenido;
    private Double puntuacion;
    private Boolean spoiler;
    private UsuarioDTO usuario;
    private ObjetoDTO objetoDTO;
    private List<ReaccionDTO> reacciones;
    private Set<ComentarioResenaDTO> comentariosResena;
}

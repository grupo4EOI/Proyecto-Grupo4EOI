package com.atm.buenas_practicas_java.dtos;

import java.time.LocalDateTime;
import java.util.List;

public record ResenaDTO(
    String titulo,
    LocalDateTime fechaPublicacion,
    String contenido,
    Double puntuacion,
    boolean spoiler,
    UsuarioDTO autor,
    List<ComentarioResenaDTO> comentariosResena
) { }

package com.atm.buenas_practicas_java.dtos;

import java.time.LocalDateTime;
import java.util.List;

public record ComentarioPublicacionDTO(
        Long idComentarioPublicacion,
        String titulo,
        String contenido,
        Boolean baneado,
        UsuarioDTO usuario,
        LocalDateTime fecha
) { }

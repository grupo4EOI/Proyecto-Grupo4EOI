package com.atm.buenas_practicas_java.dtos;

import java.time.LocalDateTime;

public record ComentarioPublicacionSimpleDTO(
        String contenido,
        UsuarioDTO usuario,
        LocalDateTime fecha
) {
}

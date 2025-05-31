package com.atm.buenas_practicas_java.dtos;

import java.util.List;

public record PublicacionDTO(
        String titulo,
        List<ComentarioPublicacionDTO> comentarios
) {
}

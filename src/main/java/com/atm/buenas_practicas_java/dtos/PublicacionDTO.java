package com.atm.buenas_practicas_java.dtos;

public record PublicacionDTO(
        String titulo,
        List<ComentarioPublicacionDTO> comentarios
) {
}

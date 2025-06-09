package com.atm.buenas_practicas_java.dtos;

public record ComentarioPublicacionCrearDTO(
        Long idComentarioPublicacion,
        String contenido,
        UsuarioDTO usuario,
        PublicacionDTO publicacion
) {
}

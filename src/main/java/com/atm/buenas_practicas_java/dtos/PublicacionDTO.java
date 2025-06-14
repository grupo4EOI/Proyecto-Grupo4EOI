package com.atm.buenas_practicas_java.dtos;

import com.atm.buenas_practicas_java.entities.Comunidad;

import java.util.List;

public record PublicacionDTO(
        Long idPublicacion,
        ComunidadDTO comunidad,
        String titulo,
        Long numComentarios,
        UsuarioDTO usuario
) {
}

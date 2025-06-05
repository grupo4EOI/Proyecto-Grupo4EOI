package com.atm.buenas_practicas_java.dtos;

import java.util.List;

public record PublicacionDTO(
        Long idPublicacion,
        String titulo,
        Long numComentarios,
        UsuarioDTO usuario
) {
}

package com.atm.buenas_practicas_java.dtos;

import java.util.List;

public record ComentarioResenaDTO(
        String fecha,
        String contenido,
        UsuarioDTO usuario,
        ResenaDTO resena,
        List<ReaccionDTO> reacciones
) {
}

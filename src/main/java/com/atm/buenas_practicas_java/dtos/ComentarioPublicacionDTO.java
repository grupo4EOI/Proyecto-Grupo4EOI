package com.atm.buenas_practicas_java.dtos;

import java.time.LocalDateTime;
import java.util.List;

public record ComentarioPublicacionDTO(
        String titulo,
        String contenido,
        UsuarioDTO usuario,
        List<ReaccionDTO> reacciones,
        LocalDateTime fecha
) { }

package com.atm.buenas_practicas_java.dtos;

import java.util.List;

public record ComentarioPublicacionDTO(
        String contenido,
        PublicacionDTO publicacion,
        UsuarioDTO usuario,
        List<ReaccionDTO> reacciones
) { }

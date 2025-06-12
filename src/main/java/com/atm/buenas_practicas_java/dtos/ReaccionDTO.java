package com.atm.buenas_practicas_java.dtos;

import com.atm.buenas_practicas_java.entities.ComentarioPublicacion;

public record ReaccionDTO(
        Boolean meGusta,
        UsuarioDTO usuario,
        PublicacionDTO publicacion,
        ComentarioPublicacionDTO comentarioPublicacion,
        ComentarioResenaDTO comentarioResena
) {
}

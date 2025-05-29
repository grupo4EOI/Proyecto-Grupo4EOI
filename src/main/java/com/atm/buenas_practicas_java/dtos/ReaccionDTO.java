package com.atm.buenas_practicas_java.dtos;

public record ReaccionDTO(
        Boolean meGusta,
        UsuarioDTO usuario,
        PublicacionDTO publicacion,
        ComentarioPublicacionDTO comentarioPublicacion,
        ComentarioResenaDTO comentarioResena
) {
}

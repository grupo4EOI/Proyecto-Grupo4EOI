package com.atm.buenas_practicas_java.dtos.composedDTOs;

import com.atm.buenas_practicas_java.dtos.*;
import com.atm.buenas_practicas_java.entities.*;

import java.util.List;
import java.util.Set;

public record UsuarioPerfilDTO(
        Long idUsuario,
        String nombreUsuario,
        String avatarUrl,
        String biografia,
        String role,
        List<ResenaDTO> resenas,
        List<ComentarioPublicacionSimpleDTO> comentariosPublicaciones,
        List<ResenaDTO> likesResenas,
        List<UsuarioDTO> amigos,
        List<ComentarioResenaDTO> comentariosResenas,
        List<GeneroDTO> generosPeliculas,
        List<GeneroDTO> generosSeries,
        List<GeneroDTO> generosVideojuegos,
        Long numeroResenas,
        Long numeroFavoritos,
        Long numeroVistos,
        Long numeroPendientes
) {
}

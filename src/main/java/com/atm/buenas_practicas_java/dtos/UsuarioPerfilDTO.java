package com.atm.buenas_practicas_java.dtos;

import com.atm.buenas_practicas_java.entities.*;

import java.util.List;
import java.util.Set;

public record UsuarioPerfilDTO(
        Long idUsuario,
        String nombreUsuario,
        String avatarUrl,
        String biografia,
        String role,
        List<Resena> resenas,
        Set<ComentarioPublicacion> comentariosPublicacion,
        List<UsuarioDTO> amigos,
        List<Reaccion> reacciones,
        Set<ComentarioResena> comentariosResenas,
        Set<Genero> generos,
        List<GeneroDTO> generosPeliculas,
        List<GeneroDTO> generosSeries,
        List<GeneroDTO> generosVideojuegos,
        List<Publicacion> publicaciones
) {
}

package com.atm.buenas_practicas_java.dtos;

import com.atm.buenas_practicas_java.entities.*;

import java.util.List;
import java.util.Set;

public record UsuarioPerfilDTO(
        Long idUsuario,
        String nombreUsuario,
        String avatarUrl,
        String biografia,
        boolean esAdministrador,
        List<Resena> resenas,
        Set<ComentarioPublicacion> comentariosPublicacion,
        Set<Amistad> amigos,
        List<Reaccion> reacciones,
        Set<ComentarioResena> comentariosResenas,
        Set<Genero> generos,
        List<GeneroPerfilDTO> generosPeliculas,
        List<GeneroPerfilDTO> generosSeries,
        List<GeneroPerfilDTO> generosVideojuegos
) {
}

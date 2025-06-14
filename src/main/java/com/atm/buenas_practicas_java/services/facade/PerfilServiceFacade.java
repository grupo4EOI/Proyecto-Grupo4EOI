package com.atm.buenas_practicas_java.services.facade;

import com.atm.buenas_practicas_java.dtos.GeneroDTO;
import com.atm.buenas_practicas_java.dtos.UsuarioDTO;
import com.atm.buenas_practicas_java.dtos.composedDTOs.AjustesPerfilDTO;
import com.atm.buenas_practicas_java.dtos.composedDTOs.UsuarioPerfilDTO;
import com.atm.buenas_practicas_java.entities.Genero;
import com.atm.buenas_practicas_java.entities.Publicacion;
import com.atm.buenas_practicas_java.entities.Usuario;
import com.atm.buenas_practicas_java.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PerfilServiceFacade {

    private final UsuarioService usuarioService;
    private final AjustesPerfilService ajustesPerfilService;
    private final GeneroService generoService;
    private final ResenaService resenaService;
    private final ComentarioPublicacionService comentarioPublicacionService;
    private final ComentarioResenaService comentarioResenaService;

    public UsuarioPerfilDTO obtenerPerfilDTO(Long idUsuario) {
        Usuario usuario = usuarioService.findById(idUsuario);

        Set<Genero> generos = usuario.getGeneros();

        return new UsuarioPerfilDTO(
                usuario.getIdUsuario(),
                usuario.getNombreUsuario(),
                usuario.getAvatarUrl(),
                usuario.getBiografia(),
                usuario.getRole(),
                resenaService.obtenerResenasUsuario(idUsuario, usuario.getNombreUsuario()),
                comentarioPublicacionService.obtenerComentariosPublicacionUsuario(idUsuario),
                resenaService.obtenerResenasReaccionadasUsuario(idUsuario, usuario.getNombreUsuario()),
                usuarioService.buscarAmigosUsuario(idUsuario),
                comentarioResenaService.obtenerComentariosResenasUsuario(idUsuario),
                generoService.filtrarGenerosPorTipo(generos, "pelicula"),
                generoService.filtrarGenerosPorTipo(generos, "serie"),
                generoService.filtrarGenerosPorTipo(generos, "videojuego")
        );
    }

//    public void editarBiografia(Long idUsuario, String biografia) {
//        Usuario usuario = perfilService.findByIdUsuario(idUsuario);
//        usuario.setBiografia(biografia);
//        perfilService.saveAndFlush(usuario);
//    }

//    public AjustesPerfilDTO obtenerAjustesPerfil(Long id) {
//        return ajustesPerfilService.obtenerAjustesPerfil(id);
//    }

    public void guardarAjustesPerfil(Long idUsuario, AjustesPerfilDTO ajustesPerfil) {
        ajustesPerfilService.actualizarAjustesPerfil(idUsuario, ajustesPerfil);
    }
}

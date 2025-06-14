package com.atm.buenas_practicas_java.services.facade;

import com.atm.buenas_practicas_java.dtos.GeneroDTO;
import com.atm.buenas_practicas_java.dtos.UsuarioDTO;
import com.atm.buenas_practicas_java.dtos.composedDTOs.AjustesPerfilDTO;
import com.atm.buenas_practicas_java.dtos.composedDTOs.UsuarioPerfilDTO;
import com.atm.buenas_practicas_java.entities.Genero;
import com.atm.buenas_practicas_java.entities.Publicacion;
import com.atm.buenas_practicas_java.entities.Usuario;
import com.atm.buenas_practicas_java.services.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PerfilServiceFacade {

    private final UsuarioService usuarioService;
    private final GeneroService generoService;
    private final ResenaService resenaService;
    private final ComentarioPublicacionService comentarioPublicacionService;
    private final ComentarioResenaService comentarioResenaService;
    private final ImagenPerfilService imagenPerfilService;
    private final ReaccionService reaccionService;
    private final PasswordEncoder encoder;
    private final ObjetoUsuarioService objetoUsuarioService;

    @Transactional
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
                generoService.filtrarGenerosPorTipo(generos, "videojuego"),
                resenaService.contarResenasUsuario(idUsuario),
                reaccionService.contarReaccionesUsuario(idUsuario),
                objetoUsuarioService.contarObjetosVistosUsuario(idUsuario),
                objetoUsuarioService.contarObjetosPendientesUsuario(idUsuario)
        );
    }

    public void editarBiografia(Long idUsuario, String biografia) {
        Usuario usuario = usuarioService.findById(idUsuario);
        usuario.setBiografia(biografia);
        usuarioService.saveAndFlush(usuario);
    }

    // Ajustes de perfil
    public AjustesPerfilDTO obtenerAjustesPerfil(Long idUsuario)  {
        Usuario usuario = usuarioService.findById(idUsuario);

        List<Genero> generosPeliculas = generoService.obtenerGenerosPorTipo("pelicula");
        List<Genero> generosSeries = generoService.obtenerGenerosPorTipo("serie");
        List<Genero> generosVideojuegos = generoService.obtenerGenerosPorTipo("videojuego");

        return new AjustesPerfilDTO(
                usuario.getIdUsuario(),
                usuario.getNombreUsuario(),
                null,
                usuario.getBiografia(),
                generosPeliculas,
                generosSeries,
                generosVideojuegos,
                null
        );
    }

    public void actualizarAjustesPerfil(Long idUsuario, AjustesPerfilDTO ajustesPerfildto) {
        Usuario usuario = usuarioService.findById(idUsuario);

        if (ajustesPerfildto.nombreUsuario() != null && !ajustesPerfildto.nombreUsuario().isBlank()) {
            usuario.setNombreUsuario(ajustesPerfildto.nombreUsuario());
        }

        if (ajustesPerfildto.contrasena() != null && !ajustesPerfildto.contrasena().isBlank()) {
            usuario.setContrasena(encoder.encode(ajustesPerfildto.contrasena()));
        }

        usuario.setBiografia(ajustesPerfildto.biografia());

        Set<Genero> generos = new HashSet<>();

        if (ajustesPerfildto.generosPeliculas() != null) {
            generos.addAll(ajustesPerfildto.generosPeliculas());
        }

        if (ajustesPerfildto.generosSeries() != null) {
            generos.addAll(ajustesPerfildto.generosSeries());
        }

        if (ajustesPerfildto.generosVideojuegos() != null) {
            generos.addAll(ajustesPerfildto.generosVideojuegos());
        }

        usuario.setGeneros(generos);

        if (ajustesPerfildto.avatar() != null && !ajustesPerfildto.avatar().isEmpty()) {
            String imagen = imagenPerfilService.guardarImagen(ajustesPerfildto.avatar());
            usuario.setAvatarUrl(imagen);
        }

        usuarioService.saveAndFlush(usuario);
    }
}

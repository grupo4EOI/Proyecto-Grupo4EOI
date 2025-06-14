package com.atm.buenas_practicas_java.services.facade;

import com.atm.buenas_practicas_java.dtos.GeneroDTO;
import com.atm.buenas_practicas_java.dtos.UsuarioDTO;
import com.atm.buenas_practicas_java.dtos.composedDTOs.AjustesPerfilDTO;
import com.atm.buenas_practicas_java.dtos.composedDTOs.UsuarioPerfilDTO;
import com.atm.buenas_practicas_java.entities.Genero;
import com.atm.buenas_practicas_java.entities.Publicacion;
import com.atm.buenas_practicas_java.entities.Usuario;
import com.atm.buenas_practicas_java.services.AjustesPerfilService;
import com.atm.buenas_practicas_java.services.GeneroService;
import com.atm.buenas_practicas_java.services.PerfilService;
import com.atm.buenas_practicas_java.services.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PerfilServiceFacade {

    private final UsuarioService usuarioService;
    private final AjustesPerfilService ajustesPerfilService;
    private final GeneroService generoService;

    public PerfilServiceFacade(UsuarioService usuarioService, AjustesPerfilService ajustesPerfilService, GeneroService generoService) {
        this.usuarioService = usuarioService;
        this.ajustesPerfilService = ajustesPerfilService;
        this.generoService = generoService;
    }

    public UsuarioPerfilDTO obtenerPerfilDTO(Long idUsuario) {
        Usuario usuario = usuarioService.findById(idUsuario);

        Set<Genero> generos = usuario.getGeneros();

        List<GeneroDTO> peliculas = generoService.filtrarGenerosPorTipo(generos, "pelicula");
        List<GeneroDTO> series = generoService.filtrarGenerosPorTipo(generos, "serie");
        List<GeneroDTO> videojuegos = generoService.filtrarGenerosPorTipo(generos, "videojuego");

        List<Publicacion> publicaciones = usuario.getPublicaciones();

        List<UsuarioDTO> amigos = usuario.getAmigos().stream()
                .map(amistad -> {
                    Usuario amigo = amistad.getAmigo();
                    return new UsuarioDTO(
                            amigo.getIdUsuario(),
                            amigo.getNombreUsuario(),
                            amigo.getAvatarUrl(),
                            amigo.getRole(),
                            amigo.getBaneado()
                    );
                })
                .collect(Collectors.toList());

        return new UsuarioPerfilDTO(
                usuario.getIdUsuario(),
                usuario.getNombreUsuario(),
                usuario.getAvatarUrl(),
                usuario.getBiografia(),
                usuario.getRole(),
                usuario.getResenas(),
                usuario.getComentariosPublicacion(),
                amigos,
                usuario.getReacciones(),
                usuario.getComentariosResenas(),
                usuario.getGeneros(),
                peliculas,
                series,
                videojuegos,
                publicaciones

        );
    }

//    public void editarBiografia(Long idUsuario, String biografia) {
//        Usuario usuario = perfilService.findByIdUsuario(idUsuario);
//        usuario.setBiografia(biografia);
//        perfilService.saveAndFlush(usuario);
//    }

    public AjustesPerfilDTO obtenerAjustesPerfil(Long id) {
        return ajustesPerfilService.obtenerAjustesPerfil(id);
    }

    public void guardarAjustesPerfil(Long idUsuario, AjustesPerfilDTO ajustesPerfil) {
        ajustesPerfilService.actualizarAjustesPerfil(idUsuario, ajustesPerfil);
    }



}

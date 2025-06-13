package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.dtos.GeneroDTO;
import com.atm.buenas_practicas_java.dtos.UsuarioDTO;
import com.atm.buenas_practicas_java.dtos.UsuarioPerfilDTO;
import com.atm.buenas_practicas_java.entities.ComentarioPublicacion;
import com.atm.buenas_practicas_java.entities.Genero;
import com.atm.buenas_practicas_java.entities.Publicacion;
import com.atm.buenas_practicas_java.entities.Usuario;
import com.atm.buenas_practicas_java.mapper.PerfilMapper;
import com.atm.buenas_practicas_java.repositories.ComentarioPublicacionRepository;
import com.atm.buenas_practicas_java.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class PerfilService {
    private final UsuarioRepository usuarioRepository;
    private final GeneroService generoService;
    private final PerfilMapper perfilMapper;
    private final ComentarioPublicacionRepository comentarioPublicacionRepository;

    public PerfilService(UsuarioRepository usuarioRepository,
                         GeneroService generoService,
                         PerfilMapper perfilMapper, ComentarioPublicacionRepository comentarioPublicacionRepository) {
        this.usuarioRepository = usuarioRepository;
        this.generoService = generoService;
        this.perfilMapper = perfilMapper;
        this.comentarioPublicacionRepository = comentarioPublicacionRepository;
    }

    @Transactional
    public Usuario findByIdUsuario(Long idUsuario) {
        Usuario usuario = usuarioRepository.findByIdUsuario(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        usuario.getResenas().size();
        usuario.getPublicaciones().size();
        usuario.getAmigos().size();
        usuario.getGeneros().size();
        usuario.getUsuarios().size();

        return usuario;
    }

    @Transactional
    public void saveAndFlush(Usuario usuario) {
        usuarioRepository.saveAndFlush(usuario);
    }

    @Transactional
    public UsuarioPerfilDTO obtenerPerfilDTO(Long idUsuario) {
        Usuario usuario = findByIdUsuario(idUsuario);

        Set<Genero> generos = usuario.getGeneros();

        List<GeneroDTO> peliculas = perfilMapper.toDtoList(
                generoService.filtrarGenerosPorTipo(generos, "pelicula")
        );
        List<GeneroDTO> series = perfilMapper.toDtoList(
                generoService.filtrarGenerosPorTipo(generos, "serie")
        );
        List<GeneroDTO> videojuegos = perfilMapper.toDtoList(
                generoService.filtrarGenerosPorTipo(generos, "videojuego")
        );

        List<ComentarioPublicacion> publicaciones = comentarioPublicacionRepository.findAllByUsuario_IdUsuario(idUsuario);

        List<UsuarioDTO> amigos = usuario.getAmigos().stream()
                .map(amistad -> {
                    Usuario amigo = amistad.getAmigo();
                    return new UsuarioDTO(
                            amigo.getIdUsuario(),
                            amigo.getNombreUsuario(),
                            amigo.getAvatarUrl(),
                            amigo.getRole()
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
}



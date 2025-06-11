package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.dtos.GeneroDTO;
import com.atm.buenas_practicas_java.dtos.UsuarioDTO;
import com.atm.buenas_practicas_java.dtos.UsuarioPerfilDTO;
import com.atm.buenas_practicas_java.entities.Amistad;
import com.atm.buenas_practicas_java.entities.Genero;
import com.atm.buenas_practicas_java.entities.Usuario;
import com.atm.buenas_practicas_java.mapper.PerfilMapper;
import com.atm.buenas_practicas_java.repositories.PerfilRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class PerfilService {
    private final PerfilRepository perfilRepository;
    private final GeneroService generoService;
    private final PerfilMapper perfilMapper;

    public PerfilService(PerfilRepository perfilRepository,
                         GeneroService generoService,
                         PerfilMapper perfilMapper) {
        this.perfilRepository = perfilRepository;
        this.generoService = generoService;
        this.perfilMapper = perfilMapper;
    }

    public Usuario findByIdUsuario(Long idUsuario) {
        Usuario usuario = perfilRepository.findByIdUsuario(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        usuario.getResenas().size();
        usuario.getAmigos().size();
        usuario.getGeneros().size();
        usuario.getUsuarios().size();

        return usuario;
    }

    public void saveAndFlush(Usuario usuario) {
        perfilRepository.saveAndFlush(usuario);
    }

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

        List<UsuarioDTO> amigosDTO = usuario.getAmigos().stream()
                .filter(Amistad::isEstado) // solo amigos confirmados
                .map(Amistad::getAmigo)
                .map(perfilMapper::toDto)
                .collect(Collectors.toList());


        return new UsuarioPerfilDTO(
                usuario.getIdUsuario(),
                usuario.getNombreUsuario(),
                usuario.getAvatarUrl(),
                usuario.getBiografia(),
                usuario.isEsAdministrador(),
                usuario.getResenas(),
                usuario.getComentariosPublicacion(),
                amigosDTO,
                usuario.getReacciones(),
                usuario.getComentariosResenas(),
                usuario.getGeneros(),
                peliculas,
                series,
                videojuegos
        );
    }

}



package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.dtos.GeneroDTO;
import com.atm.buenas_practicas_java.dtos.UsuarioDTO;
import com.atm.buenas_practicas_java.dtos.UsuarioPerfilDTO;
import com.atm.buenas_practicas_java.entities.Amistad;
import com.atm.buenas_practicas_java.entities.Genero;
import com.atm.buenas_practicas_java.entities.Publicacion;
import com.atm.buenas_practicas_java.entities.Usuario;
import com.atm.buenas_practicas_java.mapper.PerfilMapper;
import com.atm.buenas_practicas_java.repositories.PerfilRepository;
import org.springframework.security.core.context.SecurityContextHolder;
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
        usuario.getPublicaciones().size();
        usuario.getAmigos().size();
        usuario.getGeneros().size();
        usuario.getUsuarios().size();

        return usuario;
    }

    public Usuario findByNombreUsuario(String nombreUsuario) {
        return perfilRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
    }


    public void saveAndFlush(Usuario usuario) {
        perfilRepository.saveAndFlush(usuario);
    }

    public UsuarioPerfilDTO obtenerPerfilDTO(Long idUsuario, Long idOtro) {
        Usuario usuario = findByIdUsuario(idUsuario);

        // Esto es importante: obtener el usuario autenticado
        String nombreUsuarioActual = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuarioActual = perfilRepository.findByNombreUsuario(nombreUsuarioActual)
                .orElseThrow(() -> new RuntimeException("Usuario actual no encontrado"));

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

        List<Publicacion> publicaciones = usuario.getPublicaciones();

        List<UsuarioDTO> amigos = usuario.getAmigos().stream()
                .map(amistad -> {
                    Usuario amigo = amistad.getAmigo();
                    return new UsuarioDTO(amigo.getIdUsuario(), amigo.getNombreUsuario(), amigo.getAvatarUrl(), amigo.getRole());
                })
                .collect(Collectors.toList());

        boolean esAmigo = usuario.getAmigos().stream()
                .anyMatch(amistad -> amistad.getAmigo().getIdUsuario().equals(idOtro));



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
                publicaciones,
                esAmigo

        );
    }

    public void agregarAmigo(Long idUsuarioActual, Long idAmigo) {
        Usuario actual = perfilRepository.findById(idUsuarioActual)
                .orElseThrow(() -> new RuntimeException("Usuario actual no encontrado"));
        Usuario amigo = perfilRepository.findById(idAmigo)
                .orElseThrow(() -> new RuntimeException("Amigo no encontrado"));

        // Verificar si ya existe la amistad en alguna dirección
        boolean yaSonAmigos = actual.getAmigos().stream()
                .anyMatch(amistad -> amistad.getAmigo().getIdUsuario().equals(idAmigo));

        if (!yaSonAmigos) {
            // Crear amistad A -> B
            Amistad amistadAB = new Amistad();
            amistadAB.setUsuario(actual);
            amistadAB.setAmigo(amigo);

            // Crear amistad B -> A
            Amistad amistadBA = new Amistad();
            amistadBA.setUsuario(amigo);
            amistadBA.setAmigo(actual);

            actual.getAmigos().add(amistadAB);
            amigo.getAmigos().add(amistadBA);

            perfilRepository.save(actual);
            perfilRepository.save(amigo);
        }
    }


}



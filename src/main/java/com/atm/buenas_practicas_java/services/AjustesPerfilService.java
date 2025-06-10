package com.atm.buenas_practicas_java.services;


import com.atm.buenas_practicas_java.dtos.AjustesPerfilDTO;
import com.atm.buenas_practicas_java.entities.Genero;
import com.atm.buenas_practicas_java.entities.Usuario;
import com.atm.buenas_practicas_java.mapper.PerfilMapper;
import com.atm.buenas_practicas_java.repositories.GeneroRepository;
import com.atm.buenas_practicas_java.repositories.PerfilRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class AjustesPerfilService {
    private final PerfilRepository perfilRepository;
    private final GeneroService generoService;
    private final PerfilMapper perfilMapper;
    private final GeneroRepository generoRepository;
    private final ImagenPerfilService imagenPerfilService;

    public AjustesPerfilService(PerfilRepository perfilRepository, GeneroService generoService, PerfilMapper perfilMapper, GeneroRepository generoRepository, ImagenPerfilService imagenPerfilService) {
        this.perfilRepository = perfilRepository;
        this.generoService = generoService;
        this.perfilMapper = perfilMapper;
        this.generoRepository = generoRepository;
        this.imagenPerfilService = imagenPerfilService;
    }

    public Usuario findByIdUsuario(Long idUsuario) {
        Usuario usuario = perfilRepository.findByIdUsuario(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        usuario.getUsuarios().size();

        return usuario;
    }

    public void editarBiografia(Long idUsuario, String biografia) {
        Usuario usuario = findByIdUsuario(idUsuario);
        usuario.setBiografia(biografia);
        perfilRepository.saveAndFlush(usuario);
    }

    public AjustesPerfilDTO obtenerAjustesPerfil(Long idUsuario) {
        Usuario usuario = findByIdUsuario(idUsuario);

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


//    public void actualizarAjustesPerfil(Long idUsuario, AjustesPerfilDTO ajustesPerfildto) {
//        Usuario usuario = findByIdUsuario(idUsuario);
//
//        if (ajustesPerfildto.nombreUsuario() != null && !ajustesPerfildto.nombreUsuario().isBlank()) {
//            usuario.setNombreUsuario(ajustesPerfildto.nombreUsuario());
//        }
//
//        if (ajustesPerfildto.contrasena() != null && !ajustesPerfildto.contrasena().isBlank()) {
//            usuario.setContrasena(ajustesPerfildto.contrasena());
//        }
//
//        usuario.setBiografia(ajustesPerfildto.biografia());
//
//        List<Integer> idsGeneros = new ArrayList<>();
//        if (ajustesPerfildto.idsGenerosPeliculas() != null) idsGeneros.addAll(ajustesPerfildto.idsGenerosPeliculas());
//        if (ajustesPerfildto.idsGenerosSeries() != null) idsGeneros.addAll(ajustesPerfildto.idsGenerosSeries());
//        if (ajustesPerfildto.idsGenerosVideojuegos() != null) idsGeneros.addAll(ajustesPerfildto.idsGenerosVideojuegos());
//
//
//        Set<Genero> generos = new HashSet<>(generoRepository.findAllById(idsGeneros));
//        usuario.setGeneros(generos);
//
//        if (ajustesPerfildto.avatar() != null && !ajustesPerfildto.avatar().isEmpty()) {
//            String imagen = imagenPerfilService.guardarImagen(ajustesPerfildto.avatar());
//            usuario.setAvatarUrl(imagen);
//        }
//
//        perfilRepository.saveAndFlush(usuario);
//    }


}

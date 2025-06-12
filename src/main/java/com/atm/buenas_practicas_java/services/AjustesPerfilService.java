package com.atm.buenas_practicas_java.services;


import com.atm.buenas_practicas_java.dtos.AjustesPerfilDTO;
import com.atm.buenas_practicas_java.entities.Genero;
import com.atm.buenas_practicas_java.entities.Usuario;
import com.atm.buenas_practicas_java.mapper.PerfilMapper;
import com.atm.buenas_practicas_java.repositories.GeneroRepository;
import com.atm.buenas_practicas_java.repositories.PerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class AjustesPerfilService {

    private final PerfilRepository perfilRepository;
    private final GeneroService generoService;
    private final PerfilMapper perfilMapper;
    private final GeneroRepository generoRepository;
    private final ImagenPerfilService imagenPerfilService;
    private final PasswordEncoder encoder;



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


    public void actualizarAjustesPerfil(Long idUsuario, AjustesPerfilDTO ajustesPerfildto) {
        Usuario usuario = findByIdUsuario(idUsuario);

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

        perfilRepository.saveAndFlush(usuario);
    }


}

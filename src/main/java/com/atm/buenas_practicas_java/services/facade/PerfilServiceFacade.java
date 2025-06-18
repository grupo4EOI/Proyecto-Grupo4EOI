package com.atm.buenas_practicas_java.services.facade;

import com.atm.buenas_practicas_java.dtos.composedDTOs.AjustesPerfilDTO;
import com.atm.buenas_practicas_java.dtos.composedDTOs.UsuarioPerfilDTO;
import com.atm.buenas_practicas_java.entities.Amistad;
import com.atm.buenas_practicas_java.entities.Genero;
import com.atm.buenas_practicas_java.entities.Usuario;
import com.atm.buenas_practicas_java.repositories.AmistadRepository;
import com.atm.buenas_practicas_java.services.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PerfilServiceFacade {

    private final UsuarioService usuarioService;
    private final ResenaService resenaService;
    private final ComentarioPublicacionService comentarioPublicacionService;
    private final ComentarioResenaService comentarioResenaService;
    private final ImagenPerfilService imagenPerfilService;
    private final ReaccionService reaccionService;
    private final ObjetoUsuarioService objetoUsuarioService;
    private final AmistadService amistadService;
    private final PasswordEncoder encoder;

    @Transactional
    public UsuarioPerfilDTO obtenerPerfilDTO(Long idUsuario, Long idAutenticado) {
        Usuario usuario = usuarioService.findById(idUsuario);

        boolean esAmigo = false;
        if (idAutenticado != null && !idUsuario.equals(idAutenticado)) {
            esAmigo = usuarioService.sonAmigos(idUsuario, idAutenticado);
        }

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
                resenaService.contarResenasUsuario(idUsuario),
                reaccionService.contarReaccionesUsuario(idUsuario),
                objetoUsuarioService.contarObjetosVistosUsuario(idUsuario),
                objetoUsuarioService.contarObjetosPendientesUsuario(idUsuario),
                usuario.getFechaRegistro(),
                esAmigo
        );
    }

    public void editarBiografia(Long idUsuario, String biografia) {
        Usuario usuario = usuarioService.findById(idUsuario);
        usuario.setBiografia(biografia);
        usuarioService.saveAndFlush(usuario);
    }

    @Transactional
    public void agregarAmigo(Long idUsuario, Long idAmigo) {
        if (idUsuario.equals(idAmigo)) return;

        Usuario usuario = usuarioService.findById(idUsuario);
        Usuario amigo = usuarioService.findById(idAmigo);

        Optional<Amistad> existente = amistadService.findByUsuarioAndAmigo(usuario, amigo);
        if (existente.isPresent()) return;

        Amistad amistad = new Amistad();
        amistad.setUsuario(usuario);
        amistad.setAmigo(amigo);
        amistad.setFecha(new Date());
        amistad.setEstado(true);
        amistadService.save(amistad);
    }

    @Transactional
    public void eliminarAmigo(Long idUsuario, Long idAmigo) {
        if (idUsuario.equals(idAmigo)) return;

        Usuario usuario = usuarioService.findById(idUsuario);
        Usuario amigo = usuarioService.findById(idAmigo);

        amistadService.findByUsuarioAndAmigo(usuario, amigo)
                .ifPresent(amistadService::delete);

        amistadService.findByUsuarioAndAmigo(amigo, usuario)
                .ifPresent(amistadService::delete);
    }

    public Long obtenerIdUsuarioPorNombreUsuario(String nombreUsuario) {
        Usuario usuario = usuarioService.findByNombreUsuario(nombreUsuario);
        return usuario.getIdUsuario();
    }

    // Ajustes de perfil
    public AjustesPerfilDTO obtenerAjustesPerfil(Long idUsuario)  {
        Usuario usuario = usuarioService.findById(idUsuario);

        return new AjustesPerfilDTO(
                usuario.getIdUsuario(),
                usuario.getNombreUsuario(),
                null,
                usuario.getBiografia(),
                null
        );
    }

    public void actualizarAjustesPerfil(Long idUsuario, AjustesPerfilDTO ajustesPerfildto) {
        Usuario usuario = usuarioService.findById(idUsuario);

        if (ajustesPerfildto.nombreUsuario() != null && !ajustesPerfildto.nombreUsuario().isBlank()) {
            if (usuarioService.existsByNombreUsuarioAndIdNot(ajustesPerfildto.nombreUsuario(), idUsuario)) {
                throw new IllegalStateException("El nombre de usuario ya está en uso");
            }
            usuario.setNombreUsuario(ajustesPerfildto.nombreUsuario());
        }

        if (ajustesPerfildto.contrasena() != null && !ajustesPerfildto.contrasena().isBlank()) {
            usuario.setContrasena(encoder.encode(ajustesPerfildto.contrasena()));
        }

        usuario.setBiografia(ajustesPerfildto.biografia());

        if (ajustesPerfildto.avatar() != null && !ajustesPerfildto.avatar().isEmpty()) {
            String imagen = imagenPerfilService.guardarImagen(ajustesPerfildto.avatar());
            usuario.setAvatarUrl(imagen);
        }

        usuarioService.saveAndFlush(usuario);
    }
}

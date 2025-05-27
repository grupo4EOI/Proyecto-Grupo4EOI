package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.entities.ComentarioPublicacion;
import com.atm.buenas_practicas_java.entities.Publicacion;
import com.atm.buenas_practicas_java.entities.Usuario;
import com.atm.buenas_practicas_java.repositories.PublicacionRepository;
import com.atm.buenas_practicas_java.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PublicacionRepository publicacionRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, PublicacionRepository publicacionRepository) {
        this.usuarioRepository = usuarioRepository;
        this.publicacionRepository = publicacionRepository;
    }

    public Usuario findUsuarioByPublicacion(Long idPublicacion) {
        Publicacion publicacion = publicacionRepository.findById(idPublicacion).get();
        ComentarioPublicacion comentario = publicacion.getComentariosPublicacion().getFirst();
        return comentario.getUsuario();
    }
}

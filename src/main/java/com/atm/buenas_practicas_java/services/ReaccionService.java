package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.entities.Reaccion;
import com.atm.buenas_practicas_java.entities.Resena;
import com.atm.buenas_practicas_java.entities.Usuario;
import com.atm.buenas_practicas_java.repositories.ReaccionRepository;
import com.atm.buenas_practicas_java.repositories.ResenaRepository;
import com.atm.buenas_practicas_java.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class ReaccionService {

    private final ReaccionRepository reaccionRepository;
    private final UsuarioRepository usuarioRepository;
    private final ResenaRepository resenaRepository;

    public ReaccionService(ReaccionRepository reaccionRepository, UsuarioRepository usuarioRepository, ResenaRepository resenaRepository) {
        this.reaccionRepository = reaccionRepository;
        this.usuarioRepository = usuarioRepository;
        this.resenaRepository = resenaRepository;
    }

    // Metodo para marcar like o bien quitar el like si ya le ha dado like anteriormente
    @Transactional
    public void marcarQuitarLikeResena(Long idResena, String nombreUsuario) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario).orElseThrow(EntityNotFoundException::new);
        Resena resena = resenaRepository.findById(idResena).orElseThrow(EntityNotFoundException::new);

        Optional<Reaccion> existente = reaccionRepository.findByResena_IdResenaAndUsuario_IdUsuario(idResena, usuario.getIdUsuario());

        if (!existente.isPresent()) {
            Reaccion reaccion = new Reaccion();
            reaccion.setResena(resena);
            reaccion.setUsuario(usuario);
            reaccion.setMeGusta(true);
            usuario.getReacciones().add(reaccion);
            reaccionRepository.save(reaccion);
            usuarioRepository.save(usuario);
        } else {
            reaccionRepository.deleteReaccionByIdReaccion(existente.get().getIdReaccion());
        }
    }
}

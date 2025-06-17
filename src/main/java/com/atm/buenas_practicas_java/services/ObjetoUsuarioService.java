package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.entities.ObjetoUsuario;
import com.atm.buenas_practicas_java.repositories.ObjetoRepository;
import com.atm.buenas_practicas_java.repositories.ObjetoUsuarioRepository;
import com.atm.buenas_practicas_java.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class ObjetoUsuarioService {

    private final ObjetoUsuarioRepository objetoUsuarioRepository;

    public ObjetoUsuarioService(ObjetoUsuarioRepository objetoUsuarioRepository) {
        this.objetoUsuarioRepository = objetoUsuarioRepository;
    }

    public void save(ObjetoUsuario objetoUsuario) {
        objetoUsuarioRepository.save(objetoUsuario);
    }

    public Long contarObjetosVistosUsuario(Long idUsuario) {
        return objetoUsuarioRepository.countByUsuario_IdUsuarioAndEstado(idUsuario, true);
    }

    public Long contarObjetosPendientesUsuario(Long idUsuario) {
        return objetoUsuarioRepository.countByUsuario_IdUsuarioAndEstado(idUsuario, false);
    }
}

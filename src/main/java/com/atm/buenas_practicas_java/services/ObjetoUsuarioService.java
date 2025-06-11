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

    public Boolean usuarioObjetoVisto(Long idObjeto, Long idUsuario) {
        return objetoUsuarioRepository.findByObjeto_IdObjetoAndUsuario_IdUsuario(idObjeto, idUsuario)
                .map(ObjetoUsuario::getEstado)
                .orElse(null);
    }

    public void save(ObjetoUsuario objetoUsuario) {
        objetoUsuarioRepository.save(objetoUsuario);
    }
}

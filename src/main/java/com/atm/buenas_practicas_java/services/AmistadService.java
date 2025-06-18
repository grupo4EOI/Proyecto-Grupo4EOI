package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.entities.Amistad;
import com.atm.buenas_practicas_java.entities.Usuario;
import com.atm.buenas_practicas_java.repositories.AmistadRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AmistadService {

    private final AmistadRepository amistadRepository;

    public AmistadService (AmistadRepository amistadRepository) {
        this.amistadRepository = amistadRepository;
    }

    public Optional<Amistad> findByUsuarioAndAmigo(Usuario usuario, Usuario amigo) {
        return amistadRepository.findByUsuarioAndAmigo(usuario, amigo);
    }

    public void save(Amistad amistad) {
        amistadRepository.save(amistad);
    }

    public void delete(Amistad amistad) {
        amistadRepository.delete(amistad);
    }

}

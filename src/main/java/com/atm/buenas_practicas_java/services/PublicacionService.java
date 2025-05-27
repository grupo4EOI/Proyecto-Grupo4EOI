package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.entities.Comunidad;
import com.atm.buenas_practicas_java.entities.Publicacion;
import com.atm.buenas_practicas_java.repositories.PublicacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicacionService {

    private final PublicacionRepository publicacionRepository;

    public PublicacionService(PublicacionRepository publicacionRepository) {
        this.publicacionRepository = publicacionRepository;
    }

    public List<Publicacion> getPublicacionsByComunidad(Comunidad comunidad) {
        return publicacionRepository.getPublicacionsByComunidad(comunidad);
    }
}

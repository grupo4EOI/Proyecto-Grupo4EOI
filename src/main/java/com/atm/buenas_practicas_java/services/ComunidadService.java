package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.entities.Comunidad;
import com.atm.buenas_practicas_java.repositories.ComunidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComunidadService {

    private final ComunidadRepository comunidadRepository;

    public ComunidadService(ComunidadRepository comunidadRepository) {
        this.comunidadRepository = comunidadRepository;
    }

    public Comunidad save (Comunidad comunidad) {return comunidadRepository.save(comunidad);}

    public List<Comunidad> findAll() {return comunidadRepository.findAll();}

    public Comunidad findById(Long id) {return comunidadRepository.findComunidadByIdComunidad(id);}
}

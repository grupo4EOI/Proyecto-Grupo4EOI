package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.entities.Tipo;
import com.atm.buenas_practicas_java.repositories.TipoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TipoService {

    private final TipoRepository tipoRepository;

    public TipoService(TipoRepository tipoRepository) {
        this.tipoRepository = tipoRepository;
    }

    public Tipo findByNombre(String nombre) {
        return tipoRepository.findByNombre(nombre).orElseThrow(EntityNotFoundException::new);
    }

}

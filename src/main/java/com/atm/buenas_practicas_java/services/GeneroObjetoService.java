package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.repositories.GeneroObjetoRepository;
import org.springframework.stereotype.Service;

@Service
public class GeneroObjetoService {

    private final GeneroObjetoRepository generoObjetoRepository;

    public GeneroObjetoService(GeneroObjetoRepository generoObjetoRepository) {
        this.generoObjetoRepository = generoObjetoRepository;
    }
}

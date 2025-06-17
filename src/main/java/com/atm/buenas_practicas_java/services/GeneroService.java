package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.dtos.GeneroDTO;
import com.atm.buenas_practicas_java.entities.Genero;
import com.atm.buenas_practicas_java.entities.Objeto;
import com.atm.buenas_practicas_java.mapper.GeneroMapper;
import com.atm.buenas_practicas_java.repositories.GeneroRepository;
import com.atm.buenas_practicas_java.repositories.ObjetoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class GeneroService {

    private final GeneroRepository generoRepository;

    public GeneroService( GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    public Genero save(Genero genero) {
        return generoRepository.save(genero);
    }

    public Optional<Genero> findByNombreAndTipo(String nombre, String tipo) {
        return generoRepository.findByNombreAndTipo_Nombre(nombre, tipo);
    }
}

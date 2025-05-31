package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.entities.Genero;
import com.atm.buenas_practicas_java.entities.Objeto;
import com.atm.buenas_practicas_java.repositories.ObjetoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GeneroService {
    private final ObjetoRepository objetoRepository;

    public GeneroService(ObjetoRepository objetoRepository) {
        this.objetoRepository = objetoRepository;
    }

    public Set<Genero> obtenerGenerosPorObjeto(Long idObjeto) {
        Objeto objeto = objetoRepository.findById(idObjeto).orElseThrow(()-> new RuntimeException("Objeto no encontrado"));
        return objeto.getGeneros();
    }
}

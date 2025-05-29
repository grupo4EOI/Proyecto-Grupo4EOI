package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.entities.Genero;
import com.atm.buenas_practicas_java.entities.GeneroObjeto;
import com.atm.buenas_practicas_java.entities.Objeto;
import com.atm.buenas_practicas_java.repositories.GeneroObjetoRepository;
import com.atm.buenas_practicas_java.repositories.ObjetoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GeneroService {
    private final GeneroObjetoRepository generoObjetoRepository;
    private final ObjetoRepository objetoRepository;

    public GeneroService(GeneroObjetoRepository generoObjetoRepository, ObjetoRepository objetoRepository) {
        this.generoObjetoRepository = generoObjetoRepository;
        this.objetoRepository = objetoRepository;
    }

    public List<Genero> obtenerGenerosPorObjeto(Long idObjeto) {
        Optional<Objeto> objeto = objetoRepository.findById(idObjeto);

        if (objeto.isPresent()) {
            List<GeneroObjeto> relaciones = generoObjetoRepository.findByObjeto(objeto);
        }



        return relaciones.stream()
                .map(GeneroObjeto::getGenero)
                .collect(Collectors.toList());
    }
}

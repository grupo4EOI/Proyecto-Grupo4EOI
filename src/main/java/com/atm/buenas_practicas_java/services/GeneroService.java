package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.entities.Genero;
import com.atm.buenas_practicas_java.entities.Objeto;
import com.atm.buenas_practicas_java.repositories.GeneroRepository;
import com.atm.buenas_practicas_java.repositories.ObjetoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GeneroService {
    private final ObjetoRepository objetoRepository;
    private final GeneroRepository generoRepository;

    public GeneroService(ObjetoRepository objetoRepository, GeneroRepository generoRepository) {
        this.objetoRepository = objetoRepository;
        this.generoRepository = generoRepository;
    }

    public Set<Genero> obtenerGenerosPorObjeto(Long idObjeto) {
        Objeto objeto = objetoRepository.findById(idObjeto).orElseThrow(()-> new RuntimeException("Objeto no encontrado"));
        return objeto.getGeneros();
    }

    public List<Genero> filtrarGenerosPorTipo(Set<Genero> generos, String tipoNombre) {
        return generos.stream()
                .filter(g -> g.getTipo().getNombre().equalsIgnoreCase(tipoNombre))
                .toList();
    }

    public List<Genero> obtenerGenerosPorTipo(String tipoNombre) {
        return generoRepository.findByTipoNombreIgnoreCase(tipoNombre);
    }
}

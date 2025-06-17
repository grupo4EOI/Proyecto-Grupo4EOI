package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.dtos.GeneroDTO;
import com.atm.buenas_practicas_java.entities.Genero;
import com.atm.buenas_practicas_java.entities.Objeto;
import com.atm.buenas_practicas_java.mapper.GeneroMapper;
import com.atm.buenas_practicas_java.repositories.GeneroRepository;
import com.atm.buenas_practicas_java.repositories.ObjetoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GeneroService {
    private final ObjetoRepository objetoRepository;
    private final GeneroRepository generoRepository;
    private final GeneroMapper generoMapper;

    public GeneroService(ObjetoRepository objetoRepository, GeneroRepository generoRepository, GeneroMapper generoMapper) {
        this.objetoRepository = objetoRepository;
        this.generoRepository = generoRepository;
        this.generoMapper = generoMapper;
    }

    public Genero save(Genero genero) {
        return generoRepository.save(genero);
    }

    public Set<Genero> obtenerGenerosPorObjeto(Long idObjeto) {
        Objeto objeto = objetoRepository.findById(idObjeto).orElseThrow(()-> new RuntimeException("Objeto no encontrado"));
        return objeto.getGeneros();
    }

    public List<GeneroDTO> filtrarGenerosPorTipo(Set<Genero> generos, String tipoNombre) {
        return generoMapper.toDtoList(generos.stream()
                .filter(g -> g.getTipo().getNombre().equalsIgnoreCase(tipoNombre))
                .toList());
    }

    public List<Genero> obtenerGenerosPorTipo(String tipoNombre) {
        return generoRepository.findByTipoNombreIgnoreCase(tipoNombre);
    }

    public Optional<Genero> findByNombreAndTipo(String nombre, String tipo) {
        return generoRepository.findByNombreAndTipo_Nombre(nombre, tipo);
    }
}

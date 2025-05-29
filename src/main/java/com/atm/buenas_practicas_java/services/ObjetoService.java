package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.entities.Objeto;
import com.atm.buenas_practicas_java.entities.Resena;
import com.atm.buenas_practicas_java.repositories.ObjetoRepository;
import com.atm.buenas_practicas_java.repositories.ResenaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObjetoService {

    private final ObjetoRepository objetoRepository;
    private final ResenaRepository resenaRepository;

    public ObjetoService(ObjetoRepository objetoRepository, ResenaRepository resenaRepository) {
        this.objetoRepository = objetoRepository;
        this.resenaRepository = resenaRepository;
    }

    public Objeto save(Objeto objeto) {
        return objetoRepository.save(objeto);
    }

    public List<Objeto> findAll() {
        return objetoRepository.findAll();
    }

    public Objeto findById(Long id) {
        return objetoRepository.findById(id).orElseThrow(() -> new RuntimeException("Objeto no encontrado"));
    }

    public Double calcularPuntuacionObjeto(Long idObjeto) {
        List<Resena> resenas = resenaRepository.findResenasByObjeto_IdObjeto(idObjeto);
        return resenas.stream().mapToDouble(Resena::getPuntuacion).sum() / (resenas.size());
    }

    public int calcularNumeroResenas(Long idObjeto) {
        return resenaRepository.findResenasByObjeto_IdObjeto(idObjeto).size();
    }

}

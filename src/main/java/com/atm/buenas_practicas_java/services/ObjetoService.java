package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.entities.Objeto;
import com.atm.buenas_practicas_java.entities.Resena;
import com.atm.buenas_practicas_java.repositories.ObjetoRepository;
import com.atm.buenas_practicas_java.repositories.ResenaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return objetoRepository.findObjetoByIdObjeto(id);
    }

    public Double calcularPuntuacionObjeto(Objeto objeto) {
        List<Resena> resenas = resenaRepository.findResenasByObjeto(objeto);
        return resenas.stream().mapToDouble(Resena::getPuntuacion).sum() / (resenas.size());
    }

    public int calcularNumeroResenas(Objeto objeto) {
        return resenaRepository.findResenasByObjeto(objeto).size();
    }

}

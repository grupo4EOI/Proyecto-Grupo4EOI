package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.entities.Objeto;
import com.atm.buenas_practicas_java.repositories.ObjetoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjetoService {

    private final ObjetoRepository objetoRepository;

    public ObjetoService(ObjetoRepository objetoRepository) {
        this.objetoRepository = objetoRepository;
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
}

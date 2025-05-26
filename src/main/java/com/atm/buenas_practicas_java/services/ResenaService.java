package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.entities.Objeto;
import com.atm.buenas_practicas_java.entities.Resena;
import com.atm.buenas_practicas_java.repositories.ResenaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResenaService {

    private ResenaRepository resenaRepository;

    public ResenaService(ResenaRepository resenaRepository) {
        this.resenaRepository = resenaRepository;
    }

    public List<Resena> findResenasByObjeto(Objeto objeto) {
        return resenaRepository.findResenasByObjeto(objeto);
    }
}

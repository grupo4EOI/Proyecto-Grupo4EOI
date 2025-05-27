package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.entities.Objeto;
import com.atm.buenas_practicas_java.entities.Resena;
import com.atm.buenas_practicas_java.repositories.ObjetoRepository;
import com.atm.buenas_practicas_java.repositories.ResenaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Service
public class ResenaService {

    private final ObjetoRepository objetoRepository;
    private ResenaRepository resenaRepository;

    public ResenaService(ResenaRepository resenaRepository, ObjetoRepository objetoRepository) {
        this.resenaRepository = resenaRepository;
        this.objetoRepository = objetoRepository;
    }

    public List<Resena> findResenasByObjeto(Objeto objeto) {
        return resenaRepository.findResenasByObjeto(objeto);
    }

    public Resena nuevaResena(Long idObjeto, @ModelAttribute("nuevaResena") Resena resena) {
        Objeto objeto = objetoRepository.findById(idObjeto).get();
        resena.setObjeto(objeto);
        return resenaRepository.save(resena);
    }
}

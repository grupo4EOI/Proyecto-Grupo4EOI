package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.entities.ComentarioResena;
import com.atm.buenas_practicas_java.entities.Resena;
import com.atm.buenas_practicas_java.repositories.ComentarioResenaRepository;
import com.atm.buenas_practicas_java.repositories.ResenaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioResenaService {

    private final ComentarioResenaRepository comentarioResenaRepository;
    private final ResenaRepository resenaRepository;

    public ComentarioResenaService(ComentarioResenaRepository comentarioResenaRepository,
                                   ResenaRepository resenaRepository) {
        this.comentarioResenaRepository = comentarioResenaRepository;
        this.resenaRepository = resenaRepository;
    }

}

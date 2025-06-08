package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.dtos.ComentarioResenaDTO;
import com.atm.buenas_practicas_java.entities.ComentarioResena;
import com.atm.buenas_practicas_java.entities.Resena;
import com.atm.buenas_practicas_java.mapper.ComentarioResenaMapper;
import com.atm.buenas_practicas_java.repositories.ComentarioResenaRepository;
import com.atm.buenas_practicas_java.repositories.ResenaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioResenaService {

    private final ComentarioResenaRepository comentarioResenaRepository;
    private final ComentarioResenaMapper comentarioResenaMapper;

    public ComentarioResenaService(ComentarioResenaRepository comentarioResenaRepository,
                                   ComentarioResenaMapper comentarioResenaMapper) {
        this.comentarioResenaRepository = comentarioResenaRepository;
        this.comentarioResenaMapper = comentarioResenaMapper;
    }

    public void save(ComentarioResena comentarioResena) {
        comentarioResenaRepository.save(comentarioResena);
    }

    public List<ComentarioResenaDTO> obtenerComentariosResenasConAbuso() {
        List<ComentarioResena> comentarios = comentarioResenaRepository.findComentarioResenasByAbusoEquals(true);
        return comentarioResenaMapper.toDtoList(comentarios);
    }

}

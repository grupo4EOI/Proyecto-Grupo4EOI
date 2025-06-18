package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.dtos.ComentarioResenaDTO;
import com.atm.buenas_practicas_java.entities.ComentarioResena;
import com.atm.buenas_practicas_java.mapper.ComentarioResenaMapper;
import com.atm.buenas_practicas_java.repositories.ComentarioResenaRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public void save(ComentarioResena comentarioResena) {
        comentarioResenaRepository.save(comentarioResena);
    }

    @Transactional
    public List<ComentarioResenaDTO> obtenerComentariosResenasConAbuso() {
        List<ComentarioResena> comentarios = comentarioResenaRepository.findComentarioResenasByAbusoEquals(true);
        return comentarioResenaMapper.toDtoList(comentarios);
    }

    @Transactional
    public List<ComentarioResenaDTO> obtenerComentariosResenasUsuario(Long idUsuario) {
        List<ComentarioResena> comentarios = comentarioResenaRepository.findComentarioResenasByUsuario_IdUsuario(idUsuario);
        return comentarioResenaMapper.toDtoList(comentarios);
    }

    @Transactional
    public void reportarComentarioResena(Long idComentarioResena) {
        comentarioResenaRepository.reportarComentarioResena(idComentarioResena);
    }

    @Transactional
    public void reportarSpoilerComentarioResena(Long idComentarioResena) {
        comentarioResenaRepository.reportarSpoilerComentarioResena(idComentarioResena);
    }

    @Transactional
    public void borrarComentarioResena(Long idComentarioResena) {
        comentarioResenaRepository.borrarComentarioResena(idComentarioResena);
    }

    @Transactional
    public void aprobarComentarioResena(Long idComentarioResena) {
        comentarioResenaRepository.aprobarComentarioResena(idComentarioResena);
    }

    public Long contarComentariosResenaConAbuso() {
        return comentarioResenaRepository.countComentarioResenasByAbusoEquals(true);
    }
}

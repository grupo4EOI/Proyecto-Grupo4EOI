package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.dtos.ResenaDTO;
import com.atm.buenas_practicas_java.entities.Objeto;
import com.atm.buenas_practicas_java.entities.Resena;
import com.atm.buenas_practicas_java.mapper.ResenaMapper;
import com.atm.buenas_practicas_java.repositories.ObjetoRepository;
import com.atm.buenas_practicas_java.repositories.ResenaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Service
public class ResenaService {

    private final ObjetoRepository objetoRepository;
    private ResenaRepository resenaRepository;
    private ResenaMapper resenaMapper;

    public ResenaService(ResenaRepository resenaRepository, ObjetoRepository objetoRepository, ResenaMapper resenaMapper) {
        this.resenaRepository = resenaRepository;
        this.objetoRepository = objetoRepository;
        this.resenaMapper = resenaMapper;
    }

    public Resena findById(Long idResena) {
        return resenaRepository.findById(idResena).orElseThrow();
    }

    public List<ResenaDTO> findResenasByObjeto(Long idObjeto) {
        return resenaMapper.toDtoList(resenaRepository.findResenasByObjeto_IdObjetoOrderByFechaPublicacionDesc(idObjeto));
    }

    public List<ResenaDTO> obtenerResenasConAbuso() {
        List<Resena> resenasReportadas = resenaRepository.findResenasByAbusoEquals(true);
        return resenaMapper.toDtoList(resenasReportadas);
    }

    public ResenaDTO obtenerUltimaResena() {
        Resena ultimaResena = resenaRepository.findTopByOrderByFechaPublicacionDesc();
        return resenaMapper.toDto(ultimaResena);
    }

    public Resena save(Resena resena) {
        return resenaRepository.save(resena);
    }

    public void reportarResena(Long idResena) {
        resenaRepository.reportarResena(idResena);
    }

    public void reportarSpoilerResena(Long idResena) {
        resenaRepository.reportarSpoilerResena(idResena);
    }
}

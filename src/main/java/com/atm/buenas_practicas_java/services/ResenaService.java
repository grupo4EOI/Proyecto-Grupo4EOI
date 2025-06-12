package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.dtos.ResenaDTO;
import com.atm.buenas_practicas_java.entities.Objeto;
import com.atm.buenas_practicas_java.entities.Resena;
import com.atm.buenas_practicas_java.mapper.ResenaMapper;
import com.atm.buenas_practicas_java.repositories.ObjetoRepository;
import com.atm.buenas_practicas_java.repositories.ReaccionRepository;
import com.atm.buenas_practicas_java.repositories.ResenaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Service
public class ResenaService {

    private final ObjetoRepository objetoRepository;
    private final ReaccionRepository reaccionRepository;
    private ResenaRepository resenaRepository;
    private ResenaMapper resenaMapper;

    public ResenaService(ResenaRepository resenaRepository, ObjetoRepository objetoRepository, ResenaMapper resenaMapper, ReaccionRepository reaccionRepository) {
        this.resenaRepository = resenaRepository;
        this.objetoRepository = objetoRepository;
        this.resenaMapper = resenaMapper;
        this.reaccionRepository = reaccionRepository;
    }

    public Resena findById(Long idResena) {
        return resenaRepository.findById(idResena).orElseThrow();
    }

    // Metodos auxiliares para rellenar el DTO de Reseña con el numero de likes
    private ResenaDTO rellenarResenaDTO(ResenaDTO resenaDTO) {
        return new ResenaDTO(
                resenaDTO.idResena(),
                resenaDTO.titulo(),
                resenaDTO.contenido(),
                resenaDTO.puntuacion(),
                resenaDTO.spoiler(),
                resenaDTO.autor(),
                resenaDTO.comentariosResena(),
                resenaDTO.fechaPublicacion(),
                reaccionRepository.countByResena_IdResenaAndMeGustaEquals(resenaDTO.idResena(), true)
        );
    }

    private List<ResenaDTO> rellenarListaResenaDTO(List<ResenaDTO> resenasIncompletas) {
        return resenasIncompletas.stream()
                .map(resenaDTO -> rellenarResenaDTO(resenaDTO)).toList();
    }

    public List<ResenaDTO> findResenasByObjeto(Long idObjeto) {
        List<ResenaDTO> resenasDTO =  resenaMapper.toDtoList(resenaRepository.findResenasByObjeto_IdObjetoOrderByFechaPublicacionDesc(idObjeto));
        return rellenarListaResenaDTO(resenasDTO);
    }

    public List<ResenaDTO> obtenerResenasConAbuso() {
        List<ResenaDTO> resenasReportadas = resenaMapper.toDtoList(resenaRepository.findResenasByAbusoEquals(true));
        return rellenarListaResenaDTO(resenasReportadas);
    }

    public ResenaDTO obtenerUltimaResena() {
        ResenaDTO ultimaResena = resenaMapper.toDto(resenaRepository.findTopByOrderByFechaPublicacionDesc());
        return rellenarResenaDTO(ultimaResena);
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

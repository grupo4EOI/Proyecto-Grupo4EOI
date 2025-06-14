package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.dtos.ComentarioPublicacionDTO;
import com.atm.buenas_practicas_java.dtos.ComentarioPublicacionSimpleDTO;
import com.atm.buenas_practicas_java.entities.ComentarioPublicacion;
import com.atm.buenas_practicas_java.entities.Comunidad;
import com.atm.buenas_practicas_java.entities.Objeto;
import com.atm.buenas_practicas_java.entities.Publicacion;
import com.atm.buenas_practicas_java.mapper.ComentarioPublicacionSimpleMapper;
import com.atm.buenas_practicas_java.repositories.ComentarioPublicacionRepository;
import com.atm.buenas_practicas_java.mapper.ComentarioPublicacionMapper;
import com.atm.buenas_practicas_java.repositories.ObjetoRepository;
import com.atm.buenas_practicas_java.repositories.PublicacionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ComentarioPublicacionService {

    private final ObjetoRepository objetoRepository;
    private final PublicacionRepository publicacionRepository;
    private final ComentarioPublicacionRepository comPubRepository;
    private final ComentarioPublicacionMapper comentarioPublicacionMapper;
    private final ComentarioPublicacionRepository comentarioPublicacionRepository;
    private final ComentarioPublicacionSimpleMapper comPubSimpleMapper;

    public ComentarioPublicacionService(ObjetoRepository objetoRepository,
                                        PublicacionRepository publicacionRepository,
                                        ComentarioPublicacionMapper comentarioPublicacionMapper,
                                        ComentarioPublicacionRepository comPubRepository, ComentarioPublicacionRepository comentarioPublicacionRepository,
                                        ComentarioPublicacionSimpleMapper comentarioPublicacionSimpleMapper) {
        this.objetoRepository = objetoRepository;
        this.publicacionRepository = publicacionRepository;
        this.comentarioPublicacionMapper = comentarioPublicacionMapper;
        this.comPubRepository = comPubRepository;
        this.comentarioPublicacionRepository = comentarioPublicacionRepository;
        this.comPubSimpleMapper = comentarioPublicacionSimpleMapper;
    }

    /** Devuelve una lista de los primeros comentarios de cada publicación pertenecientes a la comunidad del objeto */
    @Transactional
    public List<ComentarioPublicacionDTO> getPrimerosComentariosObjeto(Long idObjeto) {
        return objetoRepository.findById(idObjeto)
                .map(objeto -> {
                    Comunidad comunidad = objeto.getComunidad();
                    return publicacionRepository.getPublicacionsByComunidad(comunidad)
                            .stream()
                            .map(publicacion -> {
                                ComentarioPublicacion comentario = publicacion.getComentariosPublicacion().getFirst();
                                ComentarioPublicacionDTO dto = comentarioPublicacionMapper.toDto(comentario);

                                return new ComentarioPublicacionDTO(
                                        dto.idComentarioPublicacion(),
                                        publicacion.getTitulo(),
                                        dto.contenido(),
                                        dto.baneado(),
                                        dto.usuario(),
                                        dto.fecha()
                                );
                            })
                            .toList();
                }).orElseThrow(() -> new RuntimeException("No se encontró el objeto"));
    }

    @Transactional
    public List<ComentarioPublicacionSimpleDTO> getComentarioPublicacionByPublicacionId(Long publicacionId){
        List<ComentarioPublicacionSimpleDTO> comPubSimpleDTO = comPubSimpleMapper.toDto(comentarioPublicacionRepository.findComentarioPublicacionsByPublicacion_IdPublicacionOrderByIdComentarioPublicacion(publicacionId));
        return comPubSimpleDTO.stream()
                .map(comentario -> new ComentarioPublicacionSimpleDTO(
                        comentario.publicacion(),
                        comentario.idComentarioPublicacion(),
                        comentario.contenido(),
                        comentario.usuario(),
                        comentario.fecha(),
                        comentario.comentarioCitado()
                        )
                    ).toList();
    }

    @Transactional
    public ComentarioPublicacionDTO buscarPrimerComentarioUltimaPublicacion() {
        Publicacion ultimaPublicacion = publicacionRepository.findAll().getLast();
        ComentarioPublicacionDTO primerComentarioDTO = comentarioPublicacionMapper.toDto(ultimaPublicacion.getComentariosPublicacion().getFirst());
        return new ComentarioPublicacionDTO(
                primerComentarioDTO.idComentarioPublicacion(),
                ultimaPublicacion.getTitulo(),
                primerComentarioDTO.contenido(),
                primerComentarioDTO.baneado(),
                primerComentarioDTO.usuario(),
                primerComentarioDTO.fecha()
        );
    }

    @Transactional
    public List<ComentarioPublicacionDTO> obtenerComentariosPublicacionConAbuso() {
        List<ComentarioPublicacion> publicaciones = comentarioPublicacionRepository.findComentarioPublicacionsByAbusoEquals(true);
        return publicaciones.stream()
                .map(comentario -> {
                    String titulo = comentario.getPublicacion().getTitulo();
                    ComentarioPublicacionDTO comentarioDTO = comentarioPublicacionMapper.toDto(comentario);
                    return new ComentarioPublicacionDTO(
                            comentarioDTO.idComentarioPublicacion(),
                            titulo,
                            comentarioDTO.contenido(),
                            comentarioDTO.baneado(),
                            comentarioDTO.usuario(),
                            comentarioDTO.fecha()
                    );
                })
                .toList();
    }

    public ComentarioPublicacion save(ComentarioPublicacion comentarioPublicacion) {
        return comPubRepository.save(comentarioPublicacion);
    }

    public ComentarioPublicacion findById(Long id) {
        return comPubRepository.findById(id).orElse(null);
    }

    // Para notificar al admin que revise el comentario de la publicación
    public void reportar(Long idComentarioPublicacion) {
        comPubRepository.reportar(idComentarioPublicacion);
    }

    // Para cuando el admin confirme que el comentario es indebido
    public void banComentarioPublicacion(Long idComentarioPublicacion) {
        comPubRepository.banComentarioPublicacion(idComentarioPublicacion);
    }

    public void aprobarComentarioPublicacion(Long idComentarioPublicacion) {
        comPubRepository.aprobarComentarioPublicacion(idComentarioPublicacion);
    }

    public Long contarComentariosPublicacionesConAbuso() {
        return comPubRepository.countComentarioPublicacionsByAbusoEquals(true);
    }
}

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
                                        publicacion.getTitulo(),
                                        dto.contenido(),
                                        dto.usuario(),
                                        dto.fecha()
                                );
                            })
                            .toList();
                }).orElseThrow(() -> new RuntimeException("No se encontró el objeto"));
    }

    public List<ComentarioPublicacionSimpleDTO> getComentarioPublicacionByPublicacionId(Long publicacionId){
        List<ComentarioPublicacionSimpleDTO> comPubSimpleDTO = comPubSimpleMapper.toDto(comentarioPublicacionRepository.findComentarioPublicacionsByPublicacion_IdPublicacion(publicacionId));
        return comPubSimpleDTO.stream()
                .map(comentario -> new ComentarioPublicacionSimpleDTO(
                        comentario.publicacion(),
                        comentario.idComentarioPublicacion(),
                        comentario.contenido(),
                        comentario.usuario(),
                        comentario.fecha()
                        )
                    ).toList();
    }

    public List<ComentarioPublicacionDTO> buscarComentariosPublicacionConAbuso() {
        List<ComentarioPublicacion> publicaciones = comentarioPublicacionRepository.findComentarioPublicacionsByAbusoEquals(true);
        return publicaciones.stream()
                .map(comentario -> {
                    String titulo = comentario.getPublicacion().getTitulo();
                    ComentarioPublicacionDTO comentarioDTO = comentarioPublicacionMapper.toDto(comentario);
                    return new ComentarioPublicacionDTO(
                            titulo,
                            comentarioDTO.contenido(),
                            comentarioDTO.usuario(),
                            comentarioDTO.fecha()
                    );
                })
                .toList();
    }

    public ComentarioPublicacion save(ComentarioPublicacion comentarioPublicacion) {
        return comPubRepository.save(comentarioPublicacion);
    }
}

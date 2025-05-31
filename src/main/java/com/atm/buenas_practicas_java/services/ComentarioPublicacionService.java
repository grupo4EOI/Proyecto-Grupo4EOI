package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.dtos.ComentarioPublicacionDTO;
import com.atm.buenas_practicas_java.entities.ComentarioPublicacion;
import com.atm.buenas_practicas_java.entities.Comunidad;
import com.atm.buenas_practicas_java.entities.Objeto;
import com.atm.buenas_practicas_java.entities.Publicacion;
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

    public ComentarioPublicacionService(ObjetoRepository objetoRepository,
                                        PublicacionRepository publicacionRepository,
                                        ComentarioPublicacionMapper comentarioPublicacionMapper,
                                       ComentarioPublicacionRepository comPubRepository) {
        this.objetoRepository = objetoRepository;
        this.publicacionRepository = publicacionRepository;
        this.comentarioPublicacionMapper = comentarioPublicacionMapper;
      this.comPubRepository = comPubRepository;
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
                                        dto.reacciones()
                                );
                            })
                            .toList();
                }).orElseThrow(() -> new RuntimeException("No se encontró el objeto"));
    }

    public List<ComentarioPublicacion> getComentarioPublicacionByPublicacionId(Long publicacionId){
        return publicacionRepository.findById(publicacionId).get().getComentariosPublicacion();
    }

    public ComentarioPublicacion save(ComentarioPublicacion comentarioPublicacion) {
        return comPubRepository.save(comentarioPublicacion);
    }

}

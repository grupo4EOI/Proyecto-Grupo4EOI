package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.dtos.ComentarioPublicacionDTO;
import com.atm.buenas_practicas_java.entities.ComentarioPublicacion;
import com.atm.buenas_practicas_java.entities.Comunidad;
import com.atm.buenas_practicas_java.entities.Objeto;
import com.atm.buenas_practicas_java.entities.Publicacion;
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
    private final ComentarioPublicacionMapper comentarioPublicacionMapper;

    public ComentarioPublicacionService(ObjetoRepository objetoRepository,
                                        PublicacionRepository publicacionRepository,
                                        ComentarioPublicacionMapper comentarioPublicacionMapper) {
        this.objetoRepository = objetoRepository;
        this.publicacionRepository = publicacionRepository;
        this.comentarioPublicacionMapper = comentarioPublicacionMapper;
    }

    /** Devuelve una lista de los primeros comentarios de cada publicación pertenecientes a la comunidad del objeto */
    public List<ComentarioPublicacionDTO> getPrimerosComentariosObjeto(Long idObjeto) {
        Optional<Objeto> objeto = objetoRepository.findById(idObjeto);
        List<ComentarioPublicacionDTO> comentarios = new ArrayList<>();
        if (objeto.isPresent()) {
            Comunidad comunidad = objeto.get().getComunidad();
            List<Publicacion> publicaciones = publicacionRepository.getPublicacionsByComunidad(comunidad);
            comentarios = publicaciones.stream()
                    .map(publicacion -> comentarioPublicacionMapper.toDto(publicacion.getComentariosPublicacion().getFirst()))
                    .toList();
        }

        return comentarios;
    }

}

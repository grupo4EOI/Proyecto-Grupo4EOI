package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.entities.ComentarioPublicacion;
import com.atm.buenas_practicas_java.entities.Comunidad;
import com.atm.buenas_practicas_java.entities.Objeto;
import com.atm.buenas_practicas_java.entities.Publicacion;
import com.atm.buenas_practicas_java.repositories.ComentarioPublicacionRepository;
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

    public ComentarioPublicacionService(ObjetoRepository objetoRepository, PublicacionRepository publicacionRepository, ComentarioPublicacionRepository comPubRepository) {
        this.objetoRepository = objetoRepository;
        this.publicacionRepository = publicacionRepository;
        this.comPubRepository = comPubRepository;
    }

    /** Devuelve una lista de los primeros comentarios de cada publicación pertenecientes a la comunidad del objeto */
    public List<ComentarioPublicacion> getPrimerosComentariosObjeto(Long idObjeto) {
        Optional<Objeto> objeto = objetoRepository.findById(idObjeto);
        List<ComentarioPublicacion> comentarios = new ArrayList<>();
        if (objeto.isPresent()) {
            Comunidad comunidad = objeto.get().getComunidad();
            List<Publicacion> publicaciones = publicacionRepository.getPublicacionsByComunidad(comunidad);
            comentarios = publicaciones.stream()
                    .map(publicacion -> publicacion.getComentariosPublicacion().getFirst())
                    .toList();
        }

        return comentarios;
    }

    public List<ComentarioPublicacion> getComentarioPublicacionByPublicacionId(Long publicacionId){
        return publicacionRepository.findById(publicacionId).get().getComentariosPublicacion();
    }

    public ComentarioPublicacion save(ComentarioPublicacion comentarioPublicacion) {
        return comPubRepository.save(comentarioPublicacion);
    }

}

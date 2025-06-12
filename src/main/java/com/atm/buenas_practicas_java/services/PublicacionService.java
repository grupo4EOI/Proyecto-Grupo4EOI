package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.dtos.PublicacionDTO;
import com.atm.buenas_practicas_java.entities.Comunidad;
import com.atm.buenas_practicas_java.entities.Publicacion;
import com.atm.buenas_practicas_java.mapper.PublicacionMapper;
import com.atm.buenas_practicas_java.repositories.ComunidadRepository;
import com.atm.buenas_practicas_java.repositories.PublicacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicacionService {

    private final PublicacionRepository publicacionRepository;
    private final PublicacionMapper publicacionMapper;

    public PublicacionService(PublicacionRepository publicacionRepository, PublicacionMapper publicacionMapper) {
        this.publicacionRepository = publicacionRepository;
        this.publicacionMapper = publicacionMapper;
    }

    public List<Publicacion> getPublicacionsByComunidad(Comunidad comunidad) {
        return publicacionRepository.getPublicacionsByComunidad(comunidad);
    }

    public Publicacion save(Publicacion publicacion) {
        return publicacionRepository.save(publicacion);
    }

    public Publicacion findById(Long id) {
        return publicacionRepository.findById(id).orElse(null);
    }

    public List<PublicacionDTO> buscarPublicacionesPorComunidad(Long idComunidad) {
        List<PublicacionDTO> publicaciones = publicacionMapper.toDto(publicacionRepository.findPublicacionsByComunidad_IdComunidad(idComunidad));
        return publicaciones.stream()
                .map(publicacionDTO -> {
                    Publicacion publicacion = publicacionRepository.findById(publicacionDTO.idPublicacion()).get();
                    return new PublicacionDTO(
                            publicacionDTO.idPublicacion(),
                            publicacionDTO.titulo(),
                            (long) publicacion.getComentariosPublicacion().size(),
                            publicacionDTO.usuario()
                    );
                }).toList();
    }

}

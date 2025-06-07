package com.atm.buenas_practicas_java.services.facade;

import com.atm.buenas_practicas_java.dtos.*;
import com.atm.buenas_practicas_java.entities.ComentarioPublicacion;
import com.atm.buenas_practicas_java.entities.Comunidad;
import com.atm.buenas_practicas_java.entities.Publicacion;
import com.atm.buenas_practicas_java.entities.Usuario;
import com.atm.buenas_practicas_java.mapper.ComentarioPublicacionMapper;
import com.atm.buenas_practicas_java.mapper.ComunidadMapper;
import com.atm.buenas_practicas_java.mapper.PublicacionCrearMapper;
import com.atm.buenas_practicas_java.mapper.PublicacionMapper;
import com.atm.buenas_practicas_java.services.ComentarioPublicacionService;
import com.atm.buenas_practicas_java.services.ComunidadService;
import com.atm.buenas_practicas_java.services.PublicacionService;
import com.atm.buenas_practicas_java.services.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ComunidadServiceFacade {
    private final ComentarioPublicacionService comentarioPublicacionService;
    private ComunidadService comunidadService;
    private PublicacionService publicacionService;
    private ComentarioPublicacionService comPubService;
    private UsuarioService usuarioService;
    private final PublicacionCrearMapper publicacionCrearMapper;
    private ComunidadMapper comunidadMapper;
    private PublicacionMapper publicacionMapper;
    private ComentarioPublicacionMapper comPubMapper;

    public ComunidadServiceFacade(ComunidadService comunidadService,
                                  PublicacionService publicacionService,
                                  ComentarioPublicacionService comPubService,
                                  UsuarioService usuarioService,
                                  ComunidadMapper comunidadMapper,
                                  PublicacionMapper publicacionMapper,
                                  ComentarioPublicacionMapper comPubMapper,
                                  PublicacionCrearMapper publicacionCrearMapper, ComentarioPublicacionService comentarioPublicacionService) {
        this.comunidadService = comunidadService;
        this.publicacionService = publicacionService;
        this.comPubService = comPubService;
        this.usuarioService = usuarioService;
        this.comunidadMapper = comunidadMapper;
        this.publicacionMapper = publicacionMapper;
        this.comPubMapper = comPubMapper;
        this.publicacionCrearMapper = publicacionCrearMapper;
        this.comentarioPublicacionService = comentarioPublicacionService;
    }

    public List<ComunidadDTO> buscarComunidades() {return comunidadService.buscarComunidades();}
    public ComunidadSimpleDTO findByID(Long id) { return comunidadService.findByID(id);}

    public List<PublicacionDTO> buscarPublicacionesPorComunidad(Long idComunidad) {
        return publicacionService.buscarPublicacionesPorComunidad(idComunidad);
    }

    public List<ComentarioPublicacionSimpleDTO> buscarComentariosPorPublicacion(Long idPublicacion) {
        return comPubService.getComentarioPublicacionByPublicacionId(idPublicacion);
    }

    public PublicacionCrearDTO nuevaPublicacion(Long idComunidad, PublicacionCrearDTO publicacionDTO, String nombreUsuario) {
        Usuario usuario = usuarioService.findByNombreUsuario(nombreUsuario);
        Publicacion publicacion = publicacionCrearMapper.toEntity(publicacionDTO);
        Comunidad comunidad = comunidadService.findById(idComunidad);

        ComentarioPublicacion comentarioPublicacion = new ComentarioPublicacion();
        comentarioPublicacion.setContenido(publicacionDTO.contenido());
        comentarioPublicacion.setUsuario(usuario);
        comentarioPublicacion.setComentarios(new ArrayList<>());

        publicacion.setTitulo(publicacionDTO.titulo());
        publicacion.setComunidad(comunidad);
        publicacion.setComentariosPublicacion(List.of(comentarioPublicacion));

        comentarioPublicacion.setPublicacion(publicacion);

        comunidad.getPublicaciones().add(publicacion);
        comunidad.getUsuarios().add(usuario);

        usuario.getComunidades().add(comunidad);

        usuarioService.save(usuario);
        comunidadService.save(comunidad);
        Publicacion publicacionGuardada = publicacionService.save(publicacion);
        comentarioPublicacionService.save(comentarioPublicacion);



        return publicacionCrearMapper.toDto(publicacionGuardada);
    }
}

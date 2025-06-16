package com.atm.buenas_practicas_java.services.facade;

import com.atm.buenas_practicas_java.dtos.*;
import com.atm.buenas_practicas_java.entities.ComentarioPublicacion;
import com.atm.buenas_practicas_java.entities.Comunidad;
import com.atm.buenas_practicas_java.entities.Publicacion;
import com.atm.buenas_practicas_java.entities.Usuario;
import com.atm.buenas_practicas_java.mapper.*;
import com.atm.buenas_practicas_java.services.ComentarioPublicacionService;
import com.atm.buenas_practicas_java.services.ComunidadService;
import com.atm.buenas_practicas_java.services.PublicacionService;
import com.atm.buenas_practicas_java.services.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ComunidadServiceFacade {
    private final ComentarioPublicacionService comentarioPublicacionService;
    private final ComentarioPublicacionCrearMapper comentarioPublicacionCrearMapper;
    private final ComunidadSimpleMapper comunidadSimpleMapper;
    private ComunidadService comunidadService;
    private PublicacionService publicacionService;
    private ComentarioPublicacionService comPubService;
    private UsuarioService usuarioService;
    private final PublicacionCrearMapper publicacionCrearMapper;


    public ComunidadServiceFacade(ComunidadService comunidadService,
                                  PublicacionService publicacionService,
                                  ComentarioPublicacionService comPubService,
                                  UsuarioService usuarioService,
                                  PublicacionCrearMapper publicacionCrearMapper,
                                  ComentarioPublicacionService comentarioPublicacionService,
                                  ComentarioPublicacionCrearMapper comentarioPublicacionCrearMapper, ComunidadSimpleMapper comunidadSimpleMapper) {
        this.comunidadService = comunidadService;
        this.publicacionService = publicacionService;
        this.comPubService = comPubService;
        this.usuarioService = usuarioService;
        this.publicacionCrearMapper = publicacionCrearMapper;
        this.comentarioPublicacionService = comentarioPublicacionService;
        this.comentarioPublicacionCrearMapper = comentarioPublicacionCrearMapper;
        this.comunidadSimpleMapper = comunidadSimpleMapper;
    }

    public ComunidadSimpleDTO findById(Long idComunidad) {
        return comunidadSimpleMapper.toDTO(comunidadService.findById(idComunidad));
    }

    public List<ComunidadDTO> buscarComunidades() {
        return comunidadService.buscarComunidades();
    }

    public List<PublicacionDTO> buscarPublicacionesPorComunidad(Long idComunidad) {
        return publicacionService.buscarPublicacionesPorComunidad(idComunidad);
    }

    public List<ComentarioPublicacionSimpleDTO> buscarComentariosPorPublicacion(Long idPublicacion) {
        return comPubService.getComentarioPublicacionByPublicacionId(idPublicacion);
    }

    @Transactional
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

    @Transactional
    public ComentarioPublicacionCrearDTO nuevoComentario(Long idComunidad, ComentarioPublicacionCrearDTO comentarioDTO, String nombreUsuario, Long idPublicacion) {
        Usuario usuario = usuarioService.findByNombreUsuario(nombreUsuario);
        Comunidad comunidad = comunidadService.findById(idComunidad);
        Publicacion publicacion = publicacionService.findById(idPublicacion);
        ComentarioPublicacion comentarioPublicacion = comentarioPublicacionCrearMapper.toEntity(comentarioDTO);

        comentarioPublicacion.setContenido(comentarioDTO.contenido());
        comentarioPublicacion.setUsuario(usuario);
        comentarioPublicacion.setPublicacion(publicacion);
        comentarioPublicacion.setFecha(LocalDateTime.now());

        comunidad.getUsuarios().add(usuario);
        usuario.getComunidades().add(comunidad);

        usuarioService.save(usuario);
        comunidadService.save(comunidad);
        ComentarioPublicacion comentarioGuardado = comentarioPublicacionService.save(comentarioPublicacion);

        return comentarioPublicacionCrearMapper.toDto(comentarioGuardado);
    }

    @Transactional
    public void reportarComentarioPublicacion(Long idComentarioPublicacion) {
        comentarioPublicacionService.reportar(idComentarioPublicacion);
    }
}

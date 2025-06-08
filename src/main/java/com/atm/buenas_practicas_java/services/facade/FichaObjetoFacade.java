package com.atm.buenas_practicas_java.services.facade;

import com.atm.buenas_practicas_java.dtos.*;
import com.atm.buenas_practicas_java.dtos.composedDTOs.FichaObjetoDTO;
import com.atm.buenas_practicas_java.entities.ComentarioResena;
import com.atm.buenas_practicas_java.entities.Objeto;
import com.atm.buenas_practicas_java.entities.Resena;
import com.atm.buenas_practicas_java.entities.Usuario;
import com.atm.buenas_practicas_java.mapper.ComentarioResenaMapper;
import com.atm.buenas_practicas_java.mapper.FichaObjetoMapper;
import com.atm.buenas_practicas_java.mapper.ResenaCrearMapper;
import com.atm.buenas_practicas_java.mapper.ResenaMapper;
import com.atm.buenas_practicas_java.repositories.ObjetoRepository;
import com.atm.buenas_practicas_java.repositories.ResenaRepository;
import com.atm.buenas_practicas_java.repositories.UsuarioRepository;
import com.atm.buenas_practicas_java.services.*;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class FichaObjetoFacade {

    private final ObjetoService objetoService;
    private final ResenaService resenaService;
    private final PersonaService personaService;
    private final ComentarioPublicacionService comentarioPublicacionService;
    private final UsuarioService usuarioService;
    private final ResenaCrearMapper resenaCrearMapper;
    private final FichaObjetoMapper fichaObjetoMapper;
    private final ComentarioResenaMapper comentarioResenaMapper;
    private final ComentarioResenaService comentarioResenaService;

    public FichaObjetoFacade(ObjetoService objetoService,
                             ResenaService resenaService,
                             PersonaService personaService,
                             ComentarioPublicacionService comentarioPublicacionService,
                             FichaObjetoMapper fichaObjetoMapper,
                             UsuarioService usuarioService,
                             ResenaCrearMapper resenaCrearMapper, ComentarioResenaMapper comentarioResenaMapper, ComentarioResenaService comentarioResenaService) {
        this.objetoService = objetoService;
        this.resenaService = resenaService;
        this.personaService = personaService;
        this.comentarioPublicacionService = comentarioPublicacionService;
        this.fichaObjetoMapper = fichaObjetoMapper;
        this.usuarioService = usuarioService;
        this.resenaCrearMapper = resenaCrearMapper;
        this.comentarioResenaMapper = comentarioResenaMapper;
        this.comentarioResenaService = comentarioResenaService;
    }

    public FichaObjetoDTO construirFichaObjeto(Long idObjeto) {
        Objeto objeto = objetoService.findById(idObjeto);

        FichaObjetoDTO dto = fichaObjetoMapper.toDto(objeto);

        DecimalFormat df = new DecimalFormat("#.##");

        List<ComentarioPublicacionDTO> primerasPublicaciones = comentarioPublicacionService.getPrimerosComentariosObjeto(idObjeto);
        String puntuacion = df.format(objetoService.calcularPuntuacionObjeto(idObjeto));
        Integer numeroResenas = objetoService.calcularNumeroResenas(idObjeto);
        List<PersonaDTO> directores = personaService.getDirectoresByObjetoId(idObjeto);
        List<PersonaDTO> actores = personaService.getActoresByObjetoId(idObjeto);
        List<ResenaDTO> resenas = resenaService.findResenasByObjeto(idObjeto);

        return new FichaObjetoDTO(
                dto.idObjeto(),
                dto.titulo(),
                dto.descripcion(),
                dto.fechaYDuracion(),
                dto.imagenUrl(),
                dto.trailerUrl(),
                dto.tipo(),
                dto.generos(),
                resenas,
                primerasPublicaciones,
                puntuacion,
                numeroResenas,
                directores,
                actores
        );
    }

    public void agregarResena(Long idObjeto, ResenaCrearDTO resenaDTO, String nombreUsuario) {
        Usuario usuario = usuarioService.findByNombreUsuario(nombreUsuario);
        Objeto objeto = objetoService.findById(idObjeto);

        Resena entidadResena = resenaCrearMapper.toEntity(resenaDTO);
        entidadResena.setUsuario(usuario);
        entidadResena.setObjeto(objeto);
        entidadResena.setReacciones(new ArrayList<>());
        entidadResena.setComentariosResena(new ArrayList<>());

        objeto.getResenas().add(entidadResena);
        usuario.getResenas().add(entidadResena);

        objetoService.save(objeto);
        usuarioService.save(usuario);
        resenaService.save(entidadResena);
    }

    public void agregarComentarioResena(Long idResena, ComentarioResenaDTO comentarioResenaDTO, String nombreUsuario) {
        Usuario usuario = usuarioService.findByNombreUsuario(nombreUsuario);
        Resena resena = resenaService.findById(idResena);

        ComentarioResena comentarioEntidad = comentarioResenaMapper.toEntity(comentarioResenaDTO);
        comentarioEntidad.setUsuario(usuario);
        comentarioEntidad.setResena(resena);

        resena.getComentariosResena().add(comentarioEntidad);

        comentarioResenaService.save(comentarioEntidad);
        resenaService.save(resena);
    }
}

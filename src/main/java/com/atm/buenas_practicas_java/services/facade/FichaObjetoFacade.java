package com.atm.buenas_practicas_java.services.facade;

import com.atm.buenas_practicas_java.dtos.*;
import com.atm.buenas_practicas_java.dtos.composedDTOs.FichaObjetoDTO;
import com.atm.buenas_practicas_java.entities.Objeto;
import com.atm.buenas_practicas_java.entities.Resena;
import com.atm.buenas_practicas_java.entities.Usuario;
import com.atm.buenas_practicas_java.mapper.FichaObjetoMapper;
import com.atm.buenas_practicas_java.mapper.ResenaCrearMapper;
import com.atm.buenas_practicas_java.mapper.ResenaMapper;
import com.atm.buenas_practicas_java.repositories.ResenaRepository;
import com.atm.buenas_practicas_java.services.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FichaObjetoFacade {

    private final ObjetoService objetoService;
    private final ResenaService resenaService;
    private final PersonaService personaService;
    private final ComentarioPublicacionService comentarioPublicacionService;
    private final FichaObjetoMapper fichaObjetoMapper;
    private final ResenaMapper resenaMapper;
    private final UsuarioService usuarioService;
    private final ResenaRepository resenaRepository;
    private final ResenaCrearMapper resenaCrearMapper;

    public FichaObjetoFacade(ObjetoService objetoService,
                             ResenaService resenaService,
                             PersonaService personaService,
                             ComentarioPublicacionService comentarioPublicacionService,
                             FichaObjetoMapper fichaObjetoMapper,
                             ResenaMapper resenaMapper,
                             UsuarioService usuarioService,
                             ResenaRepository resenaRepository,
                             ResenaCrearMapper resenaCrearMapper) {
        this.objetoService = objetoService;
        this.resenaService = resenaService;
        this.personaService = personaService;
        this.comentarioPublicacionService = comentarioPublicacionService;
        this.fichaObjetoMapper = fichaObjetoMapper;
        this.resenaMapper = resenaMapper;
        this.usuarioService = usuarioService;
        this.resenaRepository = resenaRepository;
        this.resenaCrearMapper = resenaCrearMapper;
    }

    public FichaObjetoDTO construirFichaObjeto(Long idObjeto) {
        Objeto objeto = objetoService.findById(idObjeto);

        FichaObjetoDTO dto = fichaObjetoMapper.toDto(objeto);

        List<ComentarioPublicacionDTO> primerasPublicaciones = comentarioPublicacionService.getPrimerosComentariosObjeto(idObjeto);
        Double puntuacion = objetoService.calcularPuntuacionObjeto(idObjeto);
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
        ResenaCrearDTO nuevaResena = new ResenaCrearDTO(
                resenaDTO.titulo(),
                resenaDTO.contenido(),
                resenaDTO.puntuacion(),
                resenaDTO.spoiler()
        );

        Objeto objeto = objetoService.findById(idObjeto);

        Resena entidadResena = resenaCrearMapper.toEntity(nuevaResena);
        entidadResena.setUsuario(usuario);
        entidadResena.setObjeto(objeto);

        objeto.getResenas().add(entidadResena);
        usuario.getResenas().add(entidadResena);

        resenaService.guardarResena(entidadResena);
    }
}

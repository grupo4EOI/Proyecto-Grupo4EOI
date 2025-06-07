package com.atm.buenas_practicas_java.services.facade;

import com.atm.buenas_practicas_java.dtos.ComentarioPublicacionDTO;
import com.atm.buenas_practicas_java.dtos.UsuarioDTO;
import com.atm.buenas_practicas_java.dtos.composedDTOs.FichaObjetoDTO;
import com.atm.buenas_practicas_java.dtos.PersonaDTO;
import com.atm.buenas_practicas_java.dtos.ResenaDTO;
import com.atm.buenas_practicas_java.entities.Objeto;
import com.atm.buenas_practicas_java.entities.Resena;
import com.atm.buenas_practicas_java.entities.Usuario;
import com.atm.buenas_practicas_java.mapper.FichaObjetoMapper;
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

    public FichaObjetoFacade(ObjetoService objetoService,
                             ResenaService resenaService,
                             PersonaService personaService,
                             ComentarioPublicacionService comentarioPublicacionService,
                             FichaObjetoMapper fichaObjetoMapper,
                             ResenaMapper resenaMapper,
                             UsuarioService usuarioService, ResenaRepository resenaRepository) {
        this.objetoService = objetoService;
        this.resenaService = resenaService;
        this.personaService = personaService;
        this.comentarioPublicacionService = comentarioPublicacionService;
        this.fichaObjetoMapper = fichaObjetoMapper;
        this.resenaMapper = resenaMapper;
        this.usuarioService = usuarioService;
        this.resenaRepository = resenaRepository;
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

    public void agregarResena(Long idObjeto, ResenaDTO resenaDTO, String nombreUsuario) {
        Usuario usuario = usuarioService.findByNombreUsuario(nombreUsuario);
        UsuarioDTO autorDTO = new UsuarioDTO(usuario.getNombreUsuario(), usuario.getAvatarUrl());
        ResenaDTO nuevaResena = new ResenaDTO(
                resenaDTO.titulo(),
                resenaDTO.contenido(),
                resenaDTO.puntuacion(),
                resenaDTO.spoiler(),
                autorDTO,
                List.of()
        );

        Objeto objeto = objetoService.findById(idObjeto);

        Resena entidadResena = resenaMapper.toEntity(nuevaResena);
        entidadResena.setUsuario(usuario);
        entidadResena.setObjeto(objeto);

        objeto.getResenas().add(entidadResena);
        usuario.getResenas().add(entidadResena);

        resenaRepository.save(entidadResena);
    }
}

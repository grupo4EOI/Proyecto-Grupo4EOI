package com.atm.buenas_practicas_java.services.facade;

import com.atm.buenas_practicas_java.dtos.ComentarioPublicacionDTO;
import com.atm.buenas_practicas_java.dtos.composedDTOs.FichaObjetoDTO;
import com.atm.buenas_practicas_java.dtos.PersonaDTO;
import com.atm.buenas_practicas_java.dtos.ResenaDTO;
import com.atm.buenas_practicas_java.entities.Objeto;
import com.atm.buenas_practicas_java.mapper.FichaObjetoMapper;
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

    public FichaObjetoFacade(ObjetoService objetoService,
                             ResenaService resenaService,
                             PersonaService personaService,
                             ComentarioPublicacionService comentarioPublicacionService,
                             FichaObjetoMapper fichaObjetoMapper) {
        this.objetoService = objetoService;
        this.resenaService = resenaService;
        this.personaService = personaService;
        this.comentarioPublicacionService = comentarioPublicacionService;
        this.fichaObjetoMapper = fichaObjetoMapper;
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


}

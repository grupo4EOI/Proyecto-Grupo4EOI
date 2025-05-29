package com.atm.buenas_practicas_java.services.facade;

import com.atm.buenas_practicas_java.dtos.FichaObjetoDTO;
import com.atm.buenas_practicas_java.services.*;
import org.springframework.stereotype.Service;

@Service
public class FichaObjetoFacade {

    private final ObjetoService objetoService;
    private final GeneroService generoService;
    private final ResenaService resenaService;
    private final PersonaService personaService;
    private final ComentarioPublicacionService comentarioPublicacionService;

    public FichaObjetoFacade(ObjetoService objetoService,
                             GeneroService generoService,
                             ResenaService resenaService,
                             PersonaService personaService,
                             ComentarioPublicacionService comentarioPublicacionService) {
        this.objetoService = objetoService;
        this.generoService = generoService;
        this.resenaService = resenaService;
        this.personaService = personaService;
        this.comentarioPublicacionService = comentarioPublicacionService;
    }

    public FichaObjetoDTO construirFichaObjeto(Long idObjeto) {

    }


}

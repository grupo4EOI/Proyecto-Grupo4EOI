package com.atm.buenas_practicas_java.services.facade;

import com.atm.buenas_practicas_java.dtos.composedDTOs.PaginaPrincipalDTO;
import com.atm.buenas_practicas_java.services.ComentarioPublicacionService;
import com.atm.buenas_practicas_java.services.ObjetoService;
import com.atm.buenas_practicas_java.services.ResenaService;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

@Service
public class PaginaPrincipalServiceFacade {

    private final ObjetoService objetoService;
    private final ResenaService resenaService;
    private final ComentarioPublicacionService comentarioPublicacionService;

    public PaginaPrincipalServiceFacade(ObjetoService objetoService,
                                        ResenaService resenaService,
                                        ComentarioPublicacionService comentarioPublicacionService) {
        this.objetoService = objetoService;
        this.resenaService = resenaService;
        this.comentarioPublicacionService = comentarioPublicacionService;
    }

    public PaginaPrincipalDTO construirDTOPaginaPrincipal() {
        return new PaginaPrincipalDTO(
                objetoService.obtenerObjetosMasRecientesPorTipo("pelicula", Limit.of(4)),
                objetoService.obtenerObjetosMejorValoradosPorTipo("serie", Limit.of(4)),
                objetoService.obtenerObjetosMasPopularesPorTipo("videojuego", Limit.of(4)),
                resenaService.obtenerUltimaResena(),
                comentarioPublicacionService.buscarPrimerComentarioUltimaPublicacion()
        );
    }


}

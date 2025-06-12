package com.atm.buenas_practicas_java.services.facade;

import com.atm.buenas_practicas_java.dtos.composedDTOs.PanelAdminDTO;
import com.atm.buenas_practicas_java.services.ComentarioPublicacionService;
import com.atm.buenas_practicas_java.services.ComentarioResenaService;
import com.atm.buenas_practicas_java.services.ResenaService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceFacade {

    private final ResenaService resenaService;
    private final ComentarioResenaService comentarioResenaService;
    private final ComentarioPublicacionService comentarioPublicacionService;

    public AdminServiceFacade(ResenaService resenaService,
                              ComentarioResenaService comentarioResenaService,
                              ComentarioPublicacionService comentarioPublicacionService) {
        this.resenaService = resenaService;
        this.comentarioResenaService = comentarioResenaService;
        this.comentarioPublicacionService = comentarioPublicacionService;
    }

    public PanelAdminDTO crearPanelAdminDTO() {
        return new PanelAdminDTO(
                resenaService.obtenerResenasConAbuso(),
                comentarioResenaService.obtenerComentariosResenasConAbuso(),
                comentarioPublicacionService.buscarComentariosPublicacionConAbuso()
        );
    }
}

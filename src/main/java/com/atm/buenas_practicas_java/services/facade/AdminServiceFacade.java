package com.atm.buenas_practicas_java.services.facade;

import com.atm.buenas_practicas_java.dtos.composedDTOs.PanelAdminDTO;
import com.atm.buenas_practicas_java.services.ResenaService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceFacade {

    private final ResenaService resenaService;
    private final ComentarioResenaService comentarioResenaService;

    public AdminServiceFacade(ResenaService resenaService) {
        this.resenaService = resenaService;
    }

    public PanelAdminDTO crearPanelAdminDTO() {
        return new PanelAdminDTO(
                resenaService.obtenerResenasConAbuso(),

        )
    }

}

package com.atm.buenas_practicas_java.dtos.composedDTOs;

import com.atm.buenas_practicas_java.dtos.ComentarioPublicacionDTO;
import com.atm.buenas_practicas_java.dtos.ComentarioResenaDTO;
import com.atm.buenas_practicas_java.dtos.ResenaDTO;

import java.util.List;

public record PanelAdminDTO(
        List<ResenaDTO> resenasARevisar,
        List<ComentarioResenaDTO> comentariosResenasARevisar,
        List<ComentarioPublicacionDTO> publicacionARevisar,
        List<ComentarioPublicacionDTO> comentariosPublicacionARevisar
) {
}

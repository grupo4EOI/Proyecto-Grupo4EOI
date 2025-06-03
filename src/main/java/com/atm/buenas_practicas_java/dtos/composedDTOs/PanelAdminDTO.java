package com.atm.buenas_practicas_java.dtos.composedDTOs;

import com.atm.buenas_practicas_java.dtos.ComentarioPublicacionDTO;
import com.atm.buenas_practicas_java.dtos.ComentarioResenaDTO;
import com.atm.buenas_practicas_java.dtos.ResenaDTO;

import java.util.List;

public record PanelAdminDTO(
        List<ResenaDTO> resenas,
        List<ComentarioResenaDTO> comentariosResenas,
        List<ComentarioPublicacionDTO> publicaciones,
        List<ComentarioPublicacionDTO> comentariosPublicaciones
) {
}

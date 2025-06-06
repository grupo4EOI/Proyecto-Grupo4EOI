package com.atm.buenas_practicas_java.dtos.composedDTOs;

import com.atm.buenas_practicas_java.dtos.ObjetoDTO;
import com.atm.buenas_practicas_java.dtos.PublicacionDTO;
import com.atm.buenas_practicas_java.dtos.ResenaDTO;


import java.util.List;

public record PaginaPrincipalDTO(
        List<ObjetoDTO> peliculasRecientes,
        List<ObjetoDTO> seriesMasValoradas,
        List<ObjetoDTO> videojuegosPopulares,
        ResenaDTO ultimaResena,
        PublicacionDTO ultimaPublicacion
) {
}

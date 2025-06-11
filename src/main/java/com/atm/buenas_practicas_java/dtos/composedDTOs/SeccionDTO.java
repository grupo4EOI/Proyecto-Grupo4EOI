package com.atm.buenas_practicas_java.dtos.composedDTOs;

import com.atm.buenas_practicas_java.dtos.ObjetoDTO;

import java.util.List;

public record SeccionDTO(
        String tipo,
        List<ObjetoDTO> objetosMasRecientes,
        List<ObjetoDTO> objetosMejorValorados
) {
}

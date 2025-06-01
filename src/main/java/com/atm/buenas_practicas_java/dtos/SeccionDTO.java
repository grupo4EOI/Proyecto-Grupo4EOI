package com.atm.buenas_practicas_java.dtos;

import java.util.List;

public record SeccionDTO(
        List<ObjetoDTO> objetosMasRecientes,
        List<ObjetoDTO> objetosMejorValorados
) {
}

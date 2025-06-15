package com.atm.buenas_practicas_java.dtos;

import java.time.LocalDateTime;
import java.util.List;

public record ObjetoCrearDTO(
        String titulo,
        String imagenUrl,
        String trailerUrl,
        LocalDateTime fechaPublicacion
) {
}

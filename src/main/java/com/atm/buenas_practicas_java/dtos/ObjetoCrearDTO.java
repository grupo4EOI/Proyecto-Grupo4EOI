package com.atm.buenas_practicas_java.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ObjetoCrearDTO(
        String titulo,
        String imagenUrl,
        String trailerUrl,
        String fechaPublicacion,
        String sinopsis,
        List<GeneroDTO> generos,
        List<PersonaDTO> direccion,
        List<PersonaDTO> reparto,
        Integer tmdbId
) {
}

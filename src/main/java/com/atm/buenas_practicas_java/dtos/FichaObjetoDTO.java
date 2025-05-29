package com.atm.buenas_practicas_java.dtos;

import java.util.List;

public record FichaObjetoDTO(
        String titulo,
        String descripcion,
        String fechaYDuracion,
        String imagenUrl,
        String trailerUrl,
        String tipo,
        List<String> generos,
        List<ResenaDTO> resenas,
        List<ComentarioPublicacionDTO> publicaciones,
        Double puntuacion,
        Integer numeroResenas,
        List<PersonaDTO> directores,
        List<PersonaDTO> actores
) { }

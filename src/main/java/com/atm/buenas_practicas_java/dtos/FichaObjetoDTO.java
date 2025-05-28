package com.atm.buenas_practicas_java.dtos;

import java.util.List;

public record FichaObjetoDTO(
        String titulo,
        String descripcion,
        String fechaYDuracion,
        String imagenUrl,
        String trailerUrl,
        String tipo,
        List<PersonaDTO> personas,
        List<GeneroDTO> generos,
        List<ResenaDTO> resenas,
        List<PublicacionDTO> publicaciones
) { }

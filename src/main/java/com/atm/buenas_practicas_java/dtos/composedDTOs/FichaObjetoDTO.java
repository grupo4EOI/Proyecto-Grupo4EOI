package com.atm.buenas_practicas_java.dtos.composedDTOs;

import com.atm.buenas_practicas_java.dtos.ComentarioPublicacionDTO;
import com.atm.buenas_practicas_java.dtos.ComunidadSimpleDTO;
import com.atm.buenas_practicas_java.dtos.PersonaDTO;
import com.atm.buenas_practicas_java.dtos.ResenaDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public record FichaObjetoDTO(
        Long idObjeto,
        String titulo,
        String descripcion,
        String fechaYDuracion,
        String imagenUrl,
        String trailerUrl,
        String tipo,
        List<String> generos,
        Page<ResenaDTO> resenas,
        String puntuacion,
        Integer numeroResenas,
        List<PersonaDTO> directores,
        List<PersonaDTO> actores,
        ComunidadSimpleDTO comunidad
) { }

package com.atm.buenas_practicas_java.mapper;

import com.atm.buenas_practicas_java.dtos.FichaObjetoDTO;
import com.atm.buenas_practicas_java.dtos.PublicacionDTO;
import com.atm.buenas_practicas_java.entities.Objeto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = { GeneroMapper.class, ResenaMapper.class, PublicacionMapper.class})
public interface FichaObjetoMapper {

    @Mapping(target = "fechaYDuracion", expression = "java(fechaYDuracion(objeto))")
    @Mapping(target = "tipo", source = "tipo")
    @Mapping(target = "generos", source = "generos")
    @Mapping(target = "puntuacion", ignore = true)
    @Mapping(target = "numeroResenas", ignore = true)
    @Mapping(target = "publicaciones", ignore = true)
    @Mapping(target = "directores", ignore = true)
    @Mapping(target = "actores", ignore = true)
    FichaObjetoDTO toDto(Objeto objeto);

    static String fechaYDuracion(Objeto objeto) {
        return String.format("Año de publicación: %d - Duración: %d minutos",
                objeto.getFechaPublicacion().getYear(),
                objeto.getDuracionMinutos());
    }
}

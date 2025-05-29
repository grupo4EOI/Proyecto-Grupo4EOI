package com.atm.buenas_practicas_java.mapper;

import com.atm.buenas_practicas_java.dtos.FichaObjetoDTO;
import com.atm.buenas_practicas_java.entities.Objeto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PersonaMapper.class, GeneroMapper.class, ResenaMapper.class, PublicacionMapper.class})
public interface FichaObjetoMapper {

    @Mapping(target = "fechaYDuracion", expression = "java(fechaYDuracion(objeto))")
    FichaObjetoDTO toDto(Objeto objeto);

    static String fechaYDuracion(Objeto objeto) {
        return String.format("Fecha publicación: %d - Duración: %d minutos",
                objeto.getFechaPublicacion().getYear(),
                objeto.getDuracionMinutos());
    }
}

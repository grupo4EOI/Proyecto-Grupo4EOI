package com.atm.buenas_practicas_java.mapper;

import com.atm.buenas_practicas_java.dtos.composedDTOs.FichaObjetoDTO;
import com.atm.buenas_practicas_java.entities.Genero;
import com.atm.buenas_practicas_java.entities.Objeto;
import com.atm.buenas_practicas_java.entities.Tipo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring", uses = {GeneroMapper.class, ResenaMapper.class, PublicacionMapper.class, ComunidadSimpleMapper.class})
public interface FichaObjetoMapper {

    @Mapping(target = "fechaYDuracion", expression = "java(fechaYDuracion(objeto))")
    @Mapping(target = "tipo", source = "tipo", qualifiedByName = "tipoToString")
    @Mapping(target = "generos", source = "generos")
    @Mapping(target = "puntuacion", ignore = true)
    @Mapping(target = "numeroResenas", ignore = true)
    @Mapping(target = "publicaciones", ignore = true)
    @Mapping(target = "directores", ignore = true)
    @Mapping(target = "actores", ignore = true)
    FichaObjetoDTO toDto(Objeto objeto);

    default String fechaYDuracion(Objeto objeto) {
        return String.format("Año de publicación: %d - Duración: %d minutos",
                objeto.getFechaPublicacion().getYear(),
                objeto.getDuracionMinutos());
    }

    @Named("tipoToString")
    static String tipoToString(Tipo tipo) {
        return tipo.getNombre();
    }

    default List<String> map(Set<Genero> generos) {
        if (generos == null) {
            return new ArrayList<>();
        }

        return generos.stream().map(Genero::getNombre).collect(Collectors.toList());
    }
}

package com.atm.buenas_practicas_java.mapper;

import com.atm.buenas_practicas_java.dtos.ObjetoDTO;
import com.atm.buenas_practicas_java.entities.Objeto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ObjetoMapper {

    @Mapping(target = "anoPublicacion", expression = "java(anoPublicacion(objeto)")
    @Mapping(target = "puntuacion", ignore = true)
    @Mapping(target = "numeroResenas", ignore = true)
    ObjetoDTO toDto(Objeto objeto);

    default Integer anoPublicacion(Objeto objeto) {
        return objeto.getFechaPublicacion().getYear();
    }

    List<ObjetoDTO> toDtoList(List<Objeto> objetos);
}

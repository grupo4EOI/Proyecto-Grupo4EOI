package com.atm.buenas_practicas_java.mapper;

import com.atm.buenas_practicas_java.dtos.ObjetoDTO;
import com.atm.buenas_practicas_java.entities.Objeto;
import com.atm.buenas_practicas_java.entities.Tipo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ObjetoMapper {

    @Mapping(target = "anoPublicacion", expression = "java(anoPublicacion(objeto))")
    @Mapping(target = "puntuacion", ignore = true)
    @Mapping(target = "numeroResenas", ignore = true)
    @Mapping(target = "tipo", source = "tipo", qualifiedByName = "tipoToString")
    ObjetoDTO toDto(Objeto objeto);

    default Integer anoPublicacion(Objeto objeto) {
        return objeto.getFechaPublicacion().getYear();
    }

    @Named("tipoToString")
    static String tipoToString(Tipo tipo) {
        return tipo.getNombre();
    }

    List<ObjetoDTO> toDtoList(List<Objeto> objetos);
}

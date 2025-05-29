package com.atm.buenas_practicas_java.mapper;

import com.atm.buenas_practicas_java.dtos.ComentarioPublicacionDTO;
import com.atm.buenas_practicas_java.entities.ComentarioPublicacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ComentarioPublicacionMapper {

    @Mapping(target = "titulo", ignore = true)
    ComentarioPublicacionDTO toDto(ComentarioPublicacion comentarioPublicacion);

}

package com.atm.buenas_practicas_java.mapper;

import com.atm.buenas_practicas_java.dtos.ComentarioResenaDTO;
import com.atm.buenas_practicas_java.dtos.ResenaDTO;
import com.atm.buenas_practicas_java.entities.ComentarioResena;
import com.atm.buenas_practicas_java.entities.Resena;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ResenaMapper {

    @Mapping(target = "autor", source = "usuario")
    ResenaDTO toDto(Resena resena);

    ComentarioResenaDTO comentarioToDTO(ComentarioResena comentarioResena);
}

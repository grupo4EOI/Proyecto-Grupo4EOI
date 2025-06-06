package com.atm.buenas_practicas_java.mapper;

import com.atm.buenas_practicas_java.dtos.ComentarioPublicacionDTO;
import com.atm.buenas_practicas_java.dtos.ComentarioPublicacionSimpleDTO;
import com.atm.buenas_practicas_java.entities.ComentarioPublicacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = UsuarioMapper.class)
public interface ComentarioPublicacionSimpleMapper {
    @Mapping(source = "usuario", target = "usuario")
    ComentarioPublicacionSimpleDTO toDto(ComentarioPublicacion comentarioPublicacion);

    List<ComentarioPublicacionSimpleDTO> toDto(List<ComentarioPublicacion> comentarioPublicacion);
}

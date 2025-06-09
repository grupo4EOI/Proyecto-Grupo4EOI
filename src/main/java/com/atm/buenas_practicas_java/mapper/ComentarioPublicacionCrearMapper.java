package com.atm.buenas_practicas_java.mapper;

import com.atm.buenas_practicas_java.dtos.ComentarioPublicacionCrearDTO;
import com.atm.buenas_practicas_java.entities.ComentarioPublicacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ComentarioPublicacionCrearMapper {

    @Mapping(source = "usuario", target = "usuario")
    @Mapping(source = "publicacion", target = "publicacion")
    ComentarioPublicacionCrearDTO toDto(ComentarioPublicacion comentarioPublicacion);

    @Mapping(target = "idComentarioPublicacion", ignore = true)
    @Mapping(target = "abuso", ignore = true)
    @Mapping(target = "publicacion", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    ComentarioPublicacion toEntity(ComentarioPublicacionCrearDTO comentarioDTO);

}

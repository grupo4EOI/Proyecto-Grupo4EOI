package com.atm.buenas_practicas_java.mapper;

import com.atm.buenas_practicas_java.dtos.ComentarioResenaDTO;
import com.atm.buenas_practicas_java.entities.ComentarioResena;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = UsuarioMapper.class)
public interface ComentarioResenaMapper {

    @Mapping(source = "contenido", target = "contenido")
    @Mapping(source = "fecha", target = "fecha")
    @Mapping(source = "usuario", target = "usuario")
    ComentarioResenaDTO toDto(ComentarioResena comentarioResena);

    List<ComentarioResenaDTO> toDtoList(List<ComentarioResena> comentarios);
}

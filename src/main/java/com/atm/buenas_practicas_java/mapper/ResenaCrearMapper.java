package com.atm.buenas_practicas_java.mapper;

import com.atm.buenas_practicas_java.dtos.ResenaDTO;
import com.atm.buenas_practicas_java.entities.Resena;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class, ComentarioResenaMapper.class})
public interface ResenaCrearMapper {

    @Mapping(source = "contenido", target = "contenido")
    @Mapping(source = "puntuacion", target = "puntuacion")
    @Mapping(source = "comentariosResena", target = "comentariosResena")
    ResenaDTO toDto(Resena resena);

    List<ResenaDTO> toDtoList(List<Resena> resenas);

    @Mapping(target = "idResena", ignore = true)
    @Mapping(target = "objeto", ignore = true)
    @Mapping(target = "reacciones", ignore = true)
    @Mapping(target = "comentariosResena", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "abuso", ignore = true)
    Resena toEntity(ResenaDTO resenaDTO);
}


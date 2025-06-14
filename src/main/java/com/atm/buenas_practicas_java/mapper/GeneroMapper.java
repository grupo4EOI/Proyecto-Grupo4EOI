package com.atm.buenas_practicas_java.mapper;

import com.atm.buenas_practicas_java.dtos.GeneroDTO;
import com.atm.buenas_practicas_java.entities.Genero;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GeneroMapper {

    GeneroDTO toDto(Genero genero);

    Genero toEntity(GeneroDTO generoDTO);

    List<GeneroDTO> toDtoList(List<Genero> generos);

}

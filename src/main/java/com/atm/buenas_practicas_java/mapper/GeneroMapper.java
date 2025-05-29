package com.atm.buenas_practicas_java.mapper;

import com.atm.buenas_practicas_java.dtos.GeneroDTO;
import com.atm.buenas_practicas_java.entities.Genero;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GeneroMapper {

    GeneroDTO toDTO(Genero genero);
    Genero toEntity(GeneroDTO generoDTO);

}

package com.atm.buenas_practicas_java.mapper;

import com.atm.buenas_practicas_java.dtos.PublicacionDTO;
import com.atm.buenas_practicas_java.entities.Publicacion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublicacionMapper {

    PublicacionDTO toDto(Publicacion publicacion);

}

package com.atm.buenas_practicas_java.mapper;

import com.atm.buenas_practicas_java.dtos.PublicacionDTO;
import com.atm.buenas_practicas_java.entities.Publicacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PublicacionMapper {

    @Mapping(target = "numComentarios", ignore = true)
    @Mapping(target = "nombreUsuario", ignore = true)
    PublicacionDTO toDto(Publicacion publicacion);

}

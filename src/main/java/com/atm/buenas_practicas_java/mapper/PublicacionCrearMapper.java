package com.atm.buenas_practicas_java.mapper;

import com.atm.buenas_practicas_java.dtos.PublicacionCrearDTO;
import com.atm.buenas_practicas_java.entities.Publicacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PublicacionCrearMapper {

    @Mapping(target = "contenido", ignore = true)
    PublicacionCrearDTO toDto(Publicacion publicacion);

    @Mapping(target = "idPublicacion", ignore = true)
    @Mapping(target = "comentariosPublicacion", ignore = true)
    @Mapping(target = "comunidad", ignore = true)
    Publicacion toEntity(PublicacionCrearDTO publicacionDTO);

}

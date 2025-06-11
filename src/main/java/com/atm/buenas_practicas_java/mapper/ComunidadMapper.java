package com.atm.buenas_practicas_java.mapper;

import com.atm.buenas_practicas_java.dtos.ComunidadDTO;
import com.atm.buenas_practicas_java.entities.Comunidad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ComunidadMapper {
    @Mapping(target = "numPublicaciones", ignore = true)
    @Mapping(target = "numUsuarios", ignore = true)
    ComunidadDTO toDTO(Comunidad comunidad);
    List<ComunidadDTO> toDTO(List<Comunidad> comunidades);
}

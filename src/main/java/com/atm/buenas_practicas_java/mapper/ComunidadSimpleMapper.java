package com.atm.buenas_practicas_java.mapper;

import com.atm.buenas_practicas_java.dtos.ComunidadDTO;
import com.atm.buenas_practicas_java.dtos.ComunidadSimpleDTO;
import com.atm.buenas_practicas_java.entities.Comunidad;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ComunidadSimpleMapper {
    ComunidadSimpleDTO toDTO(Comunidad comunidad);
}

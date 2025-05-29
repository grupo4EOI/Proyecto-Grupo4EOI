package com.atm.buenas_practicas_java.mapper;

import com.atm.buenas_practicas_java.dtos.PersonaDTO;
import com.atm.buenas_practicas_java.entities.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonaMapper {

    @Mapping(target = "nombreCompleto", expression = "java(persona.getNombre() + ' ' + persona.getApellido())")
    PersonaDTO toDto(Persona persona);

}

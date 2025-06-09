package com.atm.buenas_practicas_java.mapper;

import com.atm.buenas_practicas_java.dtos.GeneroDTO;
import com.atm.buenas_practicas_java.dtos.GeneroPerfilDTO;
import com.atm.buenas_practicas_java.entities.Genero;
import com.atm.buenas_practicas_java.entities.Objeto;
import com.atm.buenas_practicas_java.entities.Tipo;
import com.atm.buenas_practicas_java.entities.Usuario;
import jakarta.persistence.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;


@Mapper(componentModel = "spring")
public interface PerfilMapper {
    @Mapping(target = "idGenero", source = "idGenero")
    @Mapping(target = "nombre", source = "nombre")
    GeneroPerfilDTO toDto(Genero genero);

    List<GeneroPerfilDTO> toDtoList(List<Genero> generos);
}
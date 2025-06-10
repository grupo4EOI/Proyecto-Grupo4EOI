package com.atm.buenas_practicas_java.mapper;


import com.atm.buenas_practicas_java.dtos.GeneroDTO;
import com.atm.buenas_practicas_java.dtos.UsuarioDTO;
import com.atm.buenas_practicas_java.entities.Genero;
import com.atm.buenas_practicas_java.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface PerfilMapper {

    @Mapping(target = "nombre", source = "nombre")
    GeneroDTO toDto(Genero genero);

    List<GeneroDTO> toDtoList(List<Genero> generos);

 
    @Mapping(target = "nombreUsuario", source = "nombreUsuario")
    UsuarioDTO toDto(Usuario usuario);

    List<UsuarioDTO> toDtoListUsuarios(List<Usuario> usuarios);

}
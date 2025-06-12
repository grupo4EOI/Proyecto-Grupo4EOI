package com.atm.buenas_practicas_java.mapper;

import com.atm.buenas_practicas_java.dtos.UsuarioDTO;
import com.atm.buenas_practicas_java.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(source = "nombreUsuario", target = "nombreUsuario")
    @Mapping(source = "avatarUrl", target = "avatarUrl")
    UsuarioDTO toDto(Usuario usuario);

}

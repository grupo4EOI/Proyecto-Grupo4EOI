package com.atm.buenas_practicas_java.dtos;

import java.util.List;

public record UsuarioDTO(
        String nombreUsuario,
        String email,
        String fechaRegistro,
        String urlAvatar,
        String biografia,
        String ultimaConexion,
        Boolean esAdministrador,
        List<ResenaDTO> resenas

        // TODO: Revisar qué campos faltan en este DTO para cada una de las pantallas
) { }

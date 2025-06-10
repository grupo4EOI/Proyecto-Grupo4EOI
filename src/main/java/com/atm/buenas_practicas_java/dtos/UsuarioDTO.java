package com.atm.buenas_practicas_java.dtos;


public record UsuarioDTO(
        Long idUsuario,
        String nombreUsuario,
        String avatarUrl,
        String role
) { }

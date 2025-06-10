package com.atm.buenas_practicas_java.dtos;

public record PublicacionCrearDTO(
        Long idPublicacion,
        String titulo,
        String contenido
) {
}

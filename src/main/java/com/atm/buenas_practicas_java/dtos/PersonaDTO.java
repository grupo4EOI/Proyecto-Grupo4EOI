package com.atm.buenas_practicas_java.dtos;

public record PersonaDTO(
        String nombreCompleto,
        String fechaNacimiento,
        String biografia,
        String urlFoto,
        Boolean rol
) { }

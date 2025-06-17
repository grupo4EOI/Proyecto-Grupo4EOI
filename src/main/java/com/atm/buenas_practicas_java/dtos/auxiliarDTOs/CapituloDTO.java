package com.atm.buenas_practicas_java.dtos.auxiliarDTOs;

public record CapituloDTO(
        Integer numeroCapitulo,
        String titulo,
        String sinposis,
        String imagenUrl,
        String fechaEmision
) {
}

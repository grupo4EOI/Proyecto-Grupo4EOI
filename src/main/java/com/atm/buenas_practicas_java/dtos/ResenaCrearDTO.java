package com.atm.buenas_practicas_java.dtos;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

public record ResenaCrearDTO(
        @NotBlank String titulo,
        @NotBlank String contenido,
        @DecimalMin("0.0") @DecimalMax("5.0") Double puntuacion,
        boolean spoiler
) {
}

package com.atm.buenas_practicas_java.dtos;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ResenaDTO(
        @NotBlank String titulo,
        @NotBlank String contenido,
        @DecimalMin("0.0") @DecimalMax("5.0") Double puntuacion,
        boolean spoiler,
        UsuarioDTO autor,
        List<ComentarioResenaDTO> comentariosResena
) { }

package com.atm.buenas_practicas_java.dtos;

/**
 * Este DTO se usará para mostrar la información simplificada de un objeto.
 *
 * Se usará en pantallas como la principal, o en las secciones de peliculas, series, videojuegos
 */
public record ObjetoDTO(
    String titulo,
    String imagenUrl,
    String trailerUrl,
    String anoPublicacion,
    Double puntuacion,
    Integer numeroResenas
) {
}

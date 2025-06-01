package com.atm.buenas_practicas_java.dtos;

/**
 * Este DTO se usará para mostrar la información simplificada de un objeto.
 * <p>
 * Se usará en pantallas como la principal, o en las secciones de peliculas, series, videojuegos
 */
public record ObjetoDTO(
        Long idObjeto,
        String titulo,
        String imagenUrl,
        String trailerUrl,
        Integer anoPublicacion,
        Double puntuacion,
        Integer numeroResenas
) {
}

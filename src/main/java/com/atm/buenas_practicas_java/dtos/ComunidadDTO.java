package com.atm.buenas_practicas_java.dtos;

public record ComunidadDTO (
        Long idComunidad,
        String nombreComunidad,
        String descripcion,
        String urlImg,
        Long numPublicaciones,
        Long numUsuarios
) {

}

package com.atm.buenas_practicas_java.dtos.composedDTOs;

public record EstadisticasReportesDTO(
        Long totalResenasReportadas,
        Long totalComentariosResenasReportados,
        Long totalComentariosPublicacionesReportados
) {
}

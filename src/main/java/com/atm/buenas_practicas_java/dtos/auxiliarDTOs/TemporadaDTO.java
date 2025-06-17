package com.atm.buenas_practicas_java.dtos.auxiliarDTOs;

import java.util.ArrayList;
import java.util.List;

public record TemporadaDTO(
        Integer numeroTemporada,
        List<CapituloDTO> capitulos
) {
    public TemporadaDTO(Integer numeroTemporada) {
        this(numeroTemporada, new ArrayList<>());
    }

    public void agregarCapitulo(CapituloDTO capitulo) {
        capitulos.add(capitulo);
    }
}

package com.atm.buenas_practicas_java.repositories;

import com.atm.buenas_practicas_java.dtos.ResenaDTO;
import com.atm.buenas_practicas_java.entities.Objeto;
import com.atm.buenas_practicas_java.entities.Resena;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResenaRepository extends JpaRepository<Resena, Long> {
    List<Resena> findResenasByObjeto_IdObjeto(Long objetoIdObjeto);

    List<Resena> findResenasByAbusoEquals(Boolean abuso);

    Resena findTopByOrderByFechaPublicacionDesc();
}

package com.atm.buenas_practicas_java.repositories;

import com.atm.buenas_practicas_java.entities.Resena;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResenaRepository extends JpaRepository<Resena, Long> {
    List<Resena> findResenasByObjeto_IdObjetoOrderByFechaPublicacionDesc(Long idObjeto);

    List<Resena> findResenasByObjeto_IdObjeto(Long idObjeto);

    List<Resena> findResenasByAbusoEquals(Boolean abuso);
}

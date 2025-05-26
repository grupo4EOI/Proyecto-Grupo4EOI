package com.atm.buenas_practicas_java.repositories;

import com.atm.buenas_practicas_java.entities.ComentarioResena;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioResenaRepository extends JpaRepository<ComentarioResena, Long> {
    List<ComentarioResena> getComentarioResenasByResena_IdResena(Long resenaIdResena);
}

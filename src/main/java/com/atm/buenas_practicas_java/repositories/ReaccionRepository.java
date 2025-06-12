package com.atm.buenas_practicas_java.repositories;

import com.atm.buenas_practicas_java.entities.Reaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaccionRepository extends JpaRepository<Reaccion, Long> {

    Long countByResena_IdResenaAndMeGustaEquals(Long idResena, Boolean meGusta);

    Long countByComentarioResena_IdComentarioResenaAndMeGustaEquals(Long idComentarioResena, Boolean meGusta);
}

package com.atm.buenas_practicas_java.repositories;

import com.atm.buenas_practicas_java.entities.Reaccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReaccionRepository extends JpaRepository<Reaccion, Long> {

    Optional<Reaccion> findByResena_IdResenaAndUsuario_IdUsuario(Long idResena, Long idUsuario);
    
    Long countByResena_IdResenaAndMeGustaEquals(Long idResena, Boolean meGusta);

    Long countByComentarioResena_IdComentarioResenaAndMeGustaEquals(Long idComentarioResena, Boolean meGusta);

    void deleteReaccionByIdReaccion(Long idReaccion);
}

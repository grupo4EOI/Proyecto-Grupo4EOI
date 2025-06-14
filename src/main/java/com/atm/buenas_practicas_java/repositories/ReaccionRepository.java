package com.atm.buenas_practicas_java.repositories;

import com.atm.buenas_practicas_java.entities.Reaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReaccionRepository extends JpaRepository<Reaccion, Long> {

    Optional<Reaccion> findByResena_IdResenaAndUsuario_IdUsuario(Long idResena, Long idUsuario);
    
    Long countByResena_IdResenaAndMeGustaEquals(Long idResena, Boolean meGusta);

    Long countByUsuario_IdUsuarioAndMeGustaEquals(Long idUsuario, Boolean meGusta);

    Long countByComentarioResena_IdComentarioResenaAndMeGustaEquals(Long idComentarioResena, Boolean meGusta);

    @Modifying
    @Query("DELETE FROM Reaccion r WHERE r.idReaccion = :idReaccion")
    void deleteReaccionByIdReaccion(@Param("idReaccion") Long idReaccion);
}

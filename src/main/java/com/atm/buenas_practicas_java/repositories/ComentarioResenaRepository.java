package com.atm.buenas_practicas_java.repositories;

import com.atm.buenas_practicas_java.entities.ComentarioResena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComentarioResenaRepository extends JpaRepository<ComentarioResena, Long> {
    List<ComentarioResena> findComentarioResenasByAbusoEquals(boolean b);

    @Modifying
    @Query("DELETE FROM ComentarioResena cr WHERE cr.idComentarioResena = :idComentarioResena")
    void borrarComentarioResena(@Param("idComentarioResena") Long idComentarioResena);
}

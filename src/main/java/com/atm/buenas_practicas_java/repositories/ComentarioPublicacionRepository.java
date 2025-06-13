package com.atm.buenas_practicas_java.repositories;

import com.atm.buenas_practicas_java.entities.ComentarioPublicacion;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;


public interface ComentarioPublicacionRepository extends JpaRepository<ComentarioPublicacion, Long> {
    List<ComentarioPublicacion> findComentarioPublicacionsByAbusoEquals(boolean b);
    List<ComentarioPublicacion> findComentarioPublicacionsByPublicacion_IdPublicacionOrderByIdComentarioPublicacion(Long idPublicacion);

    @Modifying
    @Transactional
    @Query("UPDATE ComentarioPublicacion c SET c.abuso = FALSE WHERE c.idComentarioPublicacion = :idComentarioPublicacion")
    void reportar(@Param("id") Long idComentarioPublicacion);

    @Modifying
    @Transactional
    @Query("UPDATE ComentarioPublicacion c SET c.baneado = FALSE WHERE c.idComentarioPublicacion = :idComentarioPublicacion")
    void banComentarioPublicacion(@Param("idComentarioPublicacion") Long idComentarioPublicacion);

    @Modifying
    @Transactional
    @Query("UPDATE ComentarioPublicacion c SET c.baneado = FALSE, c.abuso = FALSE WHERE c.idComentarioPublicacion = :idComentarioPublicacion")
    void aprobarComentarioPublicacion(@Param("idComentarioPublicacion") Long idComentarioPublicacion);
}
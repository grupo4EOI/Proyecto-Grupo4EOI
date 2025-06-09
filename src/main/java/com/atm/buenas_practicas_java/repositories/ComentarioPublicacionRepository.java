package com.atm.buenas_practicas_java.repositories;

import com.atm.buenas_practicas_java.entities.ComentarioPublicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;


public interface ComentarioPublicacionRepository extends JpaRepository<ComentarioPublicacion, Long> {
    List<ComentarioPublicacion> findComentarioPublicacionsByAbusoEquals(boolean b);
    List<ComentarioPublicacion> findComentarioPublicacionsByPublicacion_IdPublicacion(Long idPublicacion);
    @Query("UPDATE ComentarioPublicacion c SET c.abuso = true WHERE c.idComentarioPublicacion = :id")
    void reportar(@Param("id") Long id);
}
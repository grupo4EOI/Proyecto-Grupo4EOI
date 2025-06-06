package com.atm.buenas_practicas_java.repositories;

import com.atm.buenas_practicas_java.entities.ComentarioPublicacion;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface ComentarioPublicacionRepository extends JpaRepository<ComentarioPublicacion, Long> {
    List<ComentarioPublicacion> findComentarioPublicacionsByAbusoEquals(boolean b);
    List<ComentarioPublicacion> findComentarioPublicacionsByPublicacion_IdPublicacion(Long idPublicacion);
}
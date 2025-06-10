package com.atm.buenas_practicas_java.repositories;

import com.atm.buenas_practicas_java.entities.Resena;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResenaRepository extends JpaRepository<Resena, Long> {
    List<Resena> findResenasByObjeto_IdObjetoOrderByFechaPublicacionDesc(Long idObjeto);

    List<Resena> findResenasByObjeto_IdObjeto(Long idObjeto);

    List<Resena> findResenasByAbusoEquals(Boolean abuso);

    Resena findTopByOrderByFechaPublicacionDesc();

    @Modifying
    @Transactional
    @Query("UPDATE Resena r SET r.abuso = true WHERE r.idResena = :id")
    void reportarResena(@Param("id") Long id);
}

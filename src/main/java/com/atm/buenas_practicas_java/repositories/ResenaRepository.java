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
    @Query("UPDATE Resena r SET r.abuso = true WHERE r.idResena = :idResena")
    void reportarResena(@Param("idResena") Long idResena);

    @Modifying
    @Transactional
    @Query("UPDATE Resena r SET r.spoiler = true WHERE r.idResena = :idResena")
    void reportarSpoilerResena(@Param("idResena") Long idResena);

    @Modifying
    @Transactional
    @Query("DELETE FROM Resena r WHERE r.idResena = :idResena")
    void borrarResenaPorIdResena(@Param("idResena") Long idResena);

    @Modifying
    @Transactional
    @Query("UPDATE Resena r SET r.abuso = false WHERE r.idResena = :idResena")
    void aprobarResena(@Param("idResena") Long idResena);

    Long countResenaByAbusoEquals(Boolean abuso);
}

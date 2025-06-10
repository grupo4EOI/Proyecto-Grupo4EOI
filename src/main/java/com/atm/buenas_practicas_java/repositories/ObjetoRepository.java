package com.atm.buenas_practicas_java.repositories;

import com.atm.buenas_practicas_java.entities.Objeto;
import com.atm.buenas_practicas_java.entities.Tipo;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ObjetoRepository extends JpaRepository<Objeto, Long> {

    List<Objeto> findObjetosByTipo_NombreOrderByFechaPublicacionDesc(String tipo, Limit limit);

    @Query("""
        SELECT o
            FROM Objeto o
            WHERE o.tipo.nombre = :tipo
            ORDER BY (
                SELECT COALESCE(AVG(r.puntuacion), 0)
                FROM Resena r
                WHERE r.objeto = o
            ) DESC
    """)
    List<Objeto> buscarListaObjetosMejorValoradosPorTipo(@Param("tipo") String tipo, Limit limit);

    @Query("""
    SELECT o FROM Objeto o
    WHERE LOWER(o.titulo) LIKE LOWER(CONCAT('%', :filtro, '%'))
""")
    List<Objeto> buscarPorTitulo(@Param("filtro") String filtro);
    List<Objeto> findByTituloContainingIgnoreCase(String titulo); //Búsqueda Parcial y no sensible a MAYUS/MINUS
}

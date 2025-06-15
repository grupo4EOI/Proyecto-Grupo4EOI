package com.atm.buenas_practicas_java.repositories;

import com.atm.buenas_practicas_java.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PerfilRepository extends JpaRepository<Usuario, Long> {
    @Query("""
    SELECT DISTINCT u FROM Usuario u 
    LEFT JOIN FETCH u.generos g 
    LEFT JOIN FETCH g.tipo 
    LEFT JOIN FETCH u.amigos a 
    LEFT JOIN FETCH a.amigo 
    LEFT JOIN FETCH u.publicaciones p 
    LEFT JOIN FETCH p.comunidad 
    WHERE u.idUsuario = :id
""")
    Optional<Usuario> findByIdUsuario(@Param("id") Long idUsuario);
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

}


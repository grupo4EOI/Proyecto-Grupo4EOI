package com.atm.buenas_practicas_java.repositories;

import com.atm.buenas_practicas_java.entities.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

    @Modifying
    @Transactional
    @Query("UPDATE Usuario u SET u.baneado = true WHERE u.idUsuario = :idUsuario")
    void banUsuario(@Param("idUsuario") Long idUsuario);

    boolean existsByNombreUsuarioAndIdUsuarioNot(String nombreUsuario, Long idUsuario);

    @Query("""
        SELECT DISTINCT u
            FROM Usuario u
            WHERE u.idUsuario IN (
                SELECT CASE
                    WHEN a.usuario.idUsuario = :idUsuario THEN a.amigo.idUsuario
                    WHEN a.amigo.idUsuario = :idUsuario THEN a.usuario.idUsuario
                END
                FROM Amistad a
                    WHERE (a.usuario.idUsuario = :idUsuario OR a.amigo.idUsuario = :idUsuario)
                    AND a.estado = true
            )
    """)
    List<Usuario> buscarAmistadesUsuario(@Param("idUsuario") Long idUsuario);

    // Nuevos métodos para verificación de existencia
    boolean existsByNombreUsuario(String nombreUsuario);

    boolean existsByEmail(String email);
}

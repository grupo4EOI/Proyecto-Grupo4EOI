package com.atm.buenas_practicas_java.repositories;

import com.atm.buenas_practicas_java.entities.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

    @Modifying
    @Transactional
    @Query("UPDATE Usuario u SET u.baneado = true WHERE u.idUsuario = :idUsuario")
    void banUsuario(@Param("idUsuario") Long idUsuario);
}

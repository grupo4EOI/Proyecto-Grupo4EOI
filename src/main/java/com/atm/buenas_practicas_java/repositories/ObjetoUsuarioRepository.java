package com.atm.buenas_practicas_java.repositories;

import com.atm.buenas_practicas_java.entities.ObjetoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ObjetoUsuarioRepository extends JpaRepository<ObjetoUsuario, Long> {
    Optional<ObjetoUsuario> findByObjeto_IdObjetoAndUsuario_IdUsuario(Long idObjeto, Long idUsuario);
}

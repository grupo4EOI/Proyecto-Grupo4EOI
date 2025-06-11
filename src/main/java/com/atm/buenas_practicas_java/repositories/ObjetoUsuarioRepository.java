package com.atm.buenas_practicas_java.repositories;

import com.atm.buenas_practicas_java.entities.ObjetoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjetoUsuarioRepository extends JpaRepository<ObjetoUsuario, Long> {
}

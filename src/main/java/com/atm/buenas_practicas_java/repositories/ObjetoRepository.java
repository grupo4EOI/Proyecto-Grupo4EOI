package com.atm.buenas_practicas_java.repositories;

import com.atm.buenas_practicas_java.entities.Objeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ObjetoRepository extends JpaRepository<Objeto, Long> {
}

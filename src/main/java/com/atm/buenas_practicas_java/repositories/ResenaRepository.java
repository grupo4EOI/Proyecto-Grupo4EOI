package com.atm.buenas_practicas_java.repositories;

import com.atm.buenas_practicas_java.entities.Resena;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResenaRepository extends JpaRepository<Resena, Long> {
}

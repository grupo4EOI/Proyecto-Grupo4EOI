package com.atm.buenas_practicas_java.repositories;

import com.atm.buenas_practicas_java.entities.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroRepository extends JpaRepository<Genero, Integer> {
}

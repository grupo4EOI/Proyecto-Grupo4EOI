package com.atm.buenas_practicas_java.repositories;

import com.atm.buenas_practicas_java.entities.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoRepository extends JpaRepository<Tipo, Integer> {
    Optional<Tipo> findByNombre(String nombre);
}

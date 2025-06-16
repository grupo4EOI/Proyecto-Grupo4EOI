package com.atm.buenas_practicas_java.repositories;

import com.atm.buenas_practicas_java.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    Optional<Persona> findByNombreCompleto(String nombreCompleto);
}

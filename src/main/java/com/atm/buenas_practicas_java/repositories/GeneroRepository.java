package com.atm.buenas_practicas_java.repositories;

import com.atm.buenas_practicas_java.entities.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GeneroRepository extends JpaRepository<Genero, Integer> {
    List<Genero> findByTipoNombreIgnoreCase(String nombreTipo);

    Optional<Genero> findByNombreAndTipo_Nombre(String nombre, String tipoNombre);

    List<Genero> findAll();
}

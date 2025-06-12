package com.atm.buenas_practicas_java.repositories;

import com.atm.buenas_practicas_java.entities.Amistad;
import com.atm.buenas_practicas_java.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface AmistadRepository extends JpaRepository<Amistad, Long> {

}

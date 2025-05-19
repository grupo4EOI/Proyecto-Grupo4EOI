package com.atm.buenas_practicas_java.repositories;

import com.atm.buenas_practicas_java.entities.PersonaObjeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaObjetoRepository extends JpaRepository<PersonaObjeto, Long> {
}

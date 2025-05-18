package com.atm.buenas_practicas_java.repositories;

import com.atm.buenas_practicas_java.entities.Objeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjetoRepository extends JpaRepository<Objeto, Long> {
    Objeto findObjetoByIdObjeto(Long idObjeto);
}

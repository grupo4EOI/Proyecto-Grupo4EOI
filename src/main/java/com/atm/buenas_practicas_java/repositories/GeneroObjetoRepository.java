package com.atm.buenas_practicas_java.repositories;

import com.atm.buenas_practicas_java.entities.GeneroObjeto;
import com.atm.buenas_practicas_java.entities.Objeto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GeneroObjetoRepository extends JpaRepository<GeneroObjeto, Integer> {
    List<GeneroObjeto> findByIdObjeto(Long idObjeto);


}

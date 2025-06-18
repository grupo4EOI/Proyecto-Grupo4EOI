package com.atm.buenas_practicas_java.repositories;

import com.atm.buenas_practicas_java.entities.Comunidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComunidadRepository extends JpaRepository<Comunidad, Long> {
    Comunidad findComunidadByIdComunidad(Long idComunidad);
}

package com.atm.buenas_practicas_java.repositories;

import com.atm.buenas_practicas_java.entities.Comunidad;
import com.atm.buenas_practicas_java.entities.Publicacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {
    List<Publicacion> getPublicacionsByComunidad(Comunidad comunidad);

    List<Publicacion> findPublicacionsByComunidad_IdComunidad(Long idComunidad);
}
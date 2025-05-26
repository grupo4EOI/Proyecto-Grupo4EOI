package com.atm.buenas_practicas_java.repositories;

import com.atm.buenas_practicas_java.entities.PersonaObjeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonaObjetoRepository extends JpaRepository<PersonaObjeto, Long> {

    @Query("SELECT po FROM PersonaObjeto po WHERE po.objeto.idObjeto = :idObjeto AND po.rol = :rol")
    List<PersonaObjeto> findByObjetoIdAndRol(@Param("idObjeto") Long idObjeto, @Param("rol") boolean rol);

}

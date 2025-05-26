package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.entities.Persona;
import com.atm.buenas_practicas_java.entities.PersonaObjeto;
import com.atm.buenas_practicas_java.repositories.PersonaObjetoRepository;
import com.atm.buenas_practicas_java.repositories.PersonaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaService {

    private final PersonaObjetoRepository personaObjetoRepository;

    public PersonaService(PersonaObjetoRepository personaObjetoRepository) {
        this.personaObjetoRepository = personaObjetoRepository;
    }

    // Recordamos que el rol = false si es actor, y rol = true si es director
    private List<Persona> obtenerPersonasPorRol(Long idObjeto, boolean rol) {
        List<PersonaObjeto> relaciones = personaObjetoRepository.findByObjetoIdAndRol(idObjeto, rol);
        return relaciones.stream()
                .map(PersonaObjeto::getPersona)
                .collect(Collectors.toList());
    }

    public List<Persona> getActoresByObjetoId(Long idObjeto) {
        return obtenerPersonasPorRol(idObjeto, false);
    }

    public List<Persona> getDirectoresByObjetoId(Long idObjeto) {
        return obtenerPersonasPorRol(idObjeto, true);
    }
}

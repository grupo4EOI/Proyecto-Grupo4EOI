package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.dtos.PersonaDTO;
import com.atm.buenas_practicas_java.entities.PersonaObjeto;
import com.atm.buenas_practicas_java.mapper.PersonaMapper;
import com.atm.buenas_practicas_java.repositories.PersonaObjetoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaService {

    private final PersonaObjetoRepository personaObjetoRepository;
    private final PersonaMapper personaMapper;

    public PersonaService(PersonaObjetoRepository personaObjetoRepository, PersonaMapper personaMapper) {
        this.personaObjetoRepository = personaObjetoRepository;
        this.personaMapper = personaMapper;
    }

    // Recordamos que el rol = false si es actor, y rol = true si es director
    private List<PersonaDTO> obtenerPersonasPorRol(Long idObjeto, boolean rol) {
        List<PersonaObjeto> relaciones = personaObjetoRepository.findByObjetoIdAndRol(idObjeto, rol);
        return relaciones.stream()
                .map(PersonaObjeto::getPersona)
                .map(personaMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<PersonaDTO> getActoresByObjetoId(Long idObjeto) {
        return obtenerPersonasPorRol(idObjeto, false);
    }

    public List<PersonaDTO> getDirectoresByObjetoId(Long idObjeto) {
        return obtenerPersonasPorRol(idObjeto, true);
    }
}

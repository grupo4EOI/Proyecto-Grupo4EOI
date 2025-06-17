package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.dtos.PersonaDTO;
import com.atm.buenas_practicas_java.entities.Persona;
import com.atm.buenas_practicas_java.entities.PersonaObjeto;
import com.atm.buenas_practicas_java.mapper.PersonaMapper;
import com.atm.buenas_practicas_java.repositories.PersonaObjetoRepository;
import com.atm.buenas_practicas_java.repositories.PersonaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonaService {

    private final PersonaObjetoRepository personaObjetoRepository;
    private final PersonaMapper personaMapper;
    private final PersonaRepository personaRepository;

    public PersonaService(PersonaObjetoRepository personaObjetoRepository, PersonaMapper personaMapper, PersonaRepository personaRepository) {
        this.personaObjetoRepository = personaObjetoRepository;
        this.personaMapper = personaMapper;
        this.personaRepository = personaRepository;
    }

    public Persona save(Persona persona) {
        return personaRepository.save(persona);
    }

    // Recordamos que el rol = false si es actor, y rol = true si es director
    private List<PersonaDTO> obtenerPersonasPorRol(Long idObjeto, boolean rol) {
        List<PersonaObjeto> relaciones = personaObjetoRepository.findByObjetoIdAndRol(idObjeto, rol);
        return relaciones.stream()
                .map(PersonaObjeto::getPersona)
                .map(personaMapper::toDto)
                .toList();
    }

    public List<PersonaDTO> getActoresByObjetoId(Long idObjeto) {
        return obtenerPersonasPorRol(idObjeto, false);
    }

    public List<PersonaDTO> getDirectoresByObjetoId(Long idObjeto) {
        return obtenerPersonasPorRol(idObjeto, true);
    }

    public Optional<Persona> findByNombreCompleto(String nombre) {
        return personaRepository.findByNombreCompleto(nombre);
    }
}

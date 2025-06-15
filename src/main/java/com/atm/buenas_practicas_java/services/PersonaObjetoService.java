package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.entities.PersonaObjeto;
import com.atm.buenas_practicas_java.repositories.PersonaObjetoRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonaObjetoService {

    private final PersonaObjetoRepository personaObjetoRepository;

    public PersonaObjetoService(PersonaObjetoRepository personaObjetoRepository) {
        this.personaObjetoRepository = personaObjetoRepository;
    }

    public PersonaObjeto save(PersonaObjeto personaObjeto) {
        return personaObjetoRepository.save(personaObjeto);
    }

}

package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.repositories.ReaccionRepository;
import org.springframework.stereotype.Service;

@Service
public class ReaccionService {

    private final ReaccionRepository reaccionRepository;

    public ReaccionService(ReaccionRepository reaccionRepository) {
        this.reaccionRepository = reaccionRepository;
    }

    public Long contarLikesResena(Long idResena) {
        return reaccionRepository.countByResena_IdResenaAndMeGustaEquals(idResena, true);
    }

    public Long contarLikesComentarioResena(Long idComentarioResena) {
        return reaccionRepository.countByComentarioResena_IdComentarioResenaAndMeGustaEquals(idComentarioResena, true);
    }
}

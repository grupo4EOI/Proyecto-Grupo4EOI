package com.atm.buenas_practicas_java.repositories;

import com.atm.buenas_practicas_java.entities.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);
}
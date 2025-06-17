package com.atm.buenas_practicas_java.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(String to, String token) {
        String subject = "Verifica tu cuenta";
        String verificationUrl = "http://localhost:8080/api/auth/verify?token=" + token;

        String content = "Hola,\n\nHaz clic en el siguiente enlace para verificar tu cuenta:\n"
                + verificationUrl + "\n\nSi no te registraste, ignora este mensaje.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        mailSender.send(message);
    }
}


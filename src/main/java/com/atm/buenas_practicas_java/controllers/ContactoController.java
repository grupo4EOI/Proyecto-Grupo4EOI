package com.atm.buenas_practicas_java.controllers;

import com.atm.buenas_practicas_java.services.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContactoController {

    private final EmailService emailService;

    public ContactoController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/contacto")
    public String mostrarPaginaContacto() {
        return "contacto"; // Nombre de tu plantilla Thymeleaf
    }


    @PostMapping("/contacto")
    @ResponseBody
    public ResponseEntity<String> procesarFormularioContacto(
            @RequestParam String nombre,
            @RequestParam String email,
            @RequestParam String asunto,
            @RequestParam String mensaje) {

        try {
            // Enviar email
            emailService.enviarEmailContacto(email, nombre, asunto, mensaje);

            // Responder OK
            return ResponseEntity.ok("Mensaje enviado");
        } catch (Exception e) {
            e.printStackTrace();
            // También respondemos 200 para evitar fallo en fetch, pero podrías usar 500 si deseas controlar el error en JS
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al enviar mensaje");
        }
    }
}

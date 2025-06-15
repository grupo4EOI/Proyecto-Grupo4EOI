package com.atm.buenas_practicas_java.controllers;

import com.atm.buenas_practicas_java.services.EmailService;
import com.atm.buenas_practicas_java.services.ObjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String procesarFormularioContacto(
            @RequestParam String nombre,
            @RequestParam String email,
            @RequestParam String asunto,
            @RequestParam String mensaje) {

        try {
            // Enviar email
            emailService.enviarEmailContacto("tu-email@empresa.com", nombre, asunto, mensaje);

            // También podrías guardar en base de datos aquí

            return "redirect:/contacto?success";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/contacto?error";
        }
    }
}

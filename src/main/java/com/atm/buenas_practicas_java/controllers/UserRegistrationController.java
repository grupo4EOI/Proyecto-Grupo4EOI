package com.atm.buenas_practicas_java.controllers;

import com.atm.buenas_practicas_java.entities.UserForm;
import com.atm.buenas_practicas_java.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registro")
@RequiredArgsConstructor

public class UserRegistrationController {

    private final UsuarioService usuarioService;

    @GetMapping
    public String registroUsuario() {
        return "registro";
    }

    @PostMapping
    public String doRegister(@Valid UserForm userForm) {
        usuarioService.registerUser(userForm);
        return "redirect:/login";
    }
}

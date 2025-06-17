package com.atm.buenas_practicas_java.controllers;

import com.atm.buenas_practicas_java.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String email,
                           @RequestParam String password,
                           Model model) {

        authService.registerUser(username, email, password);

        // Puedes mostrar un mensaje opcionalmente con el Model
        model.addAttribute("mensaje", "Registro exitoso. Revisa tu correo para verificar tu cuenta.");

        // Redirige a la página principal (debe existir la ruta en el controlador)
        return "redirect:/pagina-principal";
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verify(@RequestParam String token) {
        String result = authService.verifyUser(token);
        return ResponseEntity.ok(result);
    }
}

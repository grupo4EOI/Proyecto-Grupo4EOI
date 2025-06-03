package com.atm.buenas_practicas_java.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/iniciar-sesion")
    public String mostrarLogin(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model) {
        if (error != null) {
            model.addAttribute("error", "Credenciale inválidas");
        }
        if (logout != null) {
            model.addAttribute("logout", "Sesión cerrada correctamente");
        }
        return "iniciar-sesion";
    }

}

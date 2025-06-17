package com.atm.buenas_practicas_java.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaPrincipalController {

    @GetMapping("/pagina-principal")
    public String mostrarPaginaPrincipal() {
        return "pagina-principal"; // Este es el nombre del archivo HTML en `templates/`
    }
}


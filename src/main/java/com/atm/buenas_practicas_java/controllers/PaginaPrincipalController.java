package com.atm.buenas_practicas_java.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/", "/pagina-principal"})
public class PaginaPrincipalController {

    @GetMapping
    public String mostrarPaginaPrincipal() {
        return "/pagina-principal";
    }
}

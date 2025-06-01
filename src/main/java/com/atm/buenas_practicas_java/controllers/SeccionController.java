package com.atm.buenas_practicas_java.controllers;

import com.atm.buenas_practicas_java.services.ObjetoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SeccionController {

    private final ObjetoService objetoService;

    public SeccionController(ObjetoService objetoService) {
        this.objetoService = objetoService;
    }

    @GetMapping("/seccion/{tipo}s")
    public String mostrarSeccion(Model model, @PathVariable String tipo) {
        model.addAttribute("seccion", objetoService.construirSeccionDTO(tipo));
        return "/seccion";
    }
}

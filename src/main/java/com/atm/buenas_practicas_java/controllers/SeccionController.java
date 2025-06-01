package com.atm.buenas_practicas_java.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SeccionController {



    @GetMapping("/seccion/{idTipoSeccion}")
    public String mostrarSeccion(@PathVariable int idTipoSeccion, Model model) {
        return "/seccion";
    }
}

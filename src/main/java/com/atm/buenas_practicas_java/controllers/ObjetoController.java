package com.atm.buenas_practicas_java.controllers;

import com.atm.buenas_practicas_java.entities.Objeto;
import com.atm.buenas_practicas_java.services.ObjetoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ObjetoController {

    private final ObjetoService objetoService;

    public ObjetoController(ObjetoService objetoService) {
        this.objetoService = objetoService;
    }

    @GetMapping("/ficha-objeto/{id}")
    public String mostrarFichaObjeto(Model model, @PathVariable Long id) {
        Objeto objeto = objetoService.findById(id);
        model.addAttribute("objeto", objeto);
        model.addAttribute("puntuacion", objetoService.calcularPuntuacionObjeto(objeto));
        return "ficha-objeto";
    }
}

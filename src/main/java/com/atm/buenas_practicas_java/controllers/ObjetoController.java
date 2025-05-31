package com.atm.buenas_practicas_java.controllers;

import com.atm.buenas_practicas_java.entities.Objeto;
import com.atm.buenas_practicas_java.entities.Resena;
import com.atm.buenas_practicas_java.mapper.FichaObjetoMapper;
import com.atm.buenas_practicas_java.services.*;
import com.atm.buenas_practicas_java.services.facade.FichaObjetoFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.stream.Collectors;

@Controller
public class ObjetoController {

    FichaObjetoFacade fichaObjetoFacade;

    public ObjetoController(FichaObjetoFacade fichaObjetoFacade) {
        this.fichaObjetoFacade = fichaObjetoFacade;
    }

    // TODO: Revisar los métodos en los servicios que tomen como parámetro el objeto entero. Cambiar por idObjeto.
    @GetMapping("/ficha-objeto/{id}")
    public String mostrarFichaObjeto(Model model, @PathVariable Long id) {
        model.addAttribute("fichaObjeto", fichaObjetoFacade.construirFichaObjeto(id));
        model.addAttribute("nuevaResena", new Resena());

        return "/ficha-objeto";
    }

    // TODO: Revisar cómo implementarlo en el facade
    @PostMapping("/ficha-objeto/{id}")
    public String nuevaResena(@PathVariable Long id, @ModelAttribute("nuevaResena") Resena resena) {
        return String.format("redirect:/ficha-objeto/%d", id);
    }
}

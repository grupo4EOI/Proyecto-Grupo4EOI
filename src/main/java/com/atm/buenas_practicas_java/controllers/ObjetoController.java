package com.atm.buenas_practicas_java.controllers;

import com.atm.buenas_practicas_java.entities.Objeto;
import com.atm.buenas_practicas_java.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.stream.Collectors;

@Controller
public class ObjetoController {

    private final ObjetoService objetoService;
    private final GeneroService generoService;
    private final ResenaService resenaService;
    private final PersonaService personaService;
    private final ComentarioResenaService comentarioResenaService;

    public ObjetoController(ObjetoService objetoService,
                            GeneroService generoService,
                            ResenaService resenaService,
                            PersonaService personaService,
                            ComentarioResenaService comentarioResenaService) {
        this.objetoService = objetoService;
        this.generoService = generoService;
        this.resenaService = resenaService;
        this.personaService = personaService;
        this.comentarioResenaService = comentarioResenaService;
    }

    // TODO: Revisar los métodos en los servicios que tomen como parámetro el objeto entero. Cambiar por idObjeto.
    @GetMapping("/ficha-objeto/{id}")
    public String mostrarFichaObjeto(Model model, @PathVariable Long id) {
        Objeto objeto = objetoService.findById(id);
        model.addAttribute("objeto", objeto);
        model.addAttribute("puntuacion", objetoService.calcularPuntuacionObjeto(objeto));
        model.addAttribute("listaGeneros", generoService.obtenerGenerosPorObjeto(id));
        model.addAttribute("numeroResenas", objetoService.calcularNumeroResenas(objeto));
        model.addAttribute("listaResenas", resenaService.findResenasByObjeto(objeto));
        model.addAttribute("directores", personaService.getDirectoresByObjetoId(id));
        model.addAttribute("actores", personaService.getActoresByObjetoId(id));
        return "ficha-objeto";
    }
}

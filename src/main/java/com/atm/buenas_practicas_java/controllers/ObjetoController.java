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

    private final ObjetoService objetoService;
    private final GeneroService generoService;
    private final ResenaService resenaService;
    private final PersonaService personaService;
    private final ComentarioPublicacionService comentarioPublicacionService;

    public ObjetoController(
            ObjetoService objetoService,
            GeneroService generoService,
            ResenaService resenaService,
            PersonaService personaService,
            ComentarioPublicacionService comentarioPublicacionService
    ) {
        this.objetoService = objetoService;
        this.generoService = generoService;
        this.resenaService = resenaService;
        this.personaService = personaService;
        this.comentarioPublicacionService = comentarioPublicacionService;
    }

    // TODO: Revisar los métodos en los servicios que tomen como parámetro el objeto entero. Cambiar por idObjeto.
    @GetMapping("/ficha-objeto/{id}")
    public String mostrarFichaObjeto(Model model, @PathVariable Long id) {
        Objeto objeto = objetoService.findById(id);

        model.addAttribute("objeto", objeto);
        model.addAttribute("puntuacion", objetoService.calcularPuntuacionObjeto(id));
        model.addAttribute("listaGeneros", generoService.obtenerGenerosPorObjeto(id));
        model.addAttribute("numeroResenas", objetoService.calcularNumeroResenas(id));
        model.addAttribute("listaResenas", resenaService.findResenasByObjeto(id));
        model.addAttribute("directores", personaService.getDirectoresByObjetoId(id));
        model.addAttribute("actores", personaService.getActoresByObjetoId(id));
        model.addAttribute("publicaciones", comentarioPublicacionService.getPrimerosComentariosObjeto(id));
        model.addAttribute("nuevaResena", new Resena());

        return "ficha-objeto";
    }

    @PostMapping("/ficha-objeto/{id}")
    public String nuevaResena(@PathVariable Long id, @ModelAttribute("nuevaResena") Resena resena) {
        resenaService.nuevaResena(id, resena);
        return String.format("redirect:/ficha-objeto/%d", id);
    }
}

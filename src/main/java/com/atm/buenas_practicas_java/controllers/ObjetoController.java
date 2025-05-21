package com.atm.buenas_practicas_java.controllers;

import com.atm.buenas_practicas_java.entities.Objeto;
import com.atm.buenas_practicas_java.services.ComentarioPublicacionService;
import com.atm.buenas_practicas_java.services.GeneroService;
import com.atm.buenas_practicas_java.services.ObjetoService;
import com.atm.buenas_practicas_java.services.ResenaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ObjetoController {

    private final ObjetoService objetoService;
    private final GeneroService generoService;
    private final ResenaService resenaService;
    private final ComentarioPublicacionService comentarioPublicacionService;

    public ObjetoController(ObjetoService objetoService,
                            GeneroService generoService,
                            ResenaService resenaService,
                            ComentarioPublicacionService comentarioPublicacionService) {
        this.objetoService = objetoService;
        this.generoService = generoService;
        this.resenaService = resenaService;
        this.comentarioPublicacionService = comentarioPublicacionService;
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
//        model.addAttribute("publicaciones", comentarioPublicacionService.getPrimerosComentariosObjeto(id));
        return "ficha-objeto";
    }
}

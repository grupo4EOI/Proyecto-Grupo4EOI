package com.atm.buenas_practicas_java.controllers;

import com.atm.buenas_practicas_java.services.ObjetoAPIService;
import info.movito.themoviedbapi.tools.TmdbException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminObjetoController {

    private final ObjetoAPIService objetoAPIService;

    public AdminObjetoController(ObjetoAPIService objetoAPIService) {
        this.objetoAPIService = objetoAPIService;
    }

    @GetMapping("/admin/nuevoobjeto")
    public String mostrarBusqueda(Model model, String query) throws TmdbException {
        model.addAttribute("resultado", objetoAPIService.buscarPeliculas("Harry Potter"));
        return "/admin-panel/nuevoobjeto";
    }

}

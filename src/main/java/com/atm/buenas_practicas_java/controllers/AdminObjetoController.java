package com.atm.buenas_practicas_java.controllers;

import com.atm.buenas_practicas_java.services.ObjetoAPIService;
import info.movito.themoviedbapi.tools.TmdbException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminObjetoController {

    private final ObjetoAPIService objetoAPIService;

    public AdminObjetoController(ObjetoAPIService objetoAPIService) {
        this.objetoAPIService = objetoAPIService;
    }

    @GetMapping("/admin/nuevoobjeto")
    public String mostrarResultadosBusqueda() {
        return "/admin-panel/nuevoobjeto";
    }

    @GetMapping("/admin/nuevoobjeto/buscar")
    public String mostrarBusqueda(Model model, @RequestParam String query) throws TmdbException {
        model.addAttribute("resultado", objetoAPIService.buscarPeliculas(query));
        return "/admin-panel/nuevoobjeto";
    }
}

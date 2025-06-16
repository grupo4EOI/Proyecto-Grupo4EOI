package com.atm.buenas_practicas_java.controllers;

import com.atm.buenas_practicas_java.dtos.ObjetoCrearDTO;
import com.atm.buenas_practicas_java.services.ObjetoAPIService;
import com.atm.buenas_practicas_java.services.facade.AdminServiceFacade;
import info.movito.themoviedbapi.tools.TmdbException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminObjetoController {

    private final ObjetoAPIService objetoAPIService;
    private final AdminServiceFacade adminServiceFacade;

    public AdminObjetoController(ObjetoAPIService objetoAPIService, AdminServiceFacade adminServiceFacade) {
        this.objetoAPIService = objetoAPIService;
        this.adminServiceFacade = adminServiceFacade;
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

    @GetMapping("/admin/nuevoobjeto/form")
    public String mostrarFormulario(@RequestParam Integer tmdbId, Model model) throws TmdbException {
        ObjetoCrearDTO pelicula = objetoAPIService.obtenerDetallesCompletos(tmdbId);
        model.addAttribute("pelicula", pelicula);
        return "/admin-panel/form";
    }

    @PostMapping("/admin/nuevoobjeto/guardar")
    public String guardarObjeto(@ModelAttribute ObjetoCrearDTO objetoDTO) {
        adminServiceFacade.guardarPelicula(objetoDTO);
        return "redirect:/admin/nuevoobjeto";
    }
}

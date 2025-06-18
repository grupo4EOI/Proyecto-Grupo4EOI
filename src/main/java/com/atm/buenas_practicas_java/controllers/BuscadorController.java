package com.atm.buenas_practicas_java.controllers;

import com.atm.buenas_practicas_java.dtos.ObjetoDTO;
import com.atm.buenas_practicas_java.entities.Objeto;
import com.atm.buenas_practicas_java.repositories.ObjetoRepository;
import com.atm.buenas_practicas_java.services.ObjetoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BuscadorController {

    private final ObjetoService objetoService;

    public BuscadorController(ObjetoService objetoService) {
        this.objetoService = objetoService;
    }

    @GetMapping("/buscar")
    public String mostrarFormularioBusqueda() {
        return "buscar";
    }

    @GetMapping("/buscador-resultado")
    public String buscarPorTitulo(@RequestParam("q") String filtro, Model model) {
        if (filtro == null || filtro.trim().isEmpty()) {
            model.addAttribute("resultados", List.of());
            model.addAttribute("filtro", filtro);
            return "buscador-resultado";
        }

        List<ObjetoDTO> resultados = objetoService.findByTituloContainingIgnoreCase(filtro.trim());
        model.addAttribute("resultados", resultados);
        model.addAttribute("filtro", filtro);
        return "buscador-resultado";
    }
}
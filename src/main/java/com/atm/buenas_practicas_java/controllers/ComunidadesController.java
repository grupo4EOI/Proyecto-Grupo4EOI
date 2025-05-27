package com.atm.buenas_practicas_java.controllers;

import com.atm.buenas_practicas_java.entities.Comunidad;
import com.atm.buenas_practicas_java.entities.Publicacion;
import com.atm.buenas_practicas_java.entities.Usuario;
import com.atm.buenas_practicas_java.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/comunidades")
public class ComunidadesController {

    private ComunidadService comunidadService;
    private PublicacionService publicacionService;

    public ComunidadesController(ComunidadService comunidadService, PublicacionService publicacionService) {
        this.comunidadService = comunidadService;
        this.publicacionService = publicacionService;
    }

    @GetMapping
    public String mostrarComunidades(Model model) {
        List<Comunidad> comunidades = comunidadService.findAll();
        model.addAttribute("comunidades", comunidades);
        return "comunidades";
    }

    @GetMapping("/{id}/temas")
    public String mostrarTemas(Model model, @PathVariable Long id) {
        List<Publicacion> publicaciones = publicacionService.getPublicacionsByComunidad(comunidadService.findById(id));
        model.addAttribute("publicaciones", publicaciones);
        return "comunidad";
    }
}

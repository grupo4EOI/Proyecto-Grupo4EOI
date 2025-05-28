package com.atm.buenas_practicas_java.controllers;

import com.atm.buenas_practicas_java.entities.ComentarioPublicacion;
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
    private ComentarioPublicacionService comPubService;

    public ComunidadesController(ComunidadService comunidadService, PublicacionService publicacionService, ComentarioPublicacionService comPubService) {
        this.comunidadService = comunidadService;
        this.publicacionService = publicacionService;
        this.comPubService = comPubService;
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

    @GetMapping("/{idcom}/temas/{id}")
    public String mostrarComentarios(Model model, @PathVariable Long idcom, @PathVariable Long id) {
        Comunidad com = comunidadService.findById(idcom);
        List<ComentarioPublicacion> comentarios = comPubService.getComentarioPublicacionByPublicacionId(id);
        model.addAttribute("comunidad", com);
        model.addAttribute("comentarios", comentarios);
        return "ejemplo-tema";
    }


}

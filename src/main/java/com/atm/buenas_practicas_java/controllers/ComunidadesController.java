package com.atm.buenas_practicas_java.controllers;

import com.atm.buenas_practicas_java.entities.Comunidad;
import com.atm.buenas_practicas_java.services.ComentarioPublicacionService;
import com.atm.buenas_practicas_java.services.ComunidadService;
import com.atm.buenas_practicas_java.services.ObjetoService;
import com.atm.buenas_practicas_java.services.UsuarioService;
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

    @Autowired
    private ComentarioPublicacionService comPubService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ComunidadService comunidadService;
    @Autowired
    private ObjetoService objetoService;

    @GetMapping
    public String mostrarComunidades(Model model) {
        List<Comunidad> comunidades = comunidadService.findAll();
        model.addAttribute("comunidades", comunidades);
        return "comunidades";
    }
}

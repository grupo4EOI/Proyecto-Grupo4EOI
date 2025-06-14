package com.atm.buenas_practicas_java.controllers;

import com.atm.buenas_practicas_java.dtos.composedDTOs.AjustesPerfilDTO;
import com.atm.buenas_practicas_java.dtos.composedDTOs.UsuarioPerfilDTO;
import com.atm.buenas_practicas_java.services.AjustesPerfilService;
import com.atm.buenas_practicas_java.services.GeneroService;
import com.atm.buenas_practicas_java.services.PerfilService;
import com.atm.buenas_practicas_java.services.facade.PerfilServiceFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PerfilController {

    private final PerfilServiceFacade perfilServiceFacade;

    private final PerfilService perfilService;
    private final AjustesPerfilService ajustesPerfilService;
    private final GeneroService generoService;

    public PerfilController(PerfilServiceFacade perfilServiceFacade, PerfilService perfilService, AjustesPerfilService ajustesPerfilService1, GeneroService generoService) {
        this.perfilServiceFacade = perfilServiceFacade;
        this.perfilService = perfilService;
        this.ajustesPerfilService = ajustesPerfilService1;
        this.generoService = generoService;
    }

    // Perfil de usuario.
    @GetMapping("/perfil/{id}")
    public String mostrarPerfil(@PathVariable Long id, Model model) {
        UsuarioPerfilDTO usuario = perfilServiceFacade.obtenerPerfilDTO(id);
        model.addAttribute("perfil", usuario);
        return "perfil";
    }

    @PostMapping("/perfil/editarBiografia")
    public String editarBiografia(Long idUsuario, String biografia) {
        perfilServiceFacade.editarBiografia(idUsuario, biografia);
        return "redirect:/perfil/" + idUsuario;
    }

    // Ajustes del perfil de usuario.
    @GetMapping("/ajustes-perfil/{id}")
    public String mostrarAjustesPerfil(@PathVariable Long id, Model model) {
        AjustesPerfilDTO ajustesPerfilDTO = perfilServiceFacade.obtenerAjustesPerfil(id);
        model.addAttribute("ajustesPerfil", ajustesPerfilDTO);
        model.addAttribute("generosPeliculas", ajustesPerfilDTO.generosPeliculas());
        model.addAttribute("generosSeries", ajustesPerfilDTO.generosSeries());
        model.addAttribute("generosVideojuegos", ajustesPerfilDTO.generosVideojuegos());
        return "ajustes-perfil";
    }

    @PostMapping("/ajustes-perfil/{id}")
    public String guardarAjustesPerfil(
            @PathVariable Long id,
            @ModelAttribute AjustesPerfilDTO ajustesPerfilDTO
    ) {
        perfilServiceFacade.guardarAjustesPerfil(id, ajustesPerfilDTO);
        return "redirect:/perfil/" + id;
    }
}

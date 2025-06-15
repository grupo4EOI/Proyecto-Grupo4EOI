package com.atm.buenas_practicas_java.controllers;

import com.atm.buenas_practicas_java.dtos.AjustesPerfilDTO;
import com.atm.buenas_practicas_java.dtos.UsuarioPerfilDTO;
import com.atm.buenas_practicas_java.services.AjustesPerfilService;
import com.atm.buenas_practicas_java.services.GeneroService;
import com.atm.buenas_practicas_java.services.PerfilService;
import com.atm.buenas_practicas_java.services.facade.PerfilServiceFacade;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
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
    public String mostrarPerfil(@PathVariable Long id, Model model, HttpSession session) {
        Long idOtro = (Long) session.getAttribute("id");
        UsuarioPerfilDTO usuario = perfilServiceFacade.obtenerPerfilDTO(id, idOtro);
        model.addAttribute("perfil", usuario);
        return "perfil";
    }


    //Redirigir al perfil de usuario para el layout
    @GetMapping("/perfil")
    public String mostrarPerfilAutenticado(Authentication authentication, Model model, HttpSession session) {
        if (authentication == null || !authentication.isAuthenticated()
                || authentication.getPrincipal().equals("anonymousUser")) {
            return "redirect:/login";
        }

        String nombreUsuario = authentication.getName();
        Long idOtro = (Long) session.getAttribute("id");
        Long idUsuario = perfilServiceFacade.obtenerIdUsuarioPorNombreUsuario(nombreUsuario);
        UsuarioPerfilDTO usuario = perfilServiceFacade.obtenerPerfilDTO(idUsuario, idOtro);
        model.addAttribute("perfil", usuario);
        return "perfil";
    }


    @PostMapping("/perfil/editarBiografia")
    public String editarBiografia(Long idUsuario, String biografia) {
        perfilServiceFacade.editarBiografia(idUsuario, biografia);
        return "redirect:/perfil/" + idUsuario;
    }

    @PostMapping("/perfil/{id}/agregar-amigo")
    public String agregarAmigo(@PathVariable Long id, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }
        String nombreUsuarioActual = authentication.getName();
        Long idUsuarioActual = perfilServiceFacade.obtenerIdUsuarioPorNombreUsuario(nombreUsuarioActual);

        perfilService.agregarAmigo(idUsuarioActual, id);

        return "redirect:/perfil/" + id;
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

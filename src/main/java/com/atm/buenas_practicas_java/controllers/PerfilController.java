package com.atm.buenas_practicas_java.controllers;

import com.atm.buenas_practicas_java.dtos.composedDTOs.AjustesPerfilDTO;
import com.atm.buenas_practicas_java.dtos.composedDTOs.UsuarioPerfilDTO;
import com.atm.buenas_practicas_java.services.facade.PerfilServiceFacade;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PerfilController {
    private static final String REDIRECT_LOGIN_PATH = "redirect:/login";
    private static final String REDIRECT_PERFIL_PATH = "redirect:/perfil/";
    private final PerfilServiceFacade perfilServiceFacade;


    public PerfilController(PerfilServiceFacade perfilServiceFacade) {
        this.perfilServiceFacade = perfilServiceFacade;
    }

    // Perfil de usuario.
    @GetMapping("/perfil/{id}")
    public String mostrarPerfil(@PathVariable Long id, Model model, Authentication authentication) {
        Long idAutenticado = null;

        if (authentication != null && authentication.isAuthenticated()
                && !authentication.getPrincipal().equals("anonymousUser")) {
            String nombreUsuario = authentication.getName();
            idAutenticado = perfilServiceFacade.obtenerIdUsuarioPorNombreUsuario(nombreUsuario);
        }

        UsuarioPerfilDTO usuario = perfilServiceFacade.obtenerPerfilDTO(id, idAutenticado);
        model.addAttribute("perfil", usuario);
        return "perfil";
    }

    //Redirigir al perfil de usuario para el layout
    @GetMapping("/perfil")
    public String mostrarPerfilAutenticado(Authentication authentication, Model model, HttpSession session) {
        if (authentication == null || !authentication.isAuthenticated()
                || authentication.getPrincipal().equals("anonymousUser")) {
            return REDIRECT_LOGIN_PATH;
        }

        String nombreUsuario = authentication.getName();
        Long idUsuario = perfilServiceFacade.obtenerIdUsuarioPorNombreUsuario(nombreUsuario);
        UsuarioPerfilDTO usuario = perfilServiceFacade.obtenerPerfilDTO(idUsuario, idUsuario);
        model.addAttribute("perfil", usuario);
        return "perfil";
    }

    //Editar biografía
    @PostMapping("/perfil/editarBiografia")
    public String editarBiografia(Long idUsuario, String biografia, RedirectAttributes redirectAttributes) {
        perfilServiceFacade.editarBiografia(idUsuario, biografia);
        redirectAttributes.addFlashAttribute("guardado", true);
        return REDIRECT_PERFIL_PATH + idUsuario;
    }

    //Agregar amigo
    @PostMapping("/perfil/{id}/agregar-amigo")
    public String agregarAmigo(@PathVariable Long id, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return REDIRECT_LOGIN_PATH;
        }

        String nombreUsuarioActual = authentication.getName();
        Long idUsuarioActual = perfilServiceFacade.obtenerIdUsuarioPorNombreUsuario(nombreUsuarioActual);

        perfilServiceFacade.agregarAmigo(idUsuarioActual, id);
        return REDIRECT_PERFIL_PATH + id;
    }

    //Eliminar amigo
    @PostMapping("/perfil/{id}/eliminar-amigo")
    public String eliminarAmigo(@PathVariable Long id, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return REDIRECT_LOGIN_PATH;
        }

        String nombreUsuarioActual = authentication.getName();
        Long idUsuarioActual = perfilServiceFacade.obtenerIdUsuarioPorNombreUsuario(nombreUsuarioActual);

        perfilServiceFacade.eliminarAmigo(idUsuarioActual, id);
        return REDIRECT_PERFIL_PATH + id;
    }

    //Ajustes del perfil de usuario.
    @GetMapping("/ajustes-perfil/{id}")
    public String mostrarAjustesPerfil(@PathVariable Long id, Model model) {
        AjustesPerfilDTO ajustesPerfilDTO = perfilServiceFacade.obtenerAjustesPerfil(id);
        model.addAttribute("ajustesPerfil", ajustesPerfilDTO);
        return "ajustes-perfil";
    }

    @PostMapping("/ajustes-perfil/{id}")
    public String guardarAjustesPerfil(@PathVariable Long id, @ModelAttribute AjustesPerfilDTO ajustesPerfilDTO, RedirectAttributes redirectAttributes) {
        try {
            perfilServiceFacade.actualizarAjustesPerfil(id, ajustesPerfilDTO);
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/ajustes-perfil/" + id;
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar la imagen: " + e.getMessage());
            return "redirect:/ajustes-perfil/" + id;
        }

        return REDIRECT_PERFIL_PATH + id;
    }
}

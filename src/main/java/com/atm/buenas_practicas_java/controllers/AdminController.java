package com.atm.buenas_practicas_java.controllers;

import com.atm.buenas_practicas_java.services.facade.AdminServiceFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminServiceFacade adminService;

    public AdminController(AdminServiceFacade adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public String mostrarPanelAdmin(Model model, Principal principal) {
        String nombreUsuario = (principal != null) ? principal.getName() : null;
        model.addAttribute("panelAdmin", adminService.crearPanelAdminDTO(nombreUsuario));
        return "/admin";
    }

    /**
     * Funcionalidades de borrar reseñas, comentarios
     */
    @DeleteMapping(params = "accion=borrarResena")
    public String borrarResena(@RequestParam("idResena") Long idResena) {
        adminService.borrarResena(idResena);
        return "redirect:/admin";
    }

    @DeleteMapping(params = "accion=borrarComentarioResena")
    public String borrarComentarioResena(@RequestParam("idComentarioResena") Long idComentarioResena) {
        adminService.borrarComentarioResena(idComentarioResena);
        return "redirect:/admin";
    }

    /**
     * Funcionalidad de banear comentarios y usuarios
     */
    @PutMapping(params = "accion=banComentarioPublicacion")
    public String banComentarioPublicacion(@RequestParam("idComentarioPublicacion") Long idComentarioPublicacion) {
        adminService.banComentarioPublicacion(idComentarioPublicacion);
        return "redirect:/admin";
    }

    @PutMapping(params = "accion=banUsuario")
    public String banUsuario(@RequestParam("idUsuario") Long idUsuario) {
        adminService.banUsuario(idUsuario);
        return "redirect:/admin";
    }

    /**
     * Funcionalidad de aprobar reseñas, comentarios reseñas y publicaciones
     */
    @PutMapping(params = "accion=aprobarResena")
    public String aprobarResena(@RequestParam("idResena") Long idResena) {
        adminService.aprobarResena(idResena);
        return "redirect:/admin";
    }

    @PutMapping(params = "accion=aprobarComentarioResena")
    public String aprobarComentarioResena(@RequestParam("idComentarioResena") Long idComentarioResena) {
        adminService.aprobarComentarioResena(idComentarioResena);
        return "redirect:/admin";
    }

    @PutMapping(params = "accion=aprobarComentarioPublicacion")
    public String aprobarComentarioPublicacion(@RequestParam("idComentarioPublicacion") Long idComentarioPublicacion) {
        adminService.aprobarComentarioPublicacion(idComentarioPublicacion);
        return "redirect:/admin";
    }

}

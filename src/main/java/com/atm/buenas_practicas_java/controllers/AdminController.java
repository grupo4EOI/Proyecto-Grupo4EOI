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
    public String mostrarPanelAdmin(Model model) {
        model.addAttribute("currentPage", "resumen");
        model.addAttribute("estadisticas", adminService.crearPanelAdmin());
        return "/admin-panel/admin";
    }

    @GetMapping("/resenas")
    public String revisarResenas(Model model, Principal principal) {
        String nombreUsuario = (principal != null) ? principal.getName() : null;
        model.addAttribute("currentPage", "resenas");
        model.addAttribute("estadisticas", adminService.crearPanelAdmin());
        model.addAttribute("resenasAdmin", adminService.obtenerResenasConAbuso(nombreUsuario));
        return "/admin-panel/resenas";
    }

    @GetMapping("/comentariosresenas")
    public String revisarComentariosResenas(Model model) {
        model.addAttribute("currentPage", "comentariosresenas");
        model.addAttribute("estadisticas", adminService.crearPanelAdmin());
        model.addAttribute("comentariosResenasAdmin", adminService.obtenerComentariosResenasConAbuso());
        return "/admin-panel/comentariosresenas";
    }

    @GetMapping("/comentariospublicaciones")
    public String revisarComentariosPublicaciones(Model model) {
        model.addAttribute("currentPage", "comentariospublicaciones");
        model.addAttribute("estadisticas", adminService.crearPanelAdmin());
        model.addAttribute("comentariosPublicacionesAdmin", adminService.obtenerComentariosPublicacionesConAbuso());
        return "/admin-panel/comentariospublicaciones";
    }
    /**
     * Funcionalidades de borrar reseñas, comentarios
     */
    @DeleteMapping(value = "/resenas",params = "accion=borrarResena")
    public String borrarResena(@RequestParam("idResena") Long idResena) {
        adminService.borrarResena(idResena);
        return "redirect:/admin/resenas";
    }

    @DeleteMapping(value = "/comentariosresenas", params = "accion=borrarComentarioResena")
    public String borrarComentarioResena(@RequestParam("idComentarioResena") Long idComentarioResena) {
        adminService.borrarComentarioResena(idComentarioResena);
        return "redirect:/admin/comentariosresenas";
    }

    /**
     * Funcionalidad de banear comentarios y usuarios
     */
    @PutMapping(value = "/comentariospublicaciones", params = "accion=banComentarioPublicacion")
    public String banComentarioPublicacion(@RequestParam("idComentarioPublicacion") Long idComentarioPublicacion) {
        adminService.banComentarioPublicacion(idComentarioPublicacion);
        return "redirect:/admin/comentariospublicaciones";
    }

    @PutMapping(value =  "/ban-usuario", params = "accion=banUsuario")
    public String banUsuario(@RequestParam("idUsuario") Long idUsuario) {
        adminService.banUsuario(idUsuario);
        return "redirect:/admin";
    }

    /**
     * Funcionalidad de aprobar reseñas, comentarios reseñas y publicaciones
     */
    @PutMapping(value = "/resenas", params = "accion=aprobarResena")
    public String aprobarResena(@RequestParam("idResena") Long idResena) {
        adminService.aprobarResena(idResena);
        return "redirect:/admin/resenas";
    }

    @PutMapping(value = "/comentariosresenas", params = "accion=aprobarComentarioResena")
    public String aprobarComentarioResena(@RequestParam("idComentarioResena") Long idComentarioResena) {
        adminService.aprobarComentarioResena(idComentarioResena);
        return "redirect:/admin/comentariosresenas";
    }

    @PutMapping(value = "/comentariospublicaciones", params = "accion=aprobarComentarioPublicacion")
    public String aprobarComentarioPublicacion(@RequestParam("idComentarioPublicacion") Long idComentarioPublicacion) {
        adminService.aprobarComentarioPublicacion(idComentarioPublicacion);
        return "redirect:/admin/comentariospublicaciones";
    }
}

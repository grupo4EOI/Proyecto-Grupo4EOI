package com.atm.buenas_practicas_java.controllers;

import com.atm.buenas_practicas_java.services.facade.AdminServiceFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @DeleteMapping(params = "accion=borrarResena")
    public String borrarResena(@RequestParam("idResena") Long idResena) {
        adminService.borrarResenaPorId(idResena);
        return "redirect:/admin";
    }

    @DeleteMapping(params = "accion=borrarComentarioResena")
    public String borrarComentarioResena(@RequestParam("idComentarioResena") Long idComentarioResena) {
        return "redirect:/admin";
    }

    @DeleteMapping(params = "accion=borrarPublicacion")
    public String borrarPublicacion(@RequestParam("idComentarioPublicacion") Long idComentarioPublicacion) {
        return "redirect:/admin";
    }


}

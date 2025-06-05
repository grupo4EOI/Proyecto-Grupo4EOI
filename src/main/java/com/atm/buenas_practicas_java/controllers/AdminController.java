package com.atm.buenas_practicas_java.controllers;

import com.atm.buenas_practicas_java.services.facade.AdminServiceFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminServiceFacade adminService;

    public AdminController(AdminServiceFacade adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public String mostrarPanelAdmin(Model model) {
        model.addAttribute("panelAdmin", adminService.crearPanelAdminDTO());
        return "/admin";
    }
}

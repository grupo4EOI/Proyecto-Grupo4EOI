package com.atm.buenas_practicas_java.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PanelAdminController {


    @GetMapping("/admin")
    public String mostrarPanelAdmin() {
        return "/admin";
    }
}

package com.atm.buenas_practicas_java.controllers;

import com.atm.buenas_practicas_java.services.facade.PaginaPrincipalServiceFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping({"/", "/pagina-principal"})
public class PaginaPrincipalController {

    private final PaginaPrincipalServiceFacade paginaPrincipalService;

    public PaginaPrincipalController(PaginaPrincipalServiceFacade paginaPrincipalService) {
        this.paginaPrincipalService = paginaPrincipalService;
    }

    @GetMapping
    public String mostrarPaginaPrincipal(Model model, Principal principal) {
        String nombreUsuario = (principal != null) ? principal.getName() : null;
        model.addAttribute("paginaPrincipal", paginaPrincipalService.construirDTOPaginaPrincipal(nombreUsuario));
        return "/pagina-principal";
    }
}

package com.atm.buenas_practicas_java.controllers;

import com.atm.buenas_practicas_java.dtos.ResenaDTO;
import com.atm.buenas_practicas_java.entities.Objeto;
import com.atm.buenas_practicas_java.entities.Resena;
import com.atm.buenas_practicas_java.entities.Usuario;
import com.atm.buenas_practicas_java.mapper.FichaObjetoMapper;
import com.atm.buenas_practicas_java.services.*;
import com.atm.buenas_practicas_java.services.facade.FichaObjetoFacade;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ObjetoController {

    FichaObjetoFacade fichaObjetoFacade;

    public ObjetoController(FichaObjetoFacade fichaObjetoFacade) {
        this.fichaObjetoFacade = fichaObjetoFacade;
    }

    @GetMapping("/ficha-objeto/{id}")
    public String mostrarFichaObjeto(Model model, @PathVariable Long id) {
        model.addAttribute("fichaObjeto", fichaObjetoFacade.construirFichaObjeto(id));
        model.addAttribute("nuevaResena", new ResenaDTO(null, null, null, 0.0, false, null, List.of()));

        return "/ficha-objeto";
    }

    @PostMapping("/ficha-objeto/{id}")
    public String nuevaResena(@PathVariable Long id, @ModelAttribute("nuevaResena") ResenaDTO resena, @AuthenticationPrincipal UserDetails usuario, RedirectAttributes attrs) {
        fichaObjetoFacade.agregarResena(id, resena, usuario.getUsername());
        attrs.addFlashAttribute("mensaje", "¡Reseña publicada!");
        return String.format("redirect:/ficha-objeto/%d", id);
    }
}

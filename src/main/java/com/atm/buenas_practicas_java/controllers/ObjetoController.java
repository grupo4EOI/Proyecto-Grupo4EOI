package com.atm.buenas_practicas_java.controllers;

import com.atm.buenas_practicas_java.dtos.ComentarioResenaDTO;
import com.atm.buenas_practicas_java.dtos.ResenaCrearDTO;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ObjetoController {

    FichaObjetoFacade fichaObjetoFacade;

    public ObjetoController(FichaObjetoFacade fichaObjetoFacade) {
        this.fichaObjetoFacade = fichaObjetoFacade;
    }

    @GetMapping("/ficha-objeto/{idObjeto}")
    public String mostrarFichaObjeto(Model model, @PathVariable Long idObjeto) {
        model.addAttribute("fichaObjeto", fichaObjetoFacade.construirFichaObjeto(idObjeto));

        // Para postmapping de crear reseña
        model.addAttribute("nuevaResena", new ResenaCrearDTO("", "", 0.0, false));

        // Para postmapping de crear comentario reseña
        model.addAttribute("nuevoComentario", new ComentarioResenaDTO(null, "", null, null));

        return "/ficha-objeto";
    }

    @PostMapping(value = "/ficha-objeto/{idObjeto}", params = "accion=nuevaResena")
    public String nuevaResena(@PathVariable Long idObjeto, @ModelAttribute("nuevaResena") ResenaCrearDTO resena, Principal principal, RedirectAttributes attrs) {
        fichaObjetoFacade.agregarResena(idObjeto, resena, principal.getName());
        attrs.addFlashAttribute("mensaje", "¡Reseña publicada!");
        return String.format("redirect:/ficha-objeto/%d", idObjeto);
    }

    @PostMapping(value = "/ficha-objeto/{idObjeto}", params = "accion=comentarResena")
    public String nuevoComentarioResena(@PathVariable Long idObjeto, @RequestParam Long idResena, @ModelAttribute("nuevoComentario") ComentarioResenaDTO comentarioDTO, Principal principal) {
        fichaObjetoFacade.agregarComentarioResena(idResena, comentarioDTO, principal.getName());
        return String.format("redirect:/ficha-objeto/%d", idObjeto);
    }

    @PutMapping(value = "/ficha-objeto/{idObjeto}", params = "accion=reportarResena")
    public String reportarResena(@PathVariable Long idObjeto, @RequestParam("idResena") Long idResena) {
        fichaObjetoFacade.reportarResena(idResena);
        return String.format("redirect:/ficha-objeto/%d", idObjeto);
    }

    @PutMapping(value = "/ficha-objeto/{idObjeto}", params = "accion=reportarSpoilerResena")
    public String reportarSpoilerResena(@PathVariable Long idObjeto, @RequestParam("idResena") Long idResena) {
        fichaObjetoFacade.reportarSpoilerResena(idResena);
        return String.format("redirect:/ficha-objeto/%d", idObjeto);
    }
}

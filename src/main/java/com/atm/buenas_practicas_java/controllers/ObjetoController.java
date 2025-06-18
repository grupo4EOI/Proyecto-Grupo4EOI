package com.atm.buenas_practicas_java.controllers;

import com.atm.buenas_practicas_java.dtos.ComentarioResenaDTO;
import com.atm.buenas_practicas_java.dtos.ResenaCrearDTO;
import com.atm.buenas_practicas_java.services.facade.FichaObjetoFacade;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;


@Controller
public class ObjetoController {
    private static final String REDIRECT_OBJETO_PATH = "redirect:/ficha-objeto/%d";
    FichaObjetoFacade fichaObjetoFacade;

    public ObjetoController(FichaObjetoFacade fichaObjetoFacade) {
        this.fichaObjetoFacade = fichaObjetoFacade;
    }

    @GetMapping("/ficha-objeto/{idObjeto}")
    public String mostrarFichaObjeto(Model model, @PathVariable Long idObjeto, Principal principal, @PageableDefault(size = 5) Pageable pageable) {
        String nombreUsuario = (principal != null) ? principal.getName() : null;
        model.addAttribute("fichaObjeto", fichaObjetoFacade.construirFichaObjeto(idObjeto, nombreUsuario, pageable));

        // Para postmapping de crear reseña
        model.addAttribute("nuevaResena", new ResenaCrearDTO("", "", 0.0, false));
        // Para postmapping de crear comentario reseña
        model.addAttribute("nuevoComentario", new ComentarioResenaDTO(null, null, null, null, null));

        return "/ficha-objeto";
    }

    @PostMapping(value = "/ficha-objeto/{idObjeto}", params = "accion=nuevaResena")
    public String nuevaResena(@PathVariable Long idObjeto, @ModelAttribute("nuevaResena") ResenaCrearDTO resena, Principal principal, RedirectAttributes attrs) {
        fichaObjetoFacade.agregarResena(idObjeto, resena, principal.getName());
        attrs.addFlashAttribute("mensaje", "¡Reseña publicada!");
        return String.format(REDIRECT_OBJETO_PATH, idObjeto);
    }

    @PostMapping(value = "/ficha-objeto/{idObjeto}", params = "accion=comentarResena")
    public String nuevoComentarioResena(@PathVariable Long idObjeto, @RequestParam Long idResena, @ModelAttribute("nuevoComentario") ComentarioResenaDTO comentarioDTO, Principal principal) {
        fichaObjetoFacade.agregarComentarioResena(idResena, comentarioDTO, principal.getName());
        return String.format(REDIRECT_OBJETO_PATH, idObjeto);
    }

    @PostMapping(value = "/ficha-objeto/{idObjeto}", params = "accion=estadoObjeto")
    public String actualizarEstadoObjeto(@PathVariable Long idObjeto, @RequestParam("estado") Boolean estado, Principal principal) {
        fichaObjetoFacade.marcarEstadoObjeto(idObjeto, principal.getName(), estado);
        return String.format(REDIRECT_OBJETO_PATH, idObjeto);
    }

    @PostMapping(value = "/ficha-objeto/{idObjeto}", params = "accion=objetoFavorito")
    public String nuevoObjetoFavorito(@PathVariable Long idObjeto, @RequestParam("favorito") Boolean favorito, Principal principal) {
        fichaObjetoFacade.marcarObjetoFavorito(idObjeto, principal.getName(), favorito);
        return String.format(REDIRECT_OBJETO_PATH, idObjeto);
    }

    @PostMapping(value = "/ficha-objeto/{idObjeto}", params = "accion=likeResena")
    public String likeResena(@PathVariable Long idObjeto, @RequestParam("idResena") Long idResena, Principal principal) {
        if (principal != null) {
            fichaObjetoFacade.marcarQuitarLikeResena(idResena, principal.getName());
        }
        return String.format(REDIRECT_OBJETO_PATH, idObjeto);
    }

    @PostMapping("/ficha-objeto/like-resena/{idResena}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> toggleLikeResena(@PathVariable Long idResena, Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        boolean liked = fichaObjetoFacade.marcarQuitarLikeResena(idResena, principal.getName());
        Long likeCount = fichaObjetoFacade.obtenerNumeroLikesResena(idResena);

        Map<String, Object> response = new HashMap<>();
        response.put("liked", liked);
        response.put("likeCount", likeCount);

        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/ficha-objeto/{idObjeto}", params = "accion=reportarResena")
    public String reportarResena(@PathVariable Long idObjeto, @RequestParam("idResena") Long idResena) {
        fichaObjetoFacade.reportarResena(idResena);
        return String.format(REDIRECT_OBJETO_PATH, idObjeto);
    }

    @PutMapping(value = "/ficha-objeto/{idObjeto}", params = "accion=reportarSpoilerResena")
    public String reportarSpoilerResena(@PathVariable Long idObjeto, @RequestParam("idResena") Long idResena) {
        fichaObjetoFacade.reportarSpoilerResena(idResena);
        return String.format(REDIRECT_OBJETO_PATH, idObjeto);
    }

    @PutMapping(value = "/ficha-objeto/{idObjeto}", params = "accion=reportarComentarioResena")
    public String reportarComentarioResena(@PathVariable Long idObjeto, @RequestParam("idComentarioResena") Long idComentarioResena) {
        fichaObjetoFacade.reportarComentarioResena(idComentarioResena);
        return String.format(REDIRECT_OBJETO_PATH, idObjeto);
    }

    @PutMapping(value = "/ficha-objeto/{idObjeto}", params = "accion=reportarSpoilerComentarioResena")
    public String reportarSpoilerComentarioResena(@PathVariable Long idObjeto, @RequestParam("idComentarioResena") Long idComentarioResena) {
        fichaObjetoFacade.reportarSpoilerComentarioResena(idComentarioResena);
        return String.format(REDIRECT_OBJETO_PATH, idObjeto);
    }
}

package com.atm.buenas_practicas_java.controllers;

import com.atm.buenas_practicas_java.dtos.*;
import com.atm.buenas_practicas_java.entities.ComentarioPublicacion;
import com.atm.buenas_practicas_java.entities.Comunidad;
import com.atm.buenas_practicas_java.entities.Publicacion;
import com.atm.buenas_practicas_java.entities.Usuario;
import com.atm.buenas_practicas_java.mapper.ComunidadMapper;
import com.atm.buenas_practicas_java.services.*;
import com.atm.buenas_practicas_java.services.facade.ComunidadServiceFacade;
import com.atm.buenas_practicas_java.utils.PaginacionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/comunidades")
public class ComunidadesController {

    private final ComunidadServiceFacade comunidadServiceFacade;

    public ComunidadesController(ComunidadServiceFacade comunidadServiceFacade) {
        this.comunidadServiceFacade = comunidadServiceFacade;
    }

    @GetMapping
    public String mostrarComunidades(Model model) {
        List<ComunidadDTO> comunidades = comunidadServiceFacade.buscarComunidades();
        model.addAttribute("comunidades", comunidades);
        return "comunidades";
    }

    @GetMapping("/{id}/temas")
    public String mostrarTemas(Model model, @PathVariable Long id, @PageableDefault(size = 20) Pageable pageable) {
        ComunidadSimpleDTO comunidad = comunidadServiceFacade.findById(id);
        Page<PublicacionDTO> publicaciones = PaginacionUtils.listToPage(comunidadServiceFacade.buscarPublicacionesPorComunidad(id), pageable);
        model.addAttribute("comunidad", comunidad);
        model.addAttribute("publicaciones", publicaciones);
        return "comunidad";
    }

    @GetMapping("/{idcom}/temas/{id}")
    public String mostrarComentarios(Model model, @PathVariable Long idcom, @PathVariable Long id, @PageableDefault(size = 20) Pageable pageable) {
        ComunidadSimpleDTO comunidad = comunidadServiceFacade.findById(idcom);
        Page<ComentarioPublicacionSimpleDTO> comentarios = PaginacionUtils.listToPage(comunidadServiceFacade.buscarComentariosPorPublicacion(id), pageable);
        model.addAttribute("comunidad", comunidad);
        model.addAttribute("comentarios", comentarios);

        return "ejemplo-tema";
    }

    @GetMapping("/{id}/temas/nuevo-tema")
    public String mostrarNuevoTema(Model model, @PathVariable Long id) {
        ComunidadSimpleDTO comunidad = comunidadServiceFacade.findById(id);
        model.addAttribute("nuevoTema", new PublicacionCrearDTO(null, "", ""));
        model.addAttribute("comunidad", comunidad);
        return "nuevo-tema";
    }

    @PostMapping("/{id}/temas")
    public String crearNuevoTema(@PathVariable Long id, @ModelAttribute("nuevoTema") PublicacionCrearDTO publicacion, Principal principal) {
        PublicacionCrearDTO publicacionDTO = comunidadServiceFacade.nuevaPublicacion(id, publicacion, principal.getName());
        return String.format("redirect:/comunidades/%d/temas/%d", id, publicacionDTO.idPublicacion());
    }

    @PostMapping(value = "/{idcom}/temas/{id}", params = "accion=nuevoComentario")
    public String crearComentario(@PathVariable Long idcom,
                                  @PathVariable Long id,
                                  @ModelAttribute("comentario") ComentarioPublicacionCrearDTO comentario,
                                  Principal principal) {
        ComentarioPublicacionCrearDTO comentarioDTO = comunidadServiceFacade.nuevoComentario(idcom,comentario, principal.getName(), id);
        return String.format("redirect:/comunidades/%d/temas/%d", idcom, comentarioDTO.publicacion().idPublicacion());
    }

    @PutMapping(value = "/{idcom}/temas/{id}", params = "accion=reportarComentario")
    public String reportarComentario(@PathVariable Long idcom,
                                     @PathVariable Long id,
                                     @RequestParam("idComentarioPublicacion") Long idComentarioPublicacion){
        comunidadServiceFacade.reportarComentarioPublicacion(idComentarioPublicacion);
        return String.format("redirect:/comunidades/%d/temas/%d", idcom, id);
    }


}

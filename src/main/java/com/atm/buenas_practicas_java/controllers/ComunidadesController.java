package com.atm.buenas_practicas_java.controllers;

import com.atm.buenas_practicas_java.dtos.ComentarioPublicacionSimpleDTO;
import com.atm.buenas_practicas_java.dtos.ComunidadDTO;
import com.atm.buenas_practicas_java.dtos.ComunidadSimpleDTO;
import com.atm.buenas_practicas_java.dtos.PublicacionDTO;
import com.atm.buenas_practicas_java.entities.ComentarioPublicacion;
import com.atm.buenas_practicas_java.entities.Comunidad;
import com.atm.buenas_practicas_java.entities.Publicacion;
import com.atm.buenas_practicas_java.entities.Usuario;
import com.atm.buenas_practicas_java.mapper.ComunidadMapper;
import com.atm.buenas_practicas_java.services.*;
import com.atm.buenas_practicas_java.services.facade.ComunidadServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
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

//    public ComunidadesController(ComunidadServiceFacade comunidadServiceFacade) {
//        this.comunidadServiceFacade = comunidadServiceFacade;
//    }

    private ComunidadService comunidadService;
    private PublicacionService publicacionService;
    private ComentarioPublicacionService comPubService;
    private UsuarioService usuarioService;
    private ComunidadMapper comunidadMapper;

    public ComunidadesController(ComunidadServiceFacade comunidadServiceFacade, ComunidadService comunidadService, PublicacionService publicacionService, ComentarioPublicacionService comPubService, UsuarioService usuarioService, ComunidadMapper comunidadMapper) {
        this.comunidadServiceFacade = comunidadServiceFacade;
        this.comunidadService = comunidadService;
        this.publicacionService = publicacionService;
        this.comPubService = comPubService;
        this.usuarioService = usuarioService;
        this.comunidadMapper = comunidadMapper;
    }

    @GetMapping
    public String mostrarComunidades(Model model) {
        List<ComunidadDTO> comunidades = comunidadServiceFacade.buscarComunidades();
        model.addAttribute("comunidades", comunidades);
        return "comunidades";
    }

    @GetMapping("/{id}/temas")
    public String mostrarTemas(Model model, @PathVariable Long id) {
        ComunidadSimpleDTO comunidad = comunidadServiceFacade.findByID(id);
        List<PublicacionDTO> publicaciones = comunidadServiceFacade.buscarPublicacionesPorComunidad(id);
        model.addAttribute("comunidad", comunidad);
        model.addAttribute("publicaciones", publicaciones);
        return "comunidad";
    }

    @GetMapping("/{idcom}/temas/{id}")
    public String mostrarComentarios(Model model, @PathVariable Long idcom, @PathVariable Long id) {
        ComunidadSimpleDTO comunidad = comunidadServiceFacade.findByID(idcom);
        List<ComentarioPublicacionSimpleDTO> comentarios = comunidadServiceFacade.buscarComentariosPorPublicacion(id);
        model.addAttribute("comunidad", comunidad);
        model.addAttribute("comentarios", comentarios);
        return "ejemplo-tema";
    }

    @GetMapping("/{id}/temas/nuevo-tema")
    public String mostrarNuevoTema(Model model, @PathVariable Long id) {
        ComunidadSimpleDTO comunidad = comunidadServiceFacade.findByID(id);
        model.addAttribute("comunidad", comunidad);
        return "nuevo-tema";
    }

    @PostMapping("/{id}/temas")
    public String crearNuevoTema(@PathVariable Long id,
                                 @RequestParam String titulo,
                                 @RequestParam String contenido,
                                 Principal principal) {

        Comunidad comunidad = comunidadService.findById(id);
        if (comunidad == null) {
            return "redirect:/error";
        }

        Publicacion publicacion = new Publicacion();
        publicacion.setTitulo(titulo);
        publicacion.setComunidad(comunidad);


        Usuario usuario = usuarioService.findByNombreUsuario(principal.getName());

        ComentarioPublicacion comentario = new ComentarioPublicacion();
        comentario.setContenido(contenido);
        comentario.setFecha(LocalDateTime.now());
        comentario.setPublicacion(publicacion);
        comentario.setUsuario(usuario);

        publicacion.setComentariosPublicacion(Arrays.asList(comentario));

        publicacionService.save(publicacion);

        comPubService.save(comentario);



        return "redirect:/comunidades/" + id + "/temas/" + publicacion.getIdPublicacion();
    }

    @PostMapping("/{idcom}/temas/{id}")
    public String crearComentario(@PathVariable Long idcom,
                                  @PathVariable Long id,
                                  @RequestParam String contenido,
                                  Principal principal) {

        Comunidad comunidad = comunidadService.findById(idcom);
        if (comunidad == null) {
            return "redirect:/error";
        }

        Publicacion publicacion = publicacionService.findById(id);
        if (publicacion == null) {
            return "redirect:/error";
        }

        Usuario usuario = usuarioService.findByNombreUsuario(principal.getName());

        ComentarioPublicacion comentario = new ComentarioPublicacion();
        comentario.setContenido(contenido);
        comentario.setFecha(LocalDateTime.now());
        comentario.setPublicacion(publicacion);
        comentario.setUsuario(usuario);

        comPubService.save(comentario);

        return "redirect:/comunidades/" + idcom + "/temas/" + id;
    }

}

package com.atm.buenas_practicas_java.controllers;

import com.atm.buenas_practicas_java.entities.ComentarioPublicacion;
import com.atm.buenas_practicas_java.entities.Comunidad;
import com.atm.buenas_practicas_java.entities.Publicacion;
import com.atm.buenas_practicas_java.entities.Usuario;
import com.atm.buenas_practicas_java.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/comunidades")
public class ComunidadesController {

    private ComunidadService comunidadService;
    private PublicacionService publicacionService;
    private ComentarioPublicacionService comPubService;
    private UsuarioService usuarioService;

    public ComunidadesController(ComunidadService comunidadService, PublicacionService publicacionService, ComentarioPublicacionService comPubService, UsuarioService usuarioService) {
        this.comunidadService = comunidadService;
        this.publicacionService = publicacionService;
        this.comPubService = comPubService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String mostrarComunidades(Model model) {
        List<Comunidad> comunidades = comunidadService.findAll();
        model.addAttribute("comunidades", comunidades);
        return "comunidades";
    }

    @GetMapping("/{id}/temas")
    public String mostrarTemas(Model model, @PathVariable Long id) {
        List<Publicacion> publicaciones = publicacionService.getPublicacionsByComunidad(comunidadService.findById(id));
        Comunidad comunidad = comunidadService.findById(id);
        model.addAttribute("publicaciones", publicaciones);
        model.addAttribute("comunidad", comunidad);
        return "comunidad";
    }

    @GetMapping("/{idcom}/temas/{id}")
    public String mostrarComentarios(Model model, @PathVariable Long idcom, @PathVariable Long id) {
        Comunidad com = comunidadService.findById(idcom);
        List<ComentarioPublicacion> comentarios = comPubService.getComentarioPublicacionByPublicacionId(id);
        model.addAttribute("comunidad", com);
        model.addAttribute("comentarios", comentarios);
        return "ejemplo-tema";
    }

    @GetMapping("/{id}/temas/nuevo-tema")
    public String mostrarNuevoTema(Model model, @PathVariable Long id) {
        Comunidad comunidad = comunidadService.findById(id);
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
        publicacion = publicacionService.save(publicacion);

        Usuario usuario = usuarioService.findByNombreUsuario(principal.getName());

        ComentarioPublicacion comentario = new ComentarioPublicacion();
        comentario.setContenido(contenido);
        comentario.setFecha(LocalDateTime.now());
        comentario.setPublicacion(publicacion);
        comentario.setUsuario(usuario);

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

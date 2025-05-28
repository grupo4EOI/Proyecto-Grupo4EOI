package com.atm.buenas_practicas_java.controllers;

import com.atm.buenas_practicas_java.entities.Usuario;
import com.atm.buenas_practicas_java.services.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/perfil")
public class PerfilController {
 private final UsuarioService usuarioService;

    public PerfilController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @GetMapping("/perfil/{id}")
    public String mostrarPerfil(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.obtenerUsuario(id);
        //Añadiré más géneros
        List<String> generosPeliculas = List.of("Acción", "Comedia");
        List<String> generosSeries = List.of("Drama", "Ciencia ficción");
        List<String> generosVideojuegos = List.of("RPG", "Aventura");

        int contadorResenas = 0;
        int contadorInsignias = 0;
        int contadorFavoritos = 0;
        int contadorPendiente = 0;

        List<String> resenas = List.of();
        List<String> comentarios = List.of();
        List<String> meGusta = List.of();
        List<Usuario> amigos = List.of();
        List<String> insignias = List.of();

        model.addAttribute("usuario", usuario);
        model.addAttribute("generosPeliculas", generosPeliculas);
        model.addAttribute("generosSeries", generosSeries);
        model.addAttribute("generosVideojuegos", generosVideojuegos);
        model.addAttribute("contadorResenas", contadorResenas);
        model.addAttribute("contadorInsignias", contadorInsignias);
        model.addAttribute("contadorFavoritos", contadorFavoritos);
        model.addAttribute("contadorPendiente", contadorPendiente);
        model.addAttribute("resenas", resenas);
        model.addAttribute("comentarios", comentarios);
        model.addAttribute("meGusta", meGusta);
        model.addAttribute("amigos", amigos);
        model.addAttribute("insignias", insignias);

        return "perfil";
    }
}



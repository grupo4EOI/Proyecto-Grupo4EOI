package com.atm.buenas_practicas_java.controllers;

import com.atm.buenas_practicas_java.entities.Genero;
import com.atm.buenas_practicas_java.entities.Resena;
import com.atm.buenas_practicas_java.entities.Usuario;
import com.atm.buenas_practicas_java.repositories.GeneroRepository;
import com.atm.buenas_practicas_java.services.GeneroService;
import com.atm.buenas_practicas_java.services.PerfilService;
import com.atm.buenas_practicas_java.services.facade.FichaObjetoFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
public class PerfilController {
    private final GeneroRepository generoRepository;
    private final GeneroService generoService;
    PerfilService perfilService;

    public PerfilController(PerfilService perfilService, GeneroRepository generoRepository, GeneroService generoService) {
        this.perfilService = perfilService;
        this.generoRepository = generoRepository;
        this.generoService = generoService;
    }

    @GetMapping("/perfil/{id}")
    public String mostrarPerfil(@PathVariable Long id, Model model) {
        Usuario usuario = perfilService.findByIdUsuario(id);

        Set<Genero> todosGeneros = usuario.getGeneros();

        List<Genero> generosPeliculas = todosGeneros.stream()
                .filter(g -> g.getTipo().getNombre().equalsIgnoreCase("pelicula"))
                .toList();

        List<Genero> generosSeries = todosGeneros.stream()
                .filter(g -> g.getTipo().getNombre().equalsIgnoreCase("serie"))
                .toList();

        List<Genero> generosVideojuegos = todosGeneros.stream()
                .filter(g -> g.getTipo().getNombre().equalsIgnoreCase("videojuego"))
                .toList();


        model.addAttribute("Usuario", usuario);
        model.addAttribute("generosPeliculas", generosPeliculas);
        model.addAttribute("generosSeries", generosSeries);
        model.addAttribute("generosVideojuegos", generosVideojuegos);


        return "perfil";
    }

    @PostMapping("/perfil/editarBiografia")
    public String editarBiografia(@ModelAttribute Usuario usuarioActualizado) {
        perfilService.editarBiografia(usuarioActualizado.getIdUsuario(), usuarioActualizado.getBiografia());
        return "redirect:/perfil/" + usuarioActualizado.getIdUsuario();
    }
}
package com.atm.buenas_practicas_java.controllers;


import com.atm.buenas_practicas_java.services.EntidadHijaService;
import com.atm.buenas_practicas_java.services.EntidadPadreService;
import com.atm.buenas_practicas_java.services.ObjetoService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.security.Principal;
import java.util.Collection;

/**
 * Controlador encargado de manejar las solicitudes relacionadas con la entidad principal.
 *
 * Este controlador utiliza la anotación {@code @Controller} para ser detectado como un componente
 * Spring MVC y maneja solicitudes HTTP. Su objetivo principal es gestionar las operaciones
 * necesarias para mostrar una lista de entidades en la vista correspondiente.
 *
 * Anotaciones importantes:
 * - {@code @Controller}: Indica que esta clase se comporta como un controlador Spring MVC.
 * - {@code @PreAuthorize}: Define que el acceso a ciertos métodos esté restringido
 *   según las reglas de autorización establecidas.
 *
 * Dependencias:
 * - {@code EntidadPadreRepository}: Interfaz del repositorio que permite interactuar con
 *   la base de datos para operaciones de persistencia y consulta relacionadas con
 *   la entidad padre.
 *
 * Métodos principales:
 * - {@code listEntities}: Maneja solicitudes GET a la URL "/entities", recupera los
 *   datos de las entidades desde la base de datos y los pasa al modelo para mostrarlos
 *   en una vista.
 *
 */
@Controller
public class DefaultController {

    private final EntidadHijaService entidadHijaService;
    private final EntidadPadreService entidadPadreService;
    private final ObjetoService objetoService;

    /**
     * Constructor de la clase DefaultController.
     * <p>
     * Inicializa el controlador principal asignando los servicios
     * utilizados para gestionar las entidades EntidadPadre y EntidadHija.
     *
     * @param entidadHijaService  instancia de {@link EntidadHijaService} que proporciona
     *                            funcionalidades adicionales relacionadas con la entidad EntidadHija.
     * @param entidadPadreService instancia de {@link EntidadPadreService} que proporciona
     *                            funcionalidades adicionales relacionadas con la entidad EntidadPadre.
     */
    public DefaultController(EntidadHijaService entidadHijaService, EntidadPadreService entidadPadreService, ObjetoService objetoService) {
        this.entidadHijaService = entidadHijaService;
        this.entidadPadreService = entidadPadreService;
        this.objetoService = objetoService;
    }

    /**
     * Método que lista las entidades disponibles y las añade al modelo para ser utilizadas en la vista.
     * Recupera todas las entidades de un repositorio y las presenta en una vista específica.
     *
     * @param model El objeto del modelo que se utiliza para compartir datos entre el backend y la vista.
     *              Aquí se añade un atributo llamado "entities" con la lista obtenida del repositorio.
     * @return Una cadena que representa el nombre de la vista ("entitiesList") donde se renderizarán las entidades.
     */
    @GetMapping("/entities")
    public String listEntities(Model model)
    {
        model.addAttribute("entidades", entidadHijaService.findAll());
        return "entidadesHijas"; // View name
    }

    /**
     * Gestiona las solicitudes GET para obtener y mostrar la lista de entidades protegidas.
     * Añade las entidades obtenidas del repositorio al modelo para renderizarlas en la vista correspondiente.
     *
     * @param model Objeto {@link Model} que se utiliza para pasar datos desde el controlador a la vista.
     *              Contendrá la lista de entidades recuperadas desde el repositorio.
     * @return El nombre de la vista "entitiesList" donde se mostrará la lista de entidades.
     */
    @GetMapping("/protected")
    public String protectedList(Model model)
    {
        model.addAttribute("entidades", entidadPadreService.findAll());
        return "entidadesPadre"; // View name
    }

    /**
     * Deletes an EntidadHija entity by its ID using the EntidadHijaService.
     *
     * @param id The ID of the EntidadHija to delete.
     * @return A redirect to the "/protected" endpoint after deletion.
     */
    @PostMapping("/entidades/deleteHija/{id}")
    public String deleteEntidadHija(@PathVariable Long id) {
        entidadHijaService.deleteById(id);
        return "redirect:/entities";
    }



    /**
     * Deletes an EntidadHija entity by its ID using the EntidadHijaService.
     *
     * @param id The ID of the EntidadHija to delete.
     * @return A redirect to the "/protected" endpoint after deletion.
     */
    @PostMapping("/entidades/deletePadre/{id}")
    public String deleteEntidadPadre(@PathVariable Long id) {
        entidadPadreService.deleteById(id);
        return "redirect:/entities";
    }

    /* Aqui empiezan nuestros métodos */
    @GetMapping("/ajustes-perfil")
    public String mostrarAjustesPerfil() {
        return "/ajustes-perfil";
    }

    /** TODO: Añadir path variable cuando se hagan los serviicios y controladores */
    @GetMapping("/comunidad-harry-potter")
    public String mostrarComunidad() {
        return "comunidad";
    }

    @GetMapping("/contacto")
    public String mostrarContacto() {
        return "/contacto";
    }

    /** TODO: Cambiar el path y este mapping cuando se hagan servicios y controladores */
    @GetMapping("/ejemplo-tema")
    public String mostrarTema() {
        return "/ejemplo-tema";
    }

    /** TODO: Cambiar el mapping a un PostMapping cuando se hagan servicios y controladores */
    @GetMapping("/nuevo-tema")
    public String mostrarNuevoTema() {
        return "/nuevo-tema";
    }

    /** TODO: Cambiar mapping cuando se hagan servicios y controladores */
//    @GetMapping("/perfil")
//    public String mostrarPerfil() {
//        return "/perfil";
//    }

    @GetMapping("/politica-privacidad")
    public String mostrarPoliticaPrivacidad() {
        return "/politica-privacidad";
    }

    @GetMapping("/quienes-somos")
    public String mostrarQuienesSomos() {
        return "/quienes-somos";
    }

    @GetMapping("/terminos-y-condiciones")
    public String mostrarTerminosYCondiciones() {
        return "/terminos-y-condiciones";
    }


//    @GetMapping("/iniciar-sesion")
//    public String mostrarIniciarSesion() {
//        return "/iniciar-sesion";
//    }

//    @GetMapping("/registro")
//    public String mostrarRegistro() {
//        return "registro";
//    }

}

package com.atm.buenas_practicas_java.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


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
}

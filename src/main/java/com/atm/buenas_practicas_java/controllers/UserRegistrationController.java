package com.atm.buenas_practicas_java.controllers;

import com.atm.buenas_practicas_java.entities.UserForm;
import com.atm.buenas_practicas_java.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/registro")
@RequiredArgsConstructor
public class UserRegistrationController {

    private final UsuarioService usuarioService;

    @GetMapping
    public String mostrarRegistro(Model model) {
        if (!model.containsAttribute("userForm")) {
            model.addAttribute("userForm", new UserForm());
        }
        return "registro";
    }

//    @PostMapping
//    public String doRegister(@Valid UserForm userForm, BindingResult result) {
//        if (result.hasErrors()) {
//            return "registro";
//        }
//
//        try {
//            usuarioService.registerUser(userForm);
//            return "redirect:/iniciar-sesion?registroExitoso";
//        } catch(DataIntegrityViolationException e) {
//            result.rejectValue("nombreUsuario", "duplicate", "El nombre de usuario ya existe");
//            return "registro";
//        }
//    }

    @PostMapping
    public String doRegister(@Valid @ModelAttribute("userForm") UserForm userForm,
                             BindingResult result,
                             RedirectAttributes redirectAttributes,
                             Model model) {

        // Validación básica del formulario
        if (result.hasErrors()) {
            model.addAttribute("userForm", userForm);
            return "registro";
        }

        // Validación de nombre de usuario existente
        if (usuarioService.existeNombreUsuario(userForm.getNombreUsuario())) {
            result.rejectValue("nombreUsuario", "duplicate", "El nombre de usuario ya está en uso");
            model.addAttribute("userForm", userForm);
            return "registro";
        }

        // Validación de email existente
        if (usuarioService.existeEmail(userForm.getEmail())) {
            result.rejectValue("email", "duplicate", "Ya existe un usuario registrado con este email");
            model.addAttribute("userForm", userForm);
            return "registro";
        }

        try {
            // Asignar rol USER por defecto si no se especifica
            if (userForm.getRole() == null) {
                userForm.setRole("USER");
            }

            usuarioService.registerUser(userForm);
            // Configurar mensaje de éxito
            redirectAttributes.addFlashAttribute("registroExitoso", true);
            redirectAttributes.addFlashAttribute("mensajeExito", "¡Registro exitoso! Por favor inicia sesión");
            return "redirect:/iniciar-sesion";
        } catch (DataIntegrityViolationException e) {
            result.reject("registrationError", "Error al registrar el usuario. Por favor intente nuevamente");
            model.addAttribute("userForm", userForm);
            return "registro";
        } catch (IllegalArgumentException e) {
            result.reject("roleError", "Rol de usuario no válido");
            model.addAttribute("userForm", userForm);
            return "registro";
        }
    }
}


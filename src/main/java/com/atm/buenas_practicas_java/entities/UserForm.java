package com.atm.buenas_practicas_java.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserForm {
    @Email
    private String email;
    @NotBlank
    private String nombreUsuario;
    @NotBlank
    private String contrasena;

    public Usuario toUser(UserForm userForm) {
        Usuario user = new Usuario();
        user.setEmail(userForm.getEmail());
        user.setNombreUsuario(userForm.getNombreUsuario());
        user.setContrasena(userForm.getContrasena());
        user.setRole("USER");
        return user;
    }
}

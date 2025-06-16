package com.atm.buenas_practicas_java.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserForm {
    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String nombreUsuario;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe ser un email válido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 5, max = 12, message = "La contraseña debe tener entre 5 y 12 caracteres")
    private String contrasena;

    private String role = "USER";

    public Usuario toUserWithPassword(PasswordEncoder passwordEncoder) {
        return Usuario.builder()
                .email(this.email)
                .nombreUsuario(this.nombreUsuario)
                .contrasena(passwordEncoder.encode(this.contrasena))
                .role("USER")
                .build();
    }
}

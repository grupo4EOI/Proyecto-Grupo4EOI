package com.atm.buenas_practicas_java.entities;

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
    @Email
    private String email;
    @NotBlank
    private String nombreUsuario;
    @NotBlank
    private String contrasena;

    public Usuario toUserWithPassword(PasswordEncoder passwordEncoder) { // Eliminar parámetro
        return Usuario.builder()
                .email(this.email)
                .nombreUsuario(this.nombreUsuario)
                .contrasena(passwordEncoder.encode(this.contrasena))
                .role("USER")
                .build();
    }
}

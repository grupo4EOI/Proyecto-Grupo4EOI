package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.entities.Usuario;
import com.atm.buenas_practicas_java.entities.VerificationToken;
import com.atm.buenas_practicas_java.repositories.UsuarioRepository;
import com.atm.buenas_practicas_java.repositories.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository UsuarioRepository;
    private final VerificationTokenRepository tokenRepository;
    private final EmailService emailService;

    public void registerUser(String email, String username, String password) {
        if (UsuarioRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("El correo ya está registrado.");
        }

        Usuario nuevo = Usuario.builder()
                .email(email)
                .nombreUsuario(username)
                .contrasena(new BCryptPasswordEncoder().encode(password))
                .verificado(false)
                .fechaRegistro(LocalDateTime.now())
                .role("USER")
                .build();

        UsuarioRepository.save(nuevo);

        // Crear token de verificación
        String token = UUID.randomUUID().toString();
        VerificationToken vt = VerificationToken.builder()
                .token(token)
                .usuario(nuevo)
                .expiryDate(LocalDateTime.now().plusHours(24))
                .build();

        tokenRepository.save(vt);

        // Enviar email
        emailService.sendVerificationEmail(email, token);
    }

    public String verifyUser(String token) {
        VerificationToken vt = tokenRepository.findByToken(token);

        if (vt == null || vt.getExpiryDate().isBefore(LocalDateTime.now())) {
            return "Token inválido o expirado";
        }

        Usuario usuario = vt.getUsuario();
        usuario.setVerificado(true);
        UsuarioRepository.save(usuario);
        tokenRepository.delete(vt);

        return "Cuenta verificada correctamente";
    }
}

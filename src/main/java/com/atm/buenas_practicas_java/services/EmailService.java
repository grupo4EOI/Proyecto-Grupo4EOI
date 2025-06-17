package com.atm.buenas_practicas_java.services;


import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final String fromEmail;
    private final JavaMailSender mailSender;

    public EmailService(
            @Value("${spring.mail.username}") String fromEmail,
            JavaMailSender mailSender) {
        this.fromEmail = fromEmail;
        this.mailSender = mailSender;
    }

    public void enviarEmailContacto(String nombre, String emailUsuario, String asunto, String mensaje) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            // Configuración del correo
            helper.setFrom(new InternetAddress(fromEmail, "Sistema de Contacto"));
            helper.setTo(fromEmail);  // Enviar a la misma cuenta (grupo4eoi2025@gmail.com)
            helper.setReplyTo(new InternetAddress(emailUsuario));  // Permite responder al usuario original
            helper.setSubject("Nuevo mensaje de contacto: " + asunto);

            // Cuerpo del mensaje (HTML)
            String contenido = "<html><body>"
                    + "<h3>Nuevo mensaje de contacto</h3>"
                    + "<p><strong>De:</strong> " + nombre + " (" + emailUsuario + ")</p>"
                    + "<p><strong>Asunto:</strong> " + asunto + "</p>"
                    + "<p><strong>Mensaje:</strong></p>"
                    + "<pre>" + mensaje + "</pre>"  // <pre> conserva saltos de línea
                    + "</body></html>";

            helper.setText(contenido, true);  // 'true' indica contenido HTML

            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("Error al enviar el correo: " + e.getMessage(), e);
        }
    }
}

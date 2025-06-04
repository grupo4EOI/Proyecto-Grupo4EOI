package com.atm.buenas_practicas_java.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Clase de configuración de seguridad para la aplicación.
 * Utiliza las anotaciones de Spring Security para definir y personalizar
 * la seguridad de la aplicación, incluyendo la configuración de solicitudes
 * HTTP, autenticación y control de acceso.
 *
 * @Configuration Marca esta clase como una clase de configuración.
 * @EnableWebSecurity Habilita la seguridad web de Spring Security en la aplicación.
 *
 * @Author No se especificó autor.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private final Environment environment;

    public SecurityConfig(Environment environment) {
        this.environment = environment;
    }


    /**
     * Método que configura un {@link UserDetailsService} para la autenticación en memoria.
     * Este método crea un usuario en memoria utilizando un nombre de usuario y contraseña
     * definidos en las propiedades de configuración `spring.security.user.name` y
     * `spring.security.user.password`, o valores predeterminados si no están configurados.
     *
     * <p>Nota: Se utiliza el identificador `{noop}` en la contraseña para evitar el uso
     * de un encoder, ideal solo para pruebas. No debe ser utilizado en un entorno de producción.</p>
     *
     * @return Un {@link InMemoryUserDetailsManager} que contiene los detalles del usuario configurado.
     *
     * @Author No se especificó autor.
     */
//    @Bean
//    public UserDetailsService userDetailsService() {
//        String name = environment.getProperty("spring.security.user.name", "user");
//        String password = environment.getProperty("spring.security.user.password", "password");
//
//        var user = User.withUsername(name)
//                .password("{noop}" + password) // {noop} indica que no se usa encoder para simplificar (solo pruebas)
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(Customizer.withDefaults())
                .formLogin(formLogin -> formLogin
                        .loginPage("/iniciar-sesion")
                        .loginProcessingUrl("/procesar-login")
                        .defaultSuccessUrl("/pagina-principal")
                        .failureUrl("/iniciar-sesion?error")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/pagina-principal")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/",
                                "/iniciar-sesion",
                                "/registro",
                                "/pagina-principal",
                                "/seccion/**",
                                "/comunidades/**",
                                "/static/**",
                                "/css/**",
                                "/images/**",
                                "/js/**"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/registro").permitAll()
                        .requestMatchers(HttpMethod.POST, "/iniciar-sesion").permitAll()
                        .requestMatchers(HttpMethod.GET, "/iniciar-sesion").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                );


        return http.build();
    }

    /**
     * Configura y proporciona un bean de tipo {@link AuthenticationManager}.
     * Este método utiliza {@link AuthenticationConfiguration} para recuperar
     * y devolver una instancia del manejador de autenticación.
     *
     * <p>El objeto {@link AuthenticationManager} es clave para gestionar los flujos
     * de autenticación en la aplicación.</p>
     *
     * @param authConfig Objeto {@link AuthenticationConfiguration} utilizado para crear
     *                   el manejador de autenticación.
     *
     * @return Una instancia de {@link AuthenticationManager} configurada a partir del
     *         {@link AuthenticationConfiguration} proporcionado.
     *
     * @throws Exception Si ocurre algún error durante la creación del manejador de autenticación.
     *
     * @Author No se especificó autor.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}

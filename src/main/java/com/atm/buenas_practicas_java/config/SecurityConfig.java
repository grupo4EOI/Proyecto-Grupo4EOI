package com.atm.buenas_practicas_java.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.filter.HiddenHttpMethodFilter;


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
    private static final String PATH_INICIAR_SESION = "/iniciar-sesion";
    private static final String PATH_PAGINA_PRINCIPAL = "/pagina-principal";

    public SecurityConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .formLogin(formLogin -> formLogin
                        .loginPage(PATH_INICIAR_SESION)
                        .loginProcessingUrl("/procesar-login")
                        .defaultSuccessUrl(PATH_PAGINA_PRINCIPAL)
                        .failureUrl(PATH_INICIAR_SESION + "?error")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl(PATH_PAGINA_PRINCIPAL)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/",
                                PATH_INICIAR_SESION,
                                "/registro",
                                "/perfil/**",
                                "/ajustes-perfil/**",
                                PATH_PAGINA_PRINCIPAL,
                                "/seccion/**",
                                "/comunidades/**",
                                "/ficha-objeto/**",
                                "/buscador-resultado/**",
                                "/contacto/**",
                                "/static/**",
                                "/css/**",
                                "/images/**",
                                "/js/**"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/registro").permitAll()
                        .requestMatchers(HttpMethod.POST, PATH_INICIAR_SESION).permitAll()
                        .requestMatchers(HttpMethod.GET, PATH_INICIAR_SESION).permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Solo ADMIN puede acceder a /admin
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

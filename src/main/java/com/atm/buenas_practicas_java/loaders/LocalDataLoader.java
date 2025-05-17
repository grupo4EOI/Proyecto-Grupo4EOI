package com.atm.buenas_practicas_java.loaders;

import com.atm.buenas_practicas_java.entities.*;
import com.atm.buenas_practicas_java.repositories.*;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.*;


/**
 * Clase de configuración que se utiliza exclusivamente para el perfil "default" en entornos locales.
 *
 * Su propósito principal es cargar datos de ejemplo en las bases de datos asociadas a las entidades
 * {@code EntidadPadre} y {@code EntidadHija}, permitiendo la inicialización de datos útiles para pruebas
 * y desarrollo en este perfil específico.
 *
 * Esta clase está anotada con:
 * - {@code @Configuration}: Define esta clase como fuente de beans y configuración.
 * - {@code @Log4j2}: Habilita el uso de la biblioteca Log4j2 para registro de mensajes en los logs.
 * - {@code @Profile("default")}: Asegura que esta clase solo se cargue en el perfil "default".
 *
 * @see EntidadPadreRepository
 * @see EntidadHijaRepository
 */
@Configuration
@Log4j2
@Profile("local")
public class LocalDataLoader {

    private final EntidadPadreRepository repository;
    private final EntidadHijaRepository entidadHijaRepository;
    private final ObjetoRepository objetoRepository;
    private final PersonaRepository personaRepository;
    private final PersonaObjetoRepository personaObjetoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ResenaRepository resenaRepository;

    /**
     * Constructor de la clase {@code LocalDataLoader}.
     *
     * Inicializa un objeto {@code LocalDataLoader} configurado con los repositorios de las entidades,
     * proporcionando la capacidad de interactuar con estas entidades en la base de datos.
     *
     * @param repository              El repositorio de la entidad padre {@code EntidadPadreRepository}.
     *                                Se utiliza para realizar operaciones de persistencia, actualización,
     *                                eliminación y consulta relacionadas con la entidad padre.
     * @param entidadHijaRepository   El repositorio de la entidad hija {@code EntidadHijaRepository}.
     *                                Es utilizado para gestionar datos de la entidad hija y su relación con
     *                                la entidad padre.
     */
    public LocalDataLoader(EntidadPadreRepository repository, EntidadHijaRepository entidadHijaRepository, ObjetoRepository objetoRepository, PersonaRepository personaRepository, PersonaObjetoRepository personaObjetoRepository, UsuarioRepository usuarioRepository, ResenaRepository resenaRepository) {
        this.repository = repository;
        this.entidadHijaRepository = entidadHijaRepository;
        this.objetoRepository = objetoRepository;
        this.personaRepository = personaRepository;
        this.personaObjetoRepository = personaObjetoRepository;
        this.usuarioRepository = usuarioRepository;
        this.resenaRepository = resenaRepository;
    }

    /**
     * Método anotado con {@code @PostConstruct} que inicializa datos de prueba en
     * los repositorios para entornos locales. Este método se ejecuta automáticamente
     * después de la inicialización del bean y antes de que esté disponible para uso,
     * permitiendo cargar datos iniciales necesarios para el perfil local.
     *
     * Funcionalidad:
     * - Crea 10 instancias de la entidad `EntidadPadre` con nombres predefinidos.
     * - Guarda las instancias de `EntidadPadre` en el repositorio correspondiente.
     * - Para cada instancia de `EntidadPadre`, crea una entidad relacionada de tipo
     *   `EntidadHija` con un nombre identificativo, y la asocia a la entidad padre
     *   pertinente.
     * - Guarda las entidades hijas en el repositorio `entidadHijaRepository`.
     * - Registra mensajes informativos en el log sobre el inicio y finalización del proceso.
     *
     * Proceso:
     * 1. Se define un número fijo de entidades padre (10).
     * 2. Se utiliza un array para almacenar las instancias y se inicializa con un nombre
     *    único para cada entidad padre.
     * 3. Todas las entidades padre se guardan de forma simultánea utilizando
     *    {@code repository.saveAll}.
     * 4. Para cada entidad padre, se crea una instancia de la entidad hija, se establece
     *    la relación con el padre y se guarda en el repositorio correspondiente.
     * 5. Se registran logs informativos sobre el estado del proceso.
     *
     * Dependencias principales:
     * - `repository`: {@code EntidadPadreRepository}, usado para almacenar las entidades padre.
     * - `entidadHijaRepository`: {@code EntidadHijaRepository}, usado para guardar las entidades hijas.
     *
     * Importante:
     * - Este método está diseñado específicamente para ser utilizado en entornos con
     *   el perfil local activo.
     * - No debe usarse en entornos de producción, ya que sobrescribirá datos existentes.
     *
     * Logs:
     * - Mensaje al inicio del proceso: "Iniciando la carga de datos para el perfil local".
     * - Mensaje exitoso al finalizar: "Datos de entidades cargados correctamente."
     */
    @PostConstruct
    public void loadDataLocal() {

        log.info("Iniciando la carga de datos para el perfil local");
        int numeroEntidades = 100;
        EntidadPadre[] entidades = new EntidadPadre[numeroEntidades];
        Arrays.setAll(entidades, i -> new EntidadPadre("Entidad-" + (Integer.valueOf(i)+1)));
        repository.saveAll(Arrays.asList(entidades));
        for (EntidadPadre entidadPadre : entidades) {
            EntidadHija entidadHija = new EntidadHija("Hija de " + entidadPadre.getNombre());
            entidadHija.setEntidadPadre(entidadPadre);
            entidadHijaRepository.save(entidadHija);
        }

        // Personas (actores / directores) de prueba
        Persona persona1 = new Persona();
        persona1.setIdPersona(1L);
        persona1.setNombre("Daniel");
        persona1.setApellido("Radcliffe");
        persona1.setFechaNacimiento(new Date());
        persona1.setBiografia("Este chaval nación en Torremolinos junto con su familia y amigos." +
                " Disfrutó de una infancia agradable y luego se puso a hacer películas.");
        persona1.setFotoUrl("data:image/webp;base64,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");

        Persona persona2 = new Persona();
        persona2.setIdPersona(2L);
        persona2.setNombre("Emma");
        persona2.setApellido("Watson");
        persona2.setFechaNacimiento(new Date());
        persona2.setBiografia("Esta chavala nació en Guadalajara junto con su familia y amigos." +
                " Disfrutó de una infancia muy triste y luego se puso a hacer películas.");
        persona2.setFotoUrl("data:image/webp;base64,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DgECAM8AAABKAAAAmIICABEAAAAZAQAAGgEFAAEAAAAqAQAAGwEFAAEAAAAyAQAAEgEDAAEAAAABAAAAAAAAAE5FVyBZT1JLLCBORVcgWU9SSyAtIFNFUFRFTUJFUiAxNTogRW1tYSBXYXRzb24gYXR0ZW5kcyBUaGUgS2VyaW5nIEZvdW5kYXRpb24ncyBDYXJpbmcgZm9yIFdvbWVuIGRpbm5lciBhdCBUaGUgUG9vbCBvbiBQYXJrIEF2ZW51ZSBvbiBTZXB0ZW1iZXIgMTUsIDIwMjIgaW4gTmV3IFlvcmsgQ2l0eS4gKFBob3RvIGJ5IERpYSBEaXBhc3VwaWwvR2V0dHkgSW1hZ2VzKTIwMjIgR2V0dHkgSW1hZ2VzLAEAAAEAAAAsAQAAAQAAAFhNUCBIBgAAaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLwA8P3hwYWNrZXQgYmVnaW49Iu+7vyIgaWQ9Ilc1TTBNcENlaGlIenJlU3pOVGN6a2M5ZCI/Pgo8eDp4bXBtZXRhIHhtbG5zOng9ImFkb2JlOm5zOm1ldGEvIj4KCTxyZGY6UkRGIHhtbG5zOnJkZj0iaHR0cDovL3d3dy53My5vcmcvMTk5OS8wMi8yMi1yZGYtc3ludGF4LW5zIyI+CgkJPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6cGhvdG9zaG9wPSJodHRwOi8vbnMuYWRvYmUuY29tL3Bob3Rvc2hvcC8xLjAvIiB4bWxuczpJcHRjNHhtcENvcmU9Imh0dHA6Ly9pcHRjLm9yZy9zdGQvSXB0YzR4bXBDb3JlLzEuMC94bWxucy8iICAgeG1sbnM6R2V0dHlJbWFnZXNHSUZUPSJodHRwOi8veG1wLmdldHR5aW1hZ2VzLmNvbS9naWZ0LzEuMC8iIHhtbG5zOmRjPSJodHRwOi8vcHVybC5vcmcvZGMvZWxlbWVudHMvMS4xLyIgeG1sbnM6cGx1cz0iaHR0cDovL25zLnVzZXBsdXMub3JnL2xkZi94bXAvMS4wLyIgIHhtbG5zOmlwdGNFeHQ9Imh0dHA6Ly9pcHRjLm9yZy9zdGQvSXB0YzR4bXBFeHQvMjAwOC0wMi0yOS8iIHhtbG5zOnhtcFJpZ2h0cz0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL3JpZ2h0cy8iIGRjOlJpZ2h0cz0iMjAyMiBHZXR0eSBJbWFnZXMiIHBob3Rvc2hvcDpDcmVkaXQ9IkdldHR5IEltYWdlcyIgR2V0dHlJbWFnZXNHSUZUOkFzc2V0SUQ9IjE0MjQwOTExMTUiIHhtcFJpZ2h0czpXZWJTdGF0ZW1lbnQ9Imh0dHBzOi8vd3d3LmdldHR5aW1hZ2VzLmNvbS9ldWxhP3V0bV9tZWRpdW09b3JnYW5pYyZhbXA7dXRtX3NvdXJjZT1nb29nbGUmYW1wO3V0bV9jYW1wYWlnbj1pcHRjdXJsIiBwbHVzOkRhdGFNaW5pbmc9Imh0dHA6Ly9ucy51c2VwbHVzLm9yZy9sZGYvdm9jYWIvRE1JLVBST0hJQklURUQtRVhDRVBUU0VBUkNIRU5HSU5FSU5ERVhJTkciID4KPGRjOmNyZWF0b3I+PHJkZjpTZXE+PHJkZjpsaT5EaWEgRGlwYXN1cGlsPC9yZGY6bGk+PC9yZGY6U2VxPjwvZGM6Y3JlYXRvcj48ZGM6ZGVzY3JpcHRpb24+PHJkZjpBbHQ+PHJkZjpsaSB4bWw6bGFuZz0ieC1kZWZhdWx0Ij5ORVcgWU9SSywgTkVXIFlPUksgLSBTRVBURU1CRVIgMTU6IEVtbWEgV2F0c29uIGF0dGVuZHMgVGhlIEtlcmluZyBGb3VuZGF0aW9uJmFwb3M7cyBDYXJpbmcgZm9yIFdvbWVuIGRpbm5lciBhdCBUaGUgUG9vbCBvbiBQYXJrIEF2ZW51ZSBvbiBTZXB0ZW1iZXIgMTUsIDIwMjIgaW4gTmV3IFlvcmsgQ2l0eS4gKFBob3RvIGJ5IERpYSBEaXBhc3VwaWwvR2V0dHkgSW1hZ2VzKTwvcmRmOmxpPjwvcmRmOkFsdD48L2RjOmRlc2NyaXB0aW9uPgo8cGx1czpMaWNlbnNvcj48cmRmOlNlcT48cmRmOmxpIHJkZjpwYXJzZVR5cGU9J1Jlc291cmNlJz48cGx1czpMaWNlbnNvclVSTD5odHRwczovL3d3dy5nZXR0eWltYWdlcy5jb20vZGV0YWlsLzE0MjQwOTExMTU/dXRtX21lZGl1bT1vcmdhbmljJmFtcDt1dG1fc291cmNlPWdvb2dsZSZhbXA7dXRtX2NhbXBhaWduPWlwdGN1cmw8L3BsdXM6TGljZW5zb3JVUkw+PC9yZGY6bGk+PC9yZGY6U2VxPjwvcGx1czpMaWNlbnNvcj4KCQk8L3JkZjpEZXNjcmlwdGlvbj4KCTwvcmRmOlJERj4KPC94OnhtcG1ldGE+Cjw/eHBhY2tldCBlbmQ9InciPz4K");

        personaRepository.saveAll(Arrays.asList(persona1, persona2));

        // Usuarios de prueba
        Usuario usuario1 = new Usuario();
        usuario1.setIdUsuario(1L);
        usuario1.setNombreUsuario("Usuario dsadwaearwa");
        usuario1.setEmail("dsadas@dsadad.com");
        usuario1.setContrasena("9012491904912");

        Usuario usuario2 = new Usuario();
        usuario2.setIdUsuario(2L);
        usuario2.setNombreUsuario("Usuario pechuga");
        usuario2.setEmail("pechuga@pechuga.com");
        usuario2.setContrasena("90321312");

        usuarioRepository.saveAll(Arrays.asList(usuario1, usuario2));

        // Reseñas de prueba
        Resena resena1 = new Resena();
        resena1.setIdResena(1L);
        resena1.setTitulo("La peor película de mi vida - Harry Potter");
        resena1.setContenido("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Autem commodi" +
                " delectus, deleniti dolorem dolores ducimus eos ex facere laudantium magnam minus nihil odit quaerat" +
                " quibusdam quisquam quos repellat sunt vitae.");
        resena1.setPuntuacion((float) 3.5);
        resena1.setSpoiler(false);
        resena1.setUsuario(usuario1);

        Resena resena2 = new Resena();
        resena2.setIdResena(2L);
        resena2.setTitulo("La mejor película de mi vida - Harry Potter");
        resena2.setContenido("Holaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.");
        resena2.setPuntuacion((float) 2.6);
        resena2.setSpoiler(true);
        resena2.setUsuario(usuario2);

        resenaRepository.saveAll(Arrays.asList(resena1, resena2));

        // Objeto de prueba
        Objeto objetoPrueba = new Objeto();
        objetoPrueba.setIdObjeto(1L);
        objetoPrueba.setTitulo("Harry Potter");
        objetoPrueba.setDescripcion("La pelicula de harry potter trata sobre booasijdoaisjfoafijaosfa");
        objetoPrueba.setFechaPublicacion(new Date());
        objetoPrueba.setImagenUrl("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.compraentradas.com%2FPeliculaCine%2F56%2Fmulticines-rosaleda%2F2648%2Fharry-potter-y-la-piedra-filosofal&psig=AOvVaw3dwecVI0eUAPEk-0V9l8d-&ust=1747584555420000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCKCvtfLxqo0DFQAAAAAdAAAAABAt");
        objetoPrueba.setTrailerUrl("https://www.youtube.com/watch?v=6T45PEo55Po");
        objetoPrueba.setDuracionMinutos(123);
        objetoPrueba.setTemporadas(0);
        objetoPrueba.setEpisodios(0);
        objetoPrueba.setResenas(Arrays.asList(resena1, resena2));

        // Registros tabla intermedia persona - objeto de prueba
        PersonaObjeto personaObjeto1 = new PersonaObjeto();
        personaObjeto1.setIdPersonaObjeto(1L);
        personaObjeto1.setRol(false);
        personaObjeto1.setPersona(persona1);
        personaObjeto1.setObjeto(objetoPrueba);

        PersonaObjeto personaObjeto2 = new PersonaObjeto();
        personaObjeto2.setIdPersonaObjeto(1L);
        personaObjeto2.setRol(false);
        personaObjeto2.setPersona(persona2);
        personaObjeto2.setObjeto(objetoPrueba);

        personaObjetoRepository.saveAll(Arrays.asList(personaObjeto1, personaObjeto2));

        objetoPrueba.setPersonasObjetos(new HashSet<>(Arrays.asList(personaObjeto1, personaObjeto2)));

        objetoRepository.save(objetoPrueba);

        log.info("Datos de entidades cargados correctamente.");
    }





}

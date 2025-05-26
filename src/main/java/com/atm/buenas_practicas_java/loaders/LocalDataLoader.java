package com.atm.buenas_practicas_java.loaders;

import com.atm.buenas_practicas_java.entities.*;
import com.atm.buenas_practicas_java.repositories.*;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private final TipoRepository tipoRepository;
    private final GeneroRepository generoRepository;
    private final GeneroObjetoRepository generoObjetoRepository;
    private final ComentarioPublicacionRepository comentarioPublicacionRepository;
    private final PublicacionRepository publicacionRepository;
    private final ComunidadRepository comunidadRepository;
    private final ComentarioResenaRepository comentarioResenaRepository;

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
    public LocalDataLoader(EntidadPadreRepository repository, EntidadHijaRepository entidadHijaRepository, ObjetoRepository objetoRepository, PersonaRepository personaRepository, PersonaObjetoRepository personaObjetoRepository, UsuarioRepository usuarioRepository, ResenaRepository resenaRepository, TipoRepository tipoRepository, GeneroRepository generoRepository, GeneroObjetoRepository generoObjetoRepository, ComentarioPublicacionRepository comentarioPublicacionRepository, PublicacionRepository publicacionRepository, ComunidadRepository comunidadRepository, ComentarioResenaRepository comentarioResenaRepository) {
        this.repository = repository;
        this.entidadHijaRepository = entidadHijaRepository;
        this.objetoRepository = objetoRepository;
        this.personaRepository = personaRepository;
        this.personaObjetoRepository = personaObjetoRepository;
        this.usuarioRepository = usuarioRepository;
        this.resenaRepository = resenaRepository;
        this.tipoRepository = tipoRepository;
        this.generoRepository = generoRepository;
        this.generoObjetoRepository = generoObjetoRepository;
        this.comentarioPublicacionRepository = comentarioPublicacionRepository;
        this.publicacionRepository = publicacionRepository;
        this.comunidadRepository = comunidadRepository;
        this.comentarioResenaRepository = comentarioResenaRepository;
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

        // Tipos de objeto
        Tipo tipoPeliculas = new Tipo();
        tipoPeliculas.setNombre("pelicula");
        Tipo tipoSeries = new Tipo();
        tipoSeries.setNombre("serie");
        Tipo tipoVideojuegos = new Tipo();
        tipoVideojuegos.setNombre("videojuego");

        tipoRepository.saveAll(Arrays.asList(tipoPeliculas, tipoSeries, tipoVideojuegos));

        // Géneros de prueba
        Genero genero1 = new Genero();
        genero1.setNombre("Drama");
        Genero genero2 = new Genero();
        genero2.setNombre("Romance");
        Genero genero3 = new Genero();
        genero3.setNombre("Comedia");
        Genero genero4 = new Genero();
        genero4.setNombre("Ficción");
        Genero genero5 = new Genero();
        genero5.setNombre("Fantasía");

        generoRepository.saveAll(Arrays.asList(genero1, genero2, genero3, genero4, genero5));

        // Personas (actores / directores) de prueba
        Persona persona1 = new Persona();
        persona1.setNombre("Daniel");
        persona1.setApellido("Radcliffe");
        persona1.setBiografia("Este chaval nació en Torremolinos junto con su familia y amigos." +
                " Disfrutó de una infancia agradable y luego se puso a hacer películas. \n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt " +
                "ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation" +
                " ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit " +
                "in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat " +
                "non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        persona1.setFotoUrl("https://cdn-images.dzcdn.net/images/artist/477d3a877aeb43dd565cb0d9888861f7/1900x1900-000000-80-0-0.jpg");

        Persona persona2 = new Persona();
        persona2.setNombre("Emma");
        persona2.setApellido("Watson");
        persona2.setBiografia("Esta chavala nació en Guadalajara junto con su familia y amigos." +
                " Disfrutó de una infancia muy triste y luego se puso a hacer películas. \n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore " +
                "et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut " +
                "aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum" +
                " dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui" +
                " officia deserunt mollit anim id est laborum.");
        persona2.setFotoUrl("https://m.media-amazon.com/images/M/MV5BMTQ3ODE2NTMxMV5BMl5BanBnXkFtZTgwOTIzOTQzMjE@._V1_.jpg");

        Persona persona3 = new Persona();
        persona3.setNombre("Chris");
        persona3.setApellido("Colombus");
        persona3.setBiografia("Chris nació en una familia ítalo-estadounidense en Spangler, Pensilvania y se crio en " +
                "Youngstown, Ohio, hijo de Mary Irene, una trabajadora de fábrica, y Alex Michael Columbus, un minero." +
                "\nChris Columbus efectúa sus estudios secundarios en una aldea de Ohio y desarrolla su imaginación " +
                "creadora dibujando storyboards y realizando pequeñas películas de ficción en Súper 8.\n" +
                "En 1990, el también director John Hughes le ofreció la oportunidad de dirigir su propio guion con " +
                "Home Alone, que sorprendió a Hollywood al convertirse en la comedia más taquillera de todos los tiempos. " +
                "En 2001 dirigió Harry Potter y la piedra filosofal y en 2002 Harry Potter y la cámara secreta. " +
                "Es dueño de la productora 1492 Pictures, la cual fundó en 1995. ");
        persona3.setFotoUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/1/14/Chris_Columbus.jpg/500px-Chris_Columbus.jpg");

        personaRepository.saveAll(Arrays.asList(persona1, persona2, persona3));

        // Objeto de prueba
        Objeto objeto = new Objeto();
        objeto.setTitulo("Harry Potter");
        objeto.setDescripcion("Harry Potter es una pelicula que trata de " +
                " Lorem ipsum dolor sit amet, consectetur adipisicing elit. Autem commodi" +
                " delectus, deleniti dolorem dolores ducimus eos ex facere laudantium magnam minus nihil odit quaerat" +
                " quibusdam quisquam quos repellat sunt vitae");
        objeto.setImagenUrl("https://www.compraentradas.com/Carteles/piedrafilosofal.jpg");
        objeto.setDuracionMinutos(123);
        objeto.setTipo(tipoPeliculas);
        objeto.setTrailerUrl("https://www.youtube.com/embed/6T45PEo55Po");
        objeto.setFechaPublicacion(LocalDate.of(2001, 3, 01));

        PersonaObjeto personaObjeto1 = new PersonaObjeto();
        personaObjeto1.setRol(false);
        personaObjeto1.setPersona(persona1);
        personaObjeto1.setObjeto(objeto);

        PersonaObjeto personaObjeto2 = new PersonaObjeto();
        personaObjeto2.setRol(false);
        personaObjeto2.setPersona(persona2);
        personaObjeto2.setObjeto(objeto);

        PersonaObjeto personaObjeto3 = new PersonaObjeto();
        personaObjeto3.setRol(true);
        personaObjeto3.setPersona(persona3);
        personaObjeto3.setObjeto(objeto);

        objeto.setPersonasObjeto(new HashSet<>(Arrays.asList(personaObjeto1, personaObjeto2, personaObjeto3)));

        GeneroObjeto generoObjeto1 = new GeneroObjeto();
        generoObjeto1.setGenero(genero1);
        generoObjeto1.setObjeto(objeto);
        GeneroObjeto generoObjeto2 = new GeneroObjeto();
        generoObjeto2.setGenero(genero2);
        generoObjeto2.setObjeto(objeto);
        GeneroObjeto generoObjeto3 = new GeneroObjeto();
        generoObjeto3.setGenero(genero3);
        generoObjeto3.setObjeto(objeto);
        GeneroObjeto generoObjeto4 = new GeneroObjeto();
        generoObjeto4.setGenero(genero4);
        generoObjeto4.setObjeto(objeto);
        GeneroObjeto generoObjeto5 = new GeneroObjeto();
        generoObjeto5.setGenero(genero5);
        generoObjeto5.setObjeto(objeto);


        objeto.setGenerosObjeto(new HashSet<>(Arrays.asList(generoObjeto1, generoObjeto2, generoObjeto3, generoObjeto4, generoObjeto5)));

        objetoRepository.save(objeto);

        generoObjetoRepository.saveAll(Arrays.asList(generoObjeto1, generoObjeto2, generoObjeto3, generoObjeto4, generoObjeto5));

        personaObjetoRepository.saveAll(Arrays.asList(personaObjeto1, personaObjeto2, personaObjeto3));

        // Usuarios de prueba
        Usuario usuario1 = new Usuario();
        usuario1.setNombreUsuario("Usuario1");
        usuario1.setEmail("hola@gmail.com");
        usuario1.setContrasena("1234");

        Usuario usuario2 = new Usuario();
        usuario2.setNombreUsuario("Usuario2");
        usuario2.setEmail("adios@gmail.com");
        usuario2.setContrasena("4321");

        Usuario usuario3 = new Usuario();
        usuario3.setNombreUsuario("Usuario3");
        usuario3.setEmail("odijajoaspco@gmail.es");
        usuario3.setContrasena("4313213213232132");

        usuarioRepository.saveAll(Arrays.asList(usuario1, usuario2, usuario3));

        // Reseñas de prueba
        Resena resena1 = new Resena();
        resena1.setTitulo("La peor película de mi vida");
        resena1.setContenido("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus at mi felis. " +
                "Vivamus efficitur tincidunt aliquet. Etiam eu rhoncus leo. Aenean posuere enim massa, viverra " +
                "aliquam risus pharetra et. Nulla euismod efficitur lectus vitae bibendum. Aenean lobortis " +
                "molestie erat at imperdiet. Phasellus pellentesque consectetur nunc eu commodo. Nunc malesuada " +
                "consequat porttitor. Sed condimentum augue ipsum. Donec vitae dui laoreet, luctus leo vitae, " +
                "pharetra lorem. Nulla eleifend ipsum leo, a ornare quam vulputate et. Morbi ac aliquet quam. " +
                "Cras dignissim tincidunt condimentum. Phasellus placerat venenatis lobortis. Praesent commodo " +
                "sodales sapien, vitae fermentum odio lobortis ut. Fusce blandit varius mollis.Maecenas cursus " +
                "ullamcorper nunc in euismod. In eget auctor nunc. Phasellus id mauris tortor. Morbi imperdiet " +
                "tristique accumsan. Vivamus egestas turpis nulla, et ornare nisi tempus eget. Quisque commodo " +
                "erat non mi pellentesque, vitae condimentum nisi laoreet. Donec in nulla ex. Suspendisse consequat " +
                "ac nulla ac pellentesque. Nulla non ornare nulla, vulputate placerat risus. Sed in justo egestas, " +
                "fermentum neque ut, mollis eros. Vivamus gravida odio nec laoreet lacinia. Nulla urna velit, " +
                "tincidunt quis tincidunt venenatis, pretium quis urna. Nulla in ipsum dolor. ");
        resena1.setPuntuacion(3.0);
        resena1.setSpoiler(false);
        resena1.setUsuario(usuario1);
        resena1.setObjeto(objeto);

        ComentarioResena comentarioResena1 = new ComentarioResena();
        comentarioResena1.setResena(resena1);
        comentarioResena1.setUsuario(usuario2);
        comentarioResena1.setContenido("La verdad es que tu reseña se ha quedao flojilla. Podrías haber añadido esto:" +
                " Lorem ipsum dolor sit amet, consectetur adipisicing elit. Autem commodi\" +\n" +
                "                \" delectus, deleniti dolorem dolores ducimus eos ex facere laudantium magnam minus nihil odit quaerat\" +\n" +
                "                \" quibusdam quisquam quos repellat sunt vitae.");

        ComentarioResena comentarioResena2 = new ComentarioResena();
        comentarioResena2.setResena(resena1);
        comentarioResena2.setUsuario(usuario3);
        comentarioResena2.setContenido("Me ha gustao mucho tu reseña, mi pana. Ánimo con lo tuyo.");

        resena1.setComentariosResena(new HashSet<>(Arrays.asList(comentarioResena1, comentarioResena2)));



        Resena resena2 = new Resena();
        resena2.setTitulo("La mejor película de mi vida");
        resena2.setContenido("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Autem commodi" +
                " delectus, deleniti dolorem dolores ducimus eos ex facere laudantium magnam minus nihil odit quaerat" +
                " quibusdam quisquam quos repellat sunt vitae.");
        resena2.setPuntuacion(3.5);
        resena2.setSpoiler(false);
        resena2.setUsuario(usuario2);
        resena2.setObjeto(objeto);


        resenaRepository.saveAll(Arrays.asList(resena1, resena2));

        comentarioResenaRepository.saveAll(Arrays.asList(comentarioResena1, comentarioResena2));

        // Datos de prueba para las publicaciones, comunidades y comentarios

        Publicacion publicacion1 = new Publicacion();
        publicacion1.setTitulo("Recomendaciones para ver la película _________ de harry potter");

        Comunidad comunidad1 = new Comunidad();
        comunidad1.setNombreComunidad("Comunidad de Harry Potter");
        comunidad1.setDescripcion("En la comunidad de Harry Potter podrás hablar de todas " +
                "las películas, series y videojuegos relacionados. ¡Anímate y haz una publicación!");
        comunidad1.setObjetos(Arrays.asList(objeto));
        comunidad1.setPublicaciones(Arrays.asList(publicacion1));

        objeto.setComunidad(comunidad1);

        publicacion1.setComunidad(comunidad1);


        ComentarioPublicacion comentarioPublicacion1 = new ComentarioPublicacion();
        comentarioPublicacion1.setPublicacion(publicacion1);
        comentarioPublicacion1.setUsuario(usuario1);
        comentarioPublicacion1.setContenido("Este es el primer comentario de la publicación 1." +
                " Prueba para ver si sólo sale el primero de todos los comentarios de cada una de" +
                "las publicaciones asociadas a la comunidad.");

        ComentarioPublicacion comentarioPublicacion2 = new ComentarioPublicacion();
        comentarioPublicacion2.setPublicacion(publicacion1);
        comentarioPublicacion2.setUsuario(usuario2);
        comentarioPublicacion2.setContenido("Este es el segundo comentario de la publicación 1." +
                " Prueba para ver si sólo sale el primero de todos lso comentarios de cada una de" +
                " las publicaciones asociadas a la comunidad.");

        publicacion1.setComentariosPublicacion(Arrays.asList(comentarioPublicacion1, comentarioPublicacion2));


        comunidadRepository.saveAll(Arrays.asList(comunidad1));
        publicacionRepository.save(publicacion1);
        comentarioPublicacionRepository.saveAll(Arrays.asList(comentarioPublicacion1, comentarioPublicacion2));

        objetoRepository.save(objeto);

        log.info("Datos de entidades cargados correctamente.");

    }
}

package com.atm.buenas_practicas_java.loaders;

import com.atm.buenas_practicas_java.entities.*;
import com.atm.buenas_practicas_java.repositories.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
* Clase de configuración que permite cargar datos iniciales en los repositorios
* de entidades para diferentes perfiles de configuración.
*
* Esta clase es útil para inicializar datos predefinidos utilizados durante el
* desarrollo o en entornos locales.
*
* Se utiliza la anotación @Configuration para indicar que es una clase de configuración
* de Spring, y métodos específicos anotados con @Profile para definir qué datos
* iniciales se cargarán según el perfil activo.
*/

@Configuration
@Log4j2
@Profile("desarrollo")
@RequiredArgsConstructor
public class DesarrolloDataLoader {

    private final ObjetoRepository objetoRepository;
    private final PersonaRepository personaRepository;
    private final PersonaObjetoRepository personaObjetoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ResenaRepository resenaRepository;
    private final TipoRepository tipoRepository;
    private final GeneroRepository generoRepository;
    private final ComentarioPublicacionRepository comentarioPublicacionRepository;
    private final PublicacionRepository publicacionRepository;
    private final ComunidadRepository comunidadRepository;
    private final ComentarioResenaRepository comentarioResenaRepository;
    private final ObjetoUsuarioRepository objetoUsuarioRepository;
    private final PasswordEncoder encoder;

 /**
 * Clase de configuración que permite cargar datos iniciales en los repositorios
 * de entidades para diferentes perfiles de configuración.
 *
 * Esta clase es útil para inicializar datos predefinidos utilizados durante el
 * desarrollo o en entornos específicos según el perfil.
 *
 * **Anotaciones utilizadas**:
 * - `@Configuration`: Define esta clase como una clase de configuración de Spring.
 *   Permite registrar beans en el contexto de la aplicación y gestionar configuraciones específicas.
 *
 * - `@Log4j2`: Habilita el uso de Log4j2 para registrar mensajes de log importantes,
 *   utilizados para monitoreo y depuración de la aplicación.
 *
 * Cada método anotado con `@Profile` y `@PostConstruct` permite la carga de datos
 * iniciales dependiendo del perfil activo.
 */

@PostConstruct
public void loadDataDesarrollo() {
    log.info("Iniciando la carga de datos para el perfil desarrollo");

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    log.info("Iniciando la carga de datos para el perfil local");

    // Tipos de objeto
    Tipo tipoPeliculas = new Tipo();
    tipoPeliculas.setNombre("pelicula");
    Tipo tipoSeries = new Tipo();
    tipoSeries.setNombre("serie");
    Tipo tipoVideojuegos = new Tipo();
    tipoVideojuegos.setNombre("videojuego");

    tipoRepository.saveAll(Arrays.asList(tipoPeliculas, tipoSeries, tipoVideojuegos));

    // Géneros de prueba
    /* GÉNEROS PELÍCULAS */
    Genero generoDramaP = new Genero();
    generoDramaP.setNombre("Drama");
    generoDramaP.setTipo(tipoPeliculas);
    Genero generoRomanceP = new Genero();
    generoRomanceP.setNombre("Romance");
    generoRomanceP.setTipo(tipoPeliculas);
    Genero generoComediaP = new Genero();
    generoComediaP.setNombre("Comedia");
    generoComediaP.setTipo(tipoPeliculas);
    Genero generoFiccionP = new Genero();
    generoFiccionP.setNombre("Ficción");
    generoFiccionP.setTipo(tipoPeliculas);
    Genero generoFantasiaP = new Genero();
    generoFantasiaP.setNombre("Fantasía");
    generoFantasiaP.setTipo(tipoPeliculas);
    Genero generoAccionP = new Genero();
    generoAccionP.setNombre("Acción");
    generoAccionP.setTipo(tipoPeliculas);
    Genero generoAventurasP = new Genero();
    generoAventurasP.setNombre("Aventuras");
    generoAventurasP.setTipo(tipoPeliculas);
    Genero generoAnimacionP = new Genero();
    generoAnimacionP.setNombre("Animación");
    generoAnimacionP.setTipo(tipoPeliculas);
    Genero generoAnimeP = new Genero();
    generoAnimeP.setNombre("Anime");
    generoAnimeP.setTipo(tipoPeliculas);
    Genero generoBelicoP = new Genero();
    generoBelicoP.setNombre("Bélico");
    generoBelicoP.setTipo(tipoPeliculas);
    Genero generoPoliciacaP = new Genero();
    generoPoliciacaP.setNombre("Policiaca");
    generoPoliciacaP.setTipo(tipoPeliculas);
    Genero generoDocumentalP = new Genero();
    generoDocumentalP.setNombre("Documental");
    generoDocumentalP.setTipo(tipoPeliculas);
    Genero generoFamiliarP = new Genero();
    generoFamiliarP.setNombre("Familiar");
    generoFamiliarP.setTipo(tipoPeliculas);
    Genero generoHistoriaP = new Genero();
    generoHistoriaP.setNombre("Historia");
    generoHistoriaP.setTipo(tipoPeliculas);
    Genero generoMusicalP = new Genero();
    generoMusicalP.setNombre("Musical");
    generoMusicalP.setTipo(tipoPeliculas);
    Genero generoMisterioP = new Genero();
    generoMisterioP.setNombre("Misterio");
    generoMisterioP.setTipo(tipoPeliculas);
    Genero generoSuspenseP = new Genero();
    generoSuspenseP.setNombre("Suspense");
    generoSuspenseP.setTipo(tipoPeliculas);
    Genero generoTerrorP = new Genero();
    generoTerrorP.setNombre("Terror");
    generoTerrorP.setTipo(tipoPeliculas);
    Genero generoMafiaP = new Genero();
    generoMafiaP.setNombre("Mafia");
    generoMafiaP.setTipo(tipoPeliculas);


    /* GÉNEROS SERIES */
    Genero generoDramaS = new Genero();
    generoDramaS.setNombre("Drama");
    generoDramaS.setTipo(tipoSeries);
    Genero generoRomanceS = new Genero();
    generoRomanceS.setNombre("Romance");
    generoRomanceS.setTipo(tipoSeries);
    Genero generoComediaS = new Genero();
    generoComediaS.setNombre("Comedia");
    generoComediaS.setTipo(tipoSeries);
    Genero generoFiccionS = new Genero();
    generoFiccionS.setNombre("Ficción");
    generoFiccionS.setTipo(tipoSeries);
    Genero generoFantasiaS = new Genero();
    generoFantasiaS.setNombre("Fantasía");
    generoFantasiaS.setTipo(tipoSeries);
    Genero generoAccionS = new Genero();
    generoAccionS.setNombre("Acción");
    generoAccionS.setTipo(tipoSeries);
    Genero generoAventurasS = new Genero();
    generoAventurasS.setNombre("Aventuras");
    generoAventurasS.setTipo(tipoSeries);
    Genero generoAnimacionS = new Genero();
    generoAnimacionS.setNombre("Animación");
    generoAnimacionS.setTipo(tipoSeries);
    Genero generoAnimeS = new Genero();
    generoAnimeS.setNombre("Anime");
    generoAnimeS.setTipo(tipoSeries);
    Genero generoBelicoS = new Genero();
    generoBelicoS.setNombre("Bélico");
    generoBelicoS.setTipo(tipoSeries);
    Genero generoPoliciacaS = new Genero();
    generoPoliciacaS.setNombre("Policiaca");
    generoPoliciacaS.setTipo(tipoSeries);
    Genero generoDocumentalS = new Genero();
    generoDocumentalS.setNombre("Documental");
    generoDocumentalS.setTipo(tipoSeries);
    Genero generoFamiliarS = new Genero();
    generoFamiliarS.setNombre("Familiar");
    generoFamiliarS.setTipo(tipoSeries);
    Genero generoHistoriaS = new Genero();
    generoHistoriaS.setNombre("Historia");
    generoHistoriaS.setTipo(tipoSeries);
    Genero generoMusicalS = new Genero();
    generoMusicalS.setNombre("Musical");
    generoMusicalS.setTipo(tipoSeries);
    Genero generoMisterioS = new Genero();
    generoMisterioS.setNombre("Misterio");
    generoMisterioS.setTipo(tipoSeries);
    Genero generoSuspenseS = new Genero();
    generoSuspenseS.setNombre("Suspense");
    generoSuspenseS.setTipo(tipoSeries);
    Genero generoTerrorS = new Genero();
    generoTerrorS.setNombre("Terror");
    generoTerrorS.setTipo(tipoSeries);


    /* GÉNEROS VIDEOJUEGOS */
    Genero generoAventuraV = new Genero();
    generoAventuraV.setNombre("Aventura");
    generoAventuraV.setTipo(tipoVideojuegos);
    Genero generoAccionV = new Genero();
    generoAccionV.setNombre("Acción");
    generoAccionV.setTipo(tipoVideojuegos);
    Genero generoRPGV = new Genero();
    generoRPGV.setNombre("RPG");
    generoRPGV.setTipo(tipoVideojuegos);
    Genero generoEstrategiaV = new Genero();
    generoEstrategiaV.setNombre("Estrategia");
    generoEstrategiaV.setTipo(tipoVideojuegos);
    Genero generoDeportesV = new Genero();
    generoDeportesV.setNombre("Deportes");
    generoDeportesV.setTipo(tipoVideojuegos);
    Genero generoCarrerasV = new Genero();
    generoCarrerasV.setNombre("Carreras");
    generoCarrerasV.setTipo(tipoVideojuegos);
    Genero generoSimulacionV = new Genero();
    generoSimulacionV.setNombre("Simulación");
    generoSimulacionV.setTipo(tipoVideojuegos);
    Genero generoTerrorV = new Genero();
    generoTerrorV.setNombre("Terror");
    generoTerrorV.setTipo(tipoVideojuegos);
    Genero generoLuchaV = new Genero();
    generoLuchaV.setNombre("Lucha");
    generoLuchaV.setTipo(tipoVideojuegos);
    Genero generoPlataformasV = new Genero();
    generoPlataformasV.setNombre("Plataformas");
    generoPlataformasV.setTipo(tipoVideojuegos);
    Genero generoPuzlesV = new Genero();
    generoPuzlesV.setNombre("Puzles");
    generoPuzlesV.setTipo(tipoVideojuegos);
    Genero generoAventuraGraficaV = new Genero();
    generoAventuraGraficaV.setNombre("Aventura gráfica");
    generoAventuraGraficaV.setTipo(tipoVideojuegos);
    Genero generoSandboxV = new Genero();
    generoSandboxV.setNombre("Sandbox");
    generoSandboxV.setTipo(tipoVideojuegos);
    Genero generoSupervicenciaV = new Genero();
    generoSupervicenciaV.setNombre("Supervicencia");
    generoSupervicenciaV.setTipo(tipoVideojuegos);
    Genero generoMusicaV = new Genero();
    generoMusicaV.setNombre("Musica");
    generoMusicaV.setTipo(tipoVideojuegos);
    Genero generoBattleRoyaleV = new Genero();
    generoBattleRoyaleV.setNombre("Battle Royale");
    generoBattleRoyaleV.setTipo(tipoVideojuegos);
    Genero generoTacticoV = new Genero();
    generoTacticoV.setNombre("Táctico");
    generoTacticoV.setTipo(tipoVideojuegos);
    Genero generoMMOV = new Genero();
    generoMMOV.setNombre("MMO");
    generoMMOV.setTipo(tipoVideojuegos);
    Genero generoMundoAbiertoV = new Genero();
    generoMundoAbiertoV.setNombre("Mundo Abierto");
    generoMundoAbiertoV.setTipo(tipoVideojuegos);
    Genero generoJuegoCartasV = new Genero();
    generoJuegoCartasV.setNombre("Juego de cartas");
    generoJuegoCartasV.setTipo(tipoVideojuegos);
    Genero generoJuegoMesaV = new Genero();
    generoJuegoMesaV.setNombre("Juego de mesa");
    generoJuegoMesaV.setTipo(tipoVideojuegos);
    Genero generoRTSV = new Genero();
    generoRTSV.setNombre("RTS");
    generoRTSV.setTipo(tipoVideojuegos);
    Genero generoTBSV = new Genero();
    generoTBSV.setNombre("TBS");
    generoTBSV.setTipo(tipoVideojuegos);
    Genero generoSupervivenciaV = new Genero();
    generoSupervivenciaV.setNombre("Supervivencia");
    generoSupervivenciaV.setTipo(tipoVideojuegos);
    Genero generoEducativoV = new Genero();
    generoEducativoV.setNombre("Educativo");
    generoEducativoV.setTipo(tipoVideojuegos);




    generoRepository.saveAll(Arrays.asList(generoDramaP, generoRomanceP, generoComediaP, generoFiccionP,
            generoFantasiaP, generoAccionP, generoAventurasP, generoAnimacionP, generoAnimeP, generoBelicoP,
            generoPoliciacaP, generoDocumentalP, generoFamiliarP, generoHistoriaP, generoMusicalP,
            generoMisterioP, generoSuspenseP, generoTerrorP, generoMafiaP, generoDramaS, generoRomanceS,
            generoComediaS, generoFiccionS, generoFantasiaS, generoAccionS, generoAventurasS, generoAnimacionS,
            generoAnimeS,  generoBelicoS, generoPoliciacaS, generoDocumentalS, generoFamiliarS, generoHistoriaS,
            generoMusicalS,  generoMisterioS, generoSuspenseS, generoTerrorS, generoAventuraV, generoAccionV,
            generoRPGV, generoEstrategiaV, generoDeportesV, generoCarrerasV, generoSimulacionV, generoTerrorV,
            generoLuchaV, generoPlataformasV, generoPuzlesV, generoAventuraGraficaV, generoSandboxV,
            generoSupervicenciaV, generoMusicaV, generoBattleRoyaleV, generoTacticoV, generoMMOV,
            generoMundoAbiertoV, generoJuegoCartasV, generoJuegoMesaV, generoRTSV, generoTBSV, generoSupervivenciaV,
            generoEducativoV));

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
    // PELÍCULAS
    Objeto objeto = new Objeto();
    objeto.setTitulo("Harry Potterrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
    objeto.setDescripcion("Harry Potter es una pelicula que trata de " +
            " Lorem ipsum dolor sit amet, consectetur adipisicing elit. Autem commodi" +
            " delectus, deleniti dolorem dolores ducimus eos ex facere laudantium magnam minus nihil odit quaerat" +
            " quibusdam quisquam quos repellat sunt vitae");
    objeto.setImagenUrl("https://artesiete.es/Posters/harrypotterpiedra20.jpg");
    objeto.setDuracionMinutos(123);
    objeto.setTipo(tipoPeliculas);
    objeto.setTrailerUrl("https://www.youtube.com/embed/6T45PEo55Po");
    objeto.setFechaPublicacion(LocalDate.of(2001, 3, 1));

    objeto.setGeneros(new HashSet<>(Arrays.asList(generoFantasiaP, generoFiccionP, generoAventurasP)));

    Objeto objeto2 = new Objeto();
    objeto2.setTitulo("Interstellar");
    objeto2.setDescripcion("Interstellar es una pelicula que trata de " +
            " Lorem ipsum dolor sit amet, consectetur adipisicing elit. Autem commodi" +
            " delectus, deleniti dolorem dolores ducimus eos ex facere laudantium magnam minus nihil odit quaerat" +
            " quibusdam quisquam quos repellat sunt vitae");
    objeto2.setImagenUrl("https://m.media-amazon.com/images/M/MV5BYzdjMDAxZGItMjI2My00ODA1LTlkNzItOWFjMDU5ZDJlYWY3XkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg");
    objeto2.setDuracionMinutos(169);
    objeto2.setTipo(tipoPeliculas);
    objeto2.setTrailerUrl("https://www.youtube.com/embed/UoSSbmD9vqc");
    objeto2.setFechaPublicacion(LocalDate.of(2014, 11, 07));

    objeto2.setGeneros(new HashSet<>(Arrays.asList(generoFiccionP, generoAccionP, generoSuspenseP, generoDramaP)));

    Objeto objeto3 = new Objeto();
    objeto3.setTitulo("The Gentlemen");
    objeto3.setDescripcion("The Gentlemen es una pelicula que trata de " +
            " Lorem ipsum dolor sit amet, consectetur adipisicing elit. Autem commodi" +
            " delectus, deleniti dolorem dolores ducimus eos ex facere laudantium magnam minus nihil odit quaerat" +
            " quibusdam quisquam quos repellat sunt vitae");
    objeto3.setImagenUrl("https://pics.filmaffinity.com/The_Gentlemen_Los_seanores_de_la_mafia-425828685-large.jpg");
    objeto3.setDuracionMinutos(113);
    objeto3.setTipo(tipoPeliculas);
    objeto3.setTrailerUrl("https://www.youtube.com/embed/SywTszaosOo");
    objeto3.setFechaPublicacion(LocalDate.of(2019, 12, 03));

    objeto3.setGeneros(new HashSet<>(Arrays.asList(generoAccionP, generoDramaP, generoSuspenseP, generoPoliciacaP)));

    Objeto objeto4 = new Objeto();
    objeto4.setTitulo("Cadena Perpetua");
    objeto4.setDescripcion("Cadena perpetua es una pelicula que trata de " +
            " Lorem ipsum dolor sit amet, consectetur adipisicing elit. Autem commodi" +
            " delectus, deleniti dolorem dolores ducimus eos ex facere laudantium magnam minus nihil odit quaerat" +
            " quibusdam quisquam quos repellat sunt vitae");
    objeto4.setImagenUrl("https://www.ecartelera.com/carteles/5600/5676/003_m.jpg");
    objeto4.setDuracionMinutos(142);
    objeto4.setTipo(tipoPeliculas);
    objeto4.setTrailerUrl("https://www.youtube.com/embed/PLl99DlL6b4");
    objeto4.setFechaPublicacion(LocalDate.of(1995, 2, 24));

    objeto4.setGeneros(new HashSet<>(Arrays.asList(generoSuspenseP, generoDramaP, generoMisterioP, generoPoliciacaP)));

    Objeto objeto5 = new Objeto();
    objeto5.setTitulo("El Padrino");
    objeto5.setDescripcion("El Padrino es una pelicula que trata de " +
            " Lorem ipsum dolor sit amet, consectetur adipisicing elit. Autem commodi" +
            " delectus, deleniti dolorem dolores ducimus eos ex facere laudantium magnam minus nihil odit quaerat" +
            " quibusdam quisquam quos repellat sunt vitae");
    objeto5.setDuracionMinutos(175);
    objeto5.setTipo(tipoPeliculas);
    objeto5.setImagenUrl("https://www.ecartelera.com/carteles/2500/2521/002.jpg");
    objeto5.setFechaPublicacion(LocalDate.of(1969, 3, 10));

    objeto5.setGeneros(new HashSet<>(Arrays.asList(generoAccionP, generoMisterioP, generoDramaP, generoMafiaP)));

    Objeto objeto6 = new Objeto();
    objeto6.setTitulo("12 hombres sin piedad");
    objeto6.setDescripcion("12 hombres sin piedad es una película que trata de " +
            " Lorem ipsum dolor sit amet, consectetur adipisicing elit. Autem commodi" +
            " delectus, deleniti dolorem dolores ducimus eos ex facere laudantium magnam minus nihil odit quaerat" +
            " quibusdam quisquam quos repellat sunt vitae");
    objeto6.setDuracionMinutos(96);
    objeto6.setTipo(tipoPeliculas);
    objeto6.setImagenUrl("https://cinesembajadores.es/wp-content/uploads/2025/01/12-hombres-sin-piedad-cartel.jpeg");
    objeto6.setTrailerUrl("https://www.youtube.com/embed/I0OPus5jM2w");
    objeto6.setFechaPublicacion(LocalDate.of(1958, 2, 3));

    objeto6.setGeneros(new HashSet<>(Arrays.asList(generoDramaP, generoSuspenseP, generoMisterioP, generoPoliciacaP)));

    Objeto objeto7 = new Objeto();
    objeto7.setTitulo("La lista de Schindler");
    objeto7.setDescripcion("La lista de schindler es una película que trata de " +
            " Lorem ipsum dolor sit amet, consectetur adipisicing elit. Autem commodi" +
            " delectus, deleniti dolorem dolores ducimus eos ex facere laudantium magnam minus nihil odit quaerat" +
            " quibusdam quisquam quos repellat sunt vitae");
    objeto7.setDuracionMinutos(195);
    objeto7.setTipo(tipoPeliculas);
    objeto7.setImagenUrl("https://pics.filmaffinity.com/schindler_s_list-473662617-large.jpg");
    objeto7.setTrailerUrl("https://www.youtube.com/embed/7q-ETFeMxwI");
    objeto7.setFechaPublicacion(LocalDate.of(1994, 3, 4));

    objeto7.setGeneros(new HashSet<>(Arrays.asList(generoBelicoP, generoDramaP, generoHistoriaP)));

    Objeto objeto8 = new Objeto();
    objeto8.setTitulo("Pulp Fiction");
    objeto8.setDescripcion("Historias de dos matones, un boxeador y una pareja de atracadores de poca monta envueltos en una violencia espectacular e irónica.");
    objeto8.setDuracionMinutos(154);
    objeto8.setTipo(tipoPeliculas);
    objeto8.setImagenUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1cYyaS50kL0g7JCqP8Dd3-T1NFmcVoFbQcQ&s");
    objeto8.setTrailerUrl("https://www.youtube.com/embed/ZFYCXAG6fdo");
    objeto8.setFechaPublicacion(LocalDate.of(1995, 1, 13));

    objeto8.setGeneros(new HashSet<>(Arrays.asList(generoComediaP, generoMafiaP, generoAccionP, generoSuspenseP)));

    Objeto objeto9 = new Objeto();
    objeto9.setTitulo("El club de la lucha");
    objeto9.setDescripcion("\"El club de la lucha\" trata sobre un hombre insatisfecho con su vida, que se une a un individuo carismático llamado Tyler Durden para crear un club de lucha clandestino.");
    objeto9.setDuracionMinutos(139);
    objeto9.setTipo(tipoPeliculas);
    objeto9.setImagenUrl("https://www.cinepazmadrid.es/data/fotos/afiche5-elclubdelalucha.jpg");
    objeto9.setTrailerUrl("https://www.youtube.com/embed/iBsiL3NaqhE");
    objeto9.setFechaPublicacion(LocalDate.of(1999, 11, 5));

    objeto9.setGeneros(new HashSet<>(Arrays.asList(generoAccionP, generoSuspenseP, generoDramaP)));

    Objeto objeto10 = new Objeto();
    objeto10.setTitulo("Misión: Imposible - Sentencia Final");
    objeto10.setDescripcion("Ethan Hunt y su equipo deben enfrentar una amenaza tecnológica: una inteligencia artificial (IA) llamada \"La Entidad\", " +
            "que podría tomar el control de las bases nucleares de las principales potencias mundiales. La Entidad, combinada con el peligroso Gabriel, " +
            "amenaza un cataclismo global, obligando a Hunt a encontrar y destruir a La Entidad y detener a Gabriel");
    objeto10.setDuracionMinutos(169);
    objeto10.setTipo(tipoPeliculas);
    objeto10.setImagenUrl("https://pics.filmaffinity.com/Misiaon_imposible_Sentencia_final-784079226-large.jpg");
    objeto10.setTrailerUrl("https://www.youtube.com/embed/YqtdLeJSM6o");
    objeto10.setFechaPublicacion(LocalDate.of(2025, 5, 23));

    objeto10.setGeneros(new HashSet<>(Arrays.asList(generoAccionP, generoAventurasP, generoSuspenseP, generoDramaP)));

    /* CAPITULOS DE SERIES */
    Objeto objeto11 = new Objeto();
    objeto11.setTitulo("The Last Of Us T1. E1 - When You're Lost in the Darkness");
    objeto11.setDescripcion("Este capítulo trata sobre: La vida de Joel da un giro dramático " +
            "en mitad del pánico provocado por eventos apocalípticos.");
    objeto11.setDuracionMinutos(81);
    objeto11.setTipo(tipoSeries);
    objeto11.setImagenUrl("https://www.movistarplus.es/recorte/n/caratula5/F4174243?od[]=Z1V:HBOMAX_V");
    objeto11.setTrailerUrl("https://www.youtube.com/embed/yyGetSp7CIc");
    objeto11.setFechaPublicacion(LocalDate.of(2023, 1, 15));

    objeto11.setGeneros(new HashSet<>(Arrays.asList(generoTerrorS, generoAccionP, generoFiccionP, generoMisterioP)));

    Objeto objeto12 = new Objeto();
    objeto12.setTitulo("The Last Of Us T1. E2 - Infected");
    objeto12.setDescripcion("Joel, Tess y Ellie atraviesan un hotel de Boston abandonado e inundado en su camino " +
            "para dejar a Ellie con un grupo de luciérnagas.");
    objeto12.setDuracionMinutos(53);
    objeto12.setTipo(tipoSeries);
    objeto12.setImagenUrl("https://www.movistarplus.es/recorte/n/caratula5/F4174243?od[]=Z1V:HBOMAX_V");
    objeto12.setTrailerUrl("https://www.youtube.com/embed/yyGetSp7CIc");
    objeto12.setFechaPublicacion(LocalDate.of(2023, 1, 21));

    objeto12.setGeneros(new HashSet<>(Arrays.asList(generoTerrorS, generoAccionP, generoFiccionP, generoMisterioP)));

    Objeto objeto13 = new Objeto();
    objeto13.setTitulo("The Last Of Us T1. E3 - Long, Long Time");
    objeto13.setDescripcion("Cuando una persona desconocida se acerca a su complejo, el superviviente Bill " +
            "forja una conexión poco probable. Más tarde, Joel y Ellie buscan la guía de Bill.");
    objeto13.setDuracionMinutos(76);
    objeto13.setTipo(tipoSeries);
    objeto13.setImagenUrl("https://www.movistarplus.es/recorte/n/caratula5/F4174243?od[]=Z1V:HBOMAX_V");
    objeto13.setTrailerUrl("https://www.youtube.com/embed/yyGetSp7CIc");
    objeto13.setFechaPublicacion(LocalDate.of(2023, 1, 28));

    objeto13.setGeneros(new HashSet<>(Arrays.asList(generoTerrorS, generoAccionP, generoFiccionP, generoMisterioP)));

    Objeto objeto14 = new Objeto();
    objeto14.setTitulo("The Last Of Us T1. E4 - Please Hold to My Hand");
    objeto14.setDescripcion("Tras abandonar su vehículo en Kansas City, Joel y Ellie tratan de escapar sin " +
            "atraer la atención de un grupo rebelde vengativo.");
    objeto14.setDuracionMinutos(46);
    objeto14.setTipo(tipoSeries);
    objeto14.setImagenUrl("https://www.movistarplus.es/recorte/n/caratula5/F4174243?od[]=Z1V:HBOMAX_V");
    objeto14.setTrailerUrl("https://www.youtube.com/embed/yyGetSp7CIc");
    objeto14.setFechaPublicacion(LocalDate.of(2023, 2, 4));

    objeto14.setGeneros(new HashSet<>(Arrays.asList(generoTerrorS, generoAccionP, generoFiccionP, generoMisterioP)));

    Objeto objeto15 = new Objeto();
    objeto15.setTitulo("The Last Of Us T1. E5 - Endure and survive");
    objeto15.setDescripcion("Mientras intentan evadir a los rebeldes, Joel y Ellie se cruzan con el hombre " +
            "más buscado en Kansas City. Kathleen continúa su cacería.");
    objeto15.setDuracionMinutos(60);
    objeto15.setTipo(tipoSeries);
    objeto15.setImagenUrl("https://www.movistarplus.es/recorte/n/caratula5/F4174243?od[]=Z1V:HBOMAX_V");
    objeto15.setTrailerUrl("https://www.youtube.com/embed/yyGetSp7CIc");
    objeto15.setFechaPublicacion(LocalDate.of(2023, 2, 10));

    objeto15.setGeneros(new HashSet<>(Arrays.asList(generoTerrorS, generoAccionP, generoFiccionP, generoMisterioP)));

    Objeto objeto16 = new Objeto();
    objeto16.setTitulo("Breaking Bad T1. E1. - Pilot");
    objeto16.setDescripcion("After being diagnosed with terminal lung cancer, a struggling high school " +
            "chemistry teacher makes a drastic choice to secure his family's future--turning to the" +
            " drug trade with a former student.");
    objeto16.setDuracionMinutos(58);
    objeto16.setTipo(tipoSeries);
    objeto16.setImagenUrl("https://es.web.img2.acsta.net/pictures/18/07/23/11/26/1237965.jpg");
    objeto16.setTrailerUrl("https://www.youtube.com/embed/HhesaQXLuRY");
    objeto16.setFechaPublicacion(LocalDate.of(2008, 1, 20));

    objeto16.setGeneros(new HashSet<>(Arrays.asList(generoDramaS, generoSuspenseP, generoPoliciacaP, generoComediaP)));

    Objeto objeto17 = new Objeto();
    objeto17.setTitulo("Breaking Bad T1. E2. - Cat's in the Bag...");
    objeto17.setDescripcion("Walter and Jesse scramble to deal with the consequences of their first cook, " +
            "leading to a tense and unexpected dilemma. Meanwhile, Skyler grows suspicious of Walter's" +
            " strange behavior.");
    objeto17.setDuracionMinutos(48);
    objeto17.setTipo(tipoSeries);
    objeto17.setImagenUrl("https://es.web.img2.acsta.net/pictures/18/07/23/11/26/1237965.jpg");
    objeto17.setTrailerUrl("https://www.youtube.com/embed/HhesaQXLuRY");
    objeto17.setFechaPublicacion(LocalDate.of(2008, 1, 27));

    objeto17.setGeneros(new HashSet<>(Arrays.asList(generoDramaS, generoSuspenseP, generoPoliciacaP, generoComediaP)));

    Objeto objeto18 = new Objeto();
    objeto18.setTitulo("Breaking Bad T1. E23. - ...And the Bag's in the River");
    objeto18.setDescripcion("Walter is forced to make a life-altering decision as he and Jesse deal with the " +
            "fallout of their actions. Meanwhile, Skyler's suspicions continue to grow, and Hank begins to " +
            "take notice of Walter's unusual behavior.");
    objeto18.setDuracionMinutos(48);
    objeto18.setTipo(tipoSeries);
    objeto18.setImagenUrl("https://es.web.img2.acsta.net/pictures/18/07/23/11/26/1237965.jpg");
    objeto18.setTrailerUrl("https://www.youtube.com/embed/HhesaQXLuRY");
    objeto18.setFechaPublicacion(LocalDate.of(2008, 2, 10));

    objeto18.setGeneros(new HashSet<>(Arrays.asList(generoDramaS, generoSuspenseP, generoPoliciacaP, generoComediaP)));

    /* VIDEOJUEGOS */
    Objeto objeto19 = new Objeto();
    objeto19.setTitulo("Elden Ring: Nightreign");
    objeto19.setDescripcion("Elden Ring Nightreign es un videojuego de rol de acción, soulslike y roguelike, " +
            "desarrollado por FromSoftware y distribuido por Bandai Namco Entertainment. Se reveló en los " +
            "The Game Awards 2024 como un spin-off de Elden Ring.");
    objeto19.setDuracionMinutos(0);
    objeto19.setTipo(tipoVideojuegos);
    objeto19.setImagenUrl("https://i.3djuegos.com/juegos/20132/fotos/ficha/-5938515.webp");
    objeto19.setTrailerUrl("https://www.youtube.com/embed/AWrXpJQBJF0");
    objeto19.setFechaPublicacion(LocalDate.of(2025, 5, 30));

    objeto19.setGeneros(new HashSet<>(Arrays.asList(generoRPGV)));

    Objeto objeto20 = new Objeto();
    objeto20.setTitulo("Clair Obscur: Expedition 33");
    objeto20.setDescripcion("Clair Obscur se desarrolla en un entorno inspirado en la Belle Époque. " +
            "Los protagonistas Gustave (Charlie Cox), Maelle (Jennifer English), Lune (Kirsty Rider), " +
            "Sciel (Shala Nyx) y Verso (Ben Starr) intentan detener a la Pintora, una diosa cuyos poderes " +
            "divinos matan a cada persona de una determinada edad que dibuja en el cielo. En este caso, " +
            "dibujó el número 33. ");
    objeto20.setDuracionMinutos(0);
    objeto20.setTipo(tipoVideojuegos);
    objeto20.setImagenUrl("https://i.3djuegos.com/juegos/19783/clair_obscur_expedition_33/fotos/ficha/clair_obscur_expedition_33-5897212.webp");
    objeto20.setTrailerUrl("https://www.youtube.com/embed/wWGIakhqr5g");
    objeto20.setFechaPublicacion(LocalDate.of(2025, 4, 24));

    objeto20.setGeneros(new HashSet<>(Arrays.asList(generoRPGV, generoTBSV, generoAventuraV)));

    Objeto objeto21 = new Objeto();
    objeto21.setTitulo("Assassin's Creed Shadows");
    objeto21.setDescripcion("Ambientado en el Japón del siglo XVI hacia el final del período Sengoku," +
            " el juego se centra en la lucha milenaria entre la Hermandad de Asesinos, que lucha por la paz " +
            "y la libertad, y la Orden de los Templarios, que desea la paz a través del control, desde la " +
            "perspectiva de dos protagonistas: Fujibayashi Naoe, una kunoichi (una shinobi femenina), y Yasuke, " +
            "un samurái africano inspirado en la figura histórica del mismo nombre. Los dos personajes tienen " +
            "un estilo de juego diferente, lo que permite abordar las misiones de múltiples maneras. ");
    objeto21.setDuracionMinutos(0);
    objeto21.setTipo(tipoVideojuegos);
    objeto21.setImagenUrl("https://i.3djuegos.com/juegos/18752/assassin__039_s_creed_codename_red/fotos/ficha/assassin__039_s_creed_codename_red-5904359.jpg");
    objeto21.setTrailerUrl("https://www.youtube.com/embed/vovkzbtYBC8");
    objeto21.setFechaPublicacion(LocalDate.of(2025, 3, 20));

    objeto21.setGeneros(new HashSet<>(Arrays.asList(generoRPGV, generoAventuraV)));

    Objeto objeto22 = new Objeto();
    objeto22.setTitulo("The Legend of Zelda: Tears of the Kingdom");
    objeto22.setDescripcion("Tras los acontecimientos de The Legend of Zelda: Breath of the Wild, " +
            "la princesa Zelda junto a Link exploran los cimientos del castillo de Hyrule, en el que " +
            "encuentran una sala donde reposa un cadáver momificado. Después de que el cadáver tome vida " +
            "y se levante, ataca a Zelda y Link, dejando a este último malherido y rompiendo el filo de su " +
            "Espada Maestra. Tras ello desata su poder rompiendo el sello que lo mantenía retenido y se eleva " +
            "el castillo de Hyrule. Zelda sufre una caída mientras el sitio se derrumba y desaparece " +
            "mágicamente. Link deberá encontrar a la princesa Zelda por cielo, tierra y subsuelo y " +
            "así salvar al reino de Hyrule del resurgido Ganondorf, el rey demonio.");
    objeto22.setDuracionMinutos(0);
    objeto22.setTipo(tipoVideojuegos);
    objeto22.setImagenUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQoeTQgBfKZev-V2Z2chfc604vTI2hxHRv57g&s");
    objeto22.setTrailerUrl("https://www.youtube.com/embed/gp9aY09li1s");
    objeto22.setFechaPublicacion(LocalDate.of(2023, 5, 12));

    objeto22.setGeneros(new HashSet<>(Arrays.asList(generoRPGV, generoAventuraV)));

    Objeto objeto23 = new Objeto();
    objeto23.setTitulo("Super Mario World");
    objeto23.setDescripcion("Después de salvar el Reino Champiñón en Super Mario Bros. 3, los hermanos" +
            " Mario y Luigi acuerdan irse de vacaciones con la Princesa Toadstool a un lugar llamado" +
            " «Dinosaur Land», un mundo de temática prehistórica donde habitan diversos tipos de dinosaurios" +
            " y otros enemigos. Mientras descansaban en la playa, la princesa es capturada por Bowser. " +
            "Cuando ambos se despiertan, ellos intentan encontrarla, y después de horas de búsqueda, " +
            "se topan con un huevo gigante que yacía en un bosque. De repente, este eclosiona y de él sale " +
            "un joven dinosaurio llamado Yoshi, quien les dice que han encerrados a sus amigos dentro de" +
            " embriones por los hijos de Bowser, los malvados «Koopalings»");
    objeto23.setDuracionMinutos(0);
    objeto23.setTipo(tipoVideojuegos);
    objeto23.setImagenUrl("https://m.media-amazon.com/images/M/MV5BZjZkZWVjNTYtZDMzZC00N2YxLTg2NjUtMTdlMzJiMDk3MTNhXkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg");
    objeto23.setTrailerUrl("https://www.youtube.com/embed/-WpgCFSLtLo");
    objeto23.setFechaPublicacion(LocalDate.of(1990, 11, 21));

    objeto23.setGeneros(new HashSet<>(Arrays.asList(generoPlataformasV)));

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

    objetoRepository.saveAll(Arrays.asList(objeto, objeto2, objeto3));

    objetoRepository.saveAll(Arrays.asList(objeto, objeto2, objeto3, objeto4, objeto5, objeto6, objeto7,
            objeto8, objeto9, objeto10, objeto11, objeto12, objeto13, objeto14, objeto15, objeto16,
            objeto17, objeto18, objeto19, objeto20, objeto21, objeto22, objeto23));

    personaObjetoRepository.saveAll(Arrays.asList(personaObjeto1, personaObjeto2, personaObjeto3));



    // Usuarios de prueba
    Usuario admin = new Usuario();
    admin.setNombreUsuario("admin");
    admin.setEmail("admin@admin.com");
    admin.setContrasena(encoder.encode("admin"));
    admin.setRole("ADMIN");

    Usuario usuario1 = new Usuario();
    usuario1.setNombreUsuario("Usuario1");
    usuario1.setEmail("hola@gmail.com");
    usuario1.setContrasena("1234");

    usuario1.setBiografia("Hater desde 1997");
    usuario1.setRole("ADMIN");

    Set<Genero> generosFavoritos = new HashSet<>();
    generosFavoritos.add(generoAccionS);
    generosFavoritos.add(generoAnimeS);
    generosFavoritos.add(generoMisterioS);


    usuario1.setRole("USER");


    generosFavoritos.add(generoDramaP);
    generosFavoritos.add(generoRomanceP);
    generosFavoritos.add(generoComediaP);

    generosFavoritos.add(generoRPGV);
    generosFavoritos.add(generoBattleRoyaleV);
    generosFavoritos.add(generoPlataformasV);

    usuario1.setGeneros(generosFavoritos);

    /*
     * contenedor-datos-ficha-perfil mx-auto rounded p-3
     *  shadow text-center d-flex flex-column justify-content-center
     * align-items-center
     *
     *
     * */
    Usuario usuario2 = new Usuario();
    usuario2.setNombreUsuario("Usuario2");
    usuario2.setEmail("adios@gmail.com");
    usuario2.setContrasena("4321");
    usuario2.setRole("USER");


    Usuario usuario3 = new Usuario();
    usuario3.setNombreUsuario("Usuario3");
    usuario3.setEmail("odijajoaspco@gmail.es");
    usuario3.setContrasena("4313213213232132");
    usuario3.setRole("ADMIN");

    Usuario usuario4 = new Usuario();
    usuario4.setNombreUsuario("OpelCorsa99");
    usuario4.setEmail("corsita@forocoches.com");
    usuario4.setContrasena("opelpower");
    usuario4.setRole("USER");

    Usuario usuario5 = new Usuario();
    usuario5.setNombreUsuario("DNIroto");
    usuario5.setEmail("sinpapeles@forochapa.net");
    usuario5.setContrasena("cuñadismo123");
    usuario5.setRole("USER");

    Usuario usuario6 = new Usuario();
    usuario6.setNombreUsuario("Turbodiesel92");
    usuario6.setEmail("motorz@torquemax.org");
    usuario6.setContrasena("torque+ps");
    usuario6.setRole("USER");

    Usuario usuario7 = new Usuario();
    usuario7.setNombreUsuario("Forero69");
    usuario7.setEmail("estoydentro@foro.com");
    usuario7.setContrasena("s3cret0");
    usuario7.setRole("USER");

    Usuario usuario8 = new Usuario();
    usuario8.setNombreUsuario("AntiMultas3000");
    usuario8.setEmail("legalhacks@caminoalbania.ru");
    usuario8.setContrasena("radardetect");
    usuario8.setRole("USER");

    Usuario usuario9 = new Usuario();
    usuario9.setNombreUsuario("PikachuEnZanini");
    usuario9.setEmail("pokemon@electrotuneo.com");
    usuario9.setContrasena("pika-pika");
    usuario9.setRole("USER");

    Usuario usuario10 = new Usuario();
    usuario10.setNombreUsuario("JoseAntonio88");
    usuario10.setEmail("falcon@españa.es");
    usuario10.setContrasena("vivaespaña");
    usuario10.setRole("USER");

    Usuario usuario11 = new Usuario();
    usuario11.setNombreUsuario("MiVecinoMataGatos");
    usuario11.setEmail("denuncia@callejonoscuro.com");
    usuario11.setContrasena("michisno");
    usuario11.setRole("USER");

    Usuario usuario12 = new Usuario();
    usuario12.setNombreUsuario("Tronchamozas");
    usuario12.setEmail("ligon@foropub.com");
    usuario12.setContrasena(encoder.encode("musculitos"));
    usuario12.setRole("USER");

    Usuario usuario13 = new Usuario();
    usuario13.setNombreUsuario("CanelitaEnMoto");
    usuario13.setEmail("flamenco@dosruedas.com");
    usuario13.setContrasena(encoder.encode("ole1234"));
    usuario13.setRole("USER");

    Usuario usuario14 = new Usuario();
    usuario14.setNombreUsuario("BocachanclaPro");
    usuario14.setEmail("chismes@lavozdelbarrio.org");
    usuario14.setContrasena(encoder.encode("cotilleoON"));
    usuario14.setRole("USER");

    Usuario usuario15 = new Usuario();
    usuario15.setNombreUsuario("Mecanicor");
    usuario15.setEmail("grasa@talleresmanolo.com");
    usuario15.setContrasena(encoder.encode("aceite5w30"));
    usuario15.setRole("USER");

    Usuario usuario16 = new Usuario();
    usuario16.setNombreUsuario("Ibizilla_1.9TDI");
    usuario16.setEmail("seatpower@vaggroup.org");
    usuario16.setContrasena(encoder.encode("smokeyboi"));
    usuario16.setRole("USER");

    Usuario usuario17 = new Usuario();
    usuario17.setNombreUsuario("PaKeKieresSaberEso");
    usuario17.setEmail("respuestaobvia@forocuñado.com");
    usuario17.setContrasena(encoder.encode("nopreguntes"));
    usuario17.setRole("USER");

    Usuario usuario18 = new Usuario();
    usuario18.setNombreUsuario("DamePermisoAdmin");
    usuario18.setEmail("banme@modmail.com");
    usuario18.setContrasena(encoder.encode("permabanpls"));
    usuario18.setRole("USER");

    Usuario usuario19 = new Usuario();
    usuario19.setNombreUsuario("KebabDeRes");
    usuario19.setEmail("nocheloca@4AM.com");
    usuario19.setContrasena(encoder.encode("ajiquemepica"));
    usuario19.setRole("USER");

    Usuario usuario20 = new Usuario();
    usuario20.setNombreUsuario("TorettoDelPoli");
    usuario20.setEmail("familia@racinglife.com");
    usuario20.setContrasena(encoder.encode("fastnfurious"));
    usuario20.setRole("USER");


    ObjetoUsuario objetoUsuario1 = new ObjetoUsuario();
    objetoUsuario1.setEstado(true);
    objetoUsuario1.setUsuario(usuario1);
    objetoUsuario1.setFavorito(true);
    objetoUsuario1.setObjeto(objeto23);

    ObjetoUsuario objetoUsuario2 = new ObjetoUsuario();
    objetoUsuario2.setEstado(true);
    objetoUsuario2.setUsuario(usuario1);
    objetoUsuario2.setFavorito(true);
    objetoUsuario2.setObjeto(objeto22);

    ObjetoUsuario objetoUsuario3 = new ObjetoUsuario();
    objetoUsuario3.setEstado(true);
    objetoUsuario3.setUsuario(usuario1);
    objetoUsuario3.setFavorito(true);
    objetoUsuario3.setObjeto(objeto21);

    ObjetoUsuario objetoUsuario4 = new ObjetoUsuario();
    objetoUsuario4.setEstado(true);
    objetoUsuario4.setUsuario(usuario1);
    objetoUsuario4.setFavorito(true);
    objetoUsuario4.setObjeto(objeto20);

    ObjetoUsuario objetoUsuario5 = new ObjetoUsuario();
    objetoUsuario5.setEstado(true);
    objetoUsuario5.setUsuario(usuario1);
    objetoUsuario5.setFavorito(true);
    objetoUsuario5.setObjeto(objeto19);


    usuarioRepository.saveAll(Arrays.asList(admin,
            usuario1, usuario2, usuario3, usuario4, usuario5,
            usuario6, usuario7, usuario8, usuario9, usuario10,
            usuario11, usuario12, usuario13, usuario14, usuario15,
            usuario16, usuario17, usuario18, usuario19, usuario20
    ));

    objetoUsuarioRepository.saveAll(Arrays.asList(objetoUsuario1, objetoUsuario2, objetoUsuario3, objetoUsuario4, objetoUsuario5));



    // Reseñas de prueba
    Resena resena1 = new Resena();
    resena1.setTitulo("La peor película de mi vida");
    resena1.setContenido("Pese a su enorme popularidad, Harry Potter y la piedra filosofal resulta ser una experiencia cinematográfica " +
            "decepcionante si se mira más allá del brillo superficial. Dirigida por Chris Columbus, la película se siente como un desfile " +
            "mecánico de escenas del libro, sin la profundidad emocional o narrativa que una adaptación merecería." +
            "La actuación de los niños, aunque comprensible por su edad, es en gran parte rígida y poco convincente, especialmente " +
            "en momentos dramáticos donde se nota la falta de naturalidad. Daniel Radcliffe apenas transmite el conflicto interno de Harry, " +
            "y los personajes secundarios como Ron y Hermione rozan el cliché más que la complejidad." +
            " Visualmente, la película se apoya en efectos especiales que envejecieron mal, y el tono excesivamente edulcorado rebaja el peligro o " +
            "la tensión que debería haber en una historia que, en teoría, habla de magia, muerte y descubrimiento personal. El ritmo es irregular, y el guion " +
            "se limita a ser una lista de chequeo de eventos del libro, sin tomar riesgos ni ofrecer un lenguaje cinematográfico propio." +
            "En resumen, más que una película mágica, La piedra filosofal parece un producto de marketing cuidadosamente envuelto para agradar, " +
            "pero vacío de la chispa creativa que hace que una historia cobre vida en la pantalla.");
    resena1.setPuntuacion(1.0);
    resena1.setSpoiler(false);
    resena1.setUsuario(usuario1);
    resena1.setObjeto(objeto);
    resena1.setAbuso(true);

    ComentarioResena comentarioResena1 = new ComentarioResena();
    comentarioResena1.setResena(resena1);
    comentarioResena1.setUsuario(usuario2);
    comentarioResena1.setContenido("Totalmente de acuerdo");
    comentarioResena1.setAbuso(true);
    comentarioResena1.setFecha(LocalDateTime.now());

    ComentarioResena comentarioResena2 = new ComentarioResena();
    comentarioResena2.setResena(resena1);
    comentarioResena2.setUsuario(usuario3);
    comentarioResena2.setContenido("Me ha gustao mucho tu reseña, mi pana. Ánimo con lo tuyo.");
    comentarioResena2.setAbuso(true);
    comentarioResena2.setFecha(LocalDateTime.now());

    resena1.setComentariosResena(Arrays.asList(comentarioResena1, comentarioResena2));

    Resena resena2 = new Resena();
    resena2.setTitulo("La mejor película de mi vida");
    resena2.setContenido("Me ha cambiado la vida hasta tal punto que ahora cuando veo una escoba me subo encima, creo que puedo volar con ella como en la peli," +
            " lo mismo mañana salto desde el balcón para probar (vivo en un 8º)");
    resena2.setPuntuacion(3.5);
    resena2.setSpoiler(false);
    resena2.setUsuario(usuario2);
    resena2.setObjeto(objeto);
    resena2.setAbuso(true);

    Resena resena3 = new Resena();
    resena3.setTitulo("Ha estado muy bien.");
    resena3.setContenido("Muy disfrutable, me ha encantado. Sobretodo la escena donde el personaje principal consigue encontrar...");
    resena3.setPuntuacion(9.0);
    resena3.setSpoiler(true);
    resena3.setUsuario(usuario1);
    resena3.setObjeto(objeto);


    resenaRepository.saveAll(Arrays.asList(resena1, resena2, resena3));

    comentarioResenaRepository.saveAll(Arrays.asList(comentarioResena1, comentarioResena2));

    // Datos de prueba para las publicaciones, comunidades y comentarios


    //Publicaciones de prueba

    //Publicaciones en la comunidad de Harry Potter

    //Publicacion 1

    Publicacion publicacion1 = new Publicacion();
    publicacion1.setTitulo("Recomendaciones para ver la última película de harry potter");

    //Comentarios de la publicacion 1:

    ComentarioPublicacion comentarioPublicacion1 = new ComentarioPublicacion();
    comentarioPublicacion1.setPublicacion(publicacion1);
    comentarioPublicacion1.setUsuario(usuario1);
    comentarioPublicacion1.setContenido("Pues eso, a parte de ponerme el pijama y tumbarme en la cama para estar preparado para la peli, " +
            "me recomendais algo más??");
    comentarioPublicacion1.setFecha(LocalDateTime.of(2025, 5, 25, 16, 32, 28));

    ComentarioPublicacion comentarioPublicacion2 = new ComentarioPublicacion();
    comentarioPublicacion2.setPublicacion(publicacion1);
    comentarioPublicacion2.setUsuario(usuario2);
    comentarioPublicacion2.setContenido("Tómate una dormidina");
    comentarioPublicacion2.setFecha(LocalDateTime.of(2025, 5, 25, 16, 39, 14));
    comentarioPublicacion2.setAbuso(true);

    ComentarioPublicacion comentarioPublicacion3 = new ComentarioPublicacion();
    comentarioPublicacion3.setPublicacion(publicacion1);
    comentarioPublicacion3.setUsuario(usuario3);
    comentarioPublicacion3.setContenido("Pon la peli con la tele apagada");
    comentarioPublicacion3.setFecha(LocalDateTime.of(2025, 5, 25, 16, 48, 54));

    publicacion1.setComentariosPublicacion(Arrays.asList(comentarioPublicacion1, comentarioPublicacion2, comentarioPublicacion3));

    //--------------------------------------------------------------

    Publicacion publicacion2 = new Publicacion();
    publicacion2.setTitulo("Vendo Opel Corsa 250mil KM como nuevo");

    //Comentarios de la publicacion 2:

    ComentarioPublicacion comentarioPublicacion4 = new ComentarioPublicacion();
    comentarioPublicacion4.setPublicacion(publicacion2);
    comentarioPublicacion4.setUsuario(usuario2);
    comentarioPublicacion4.setContenido("Pues eso, escuho ofertas");
    comentarioPublicacion4.setFecha(LocalDateTime.of(2025, 5, 26, 18, 36, 32));

    ComentarioPublicacion comentarioPublicacion5 = new ComentarioPublicacion();
    comentarioPublicacion5.setPublicacion(publicacion2);
    comentarioPublicacion5.setUsuario(usuario1);
    comentarioPublicacion5.setContenido("Mi oferta es que lo lleves al desguace");
    comentarioPublicacion5.setFecha(LocalDateTime.of(2025, 5, 26, 18, 48, 41));
    comentarioPublicacion5.setAbuso(true);

    ComentarioPublicacion comentarioPublicacion6 = new ComentarioPublicacion();
    comentarioPublicacion6.setPublicacion(publicacion2);
    comentarioPublicacion6.setUsuario(usuario3);
    comentarioPublicacion6.setContenido("Te lo cambio por mi seat ibiza del 97 con 300k Km");
    comentarioPublicacion6.setFecha(LocalDateTime.of(2025, 5, 26, 19, 2, 12));

    publicacion2.setComentariosPublicacion(Arrays.asList(comentarioPublicacion4, comentarioPublicacion5, comentarioPublicacion6));

    //--------------------------------------------------------------

    Publicacion publicacion3 = new Publicacion();
    publicacion3.setTitulo("Ron estaba de relleno, no pinta nada en las pelis");

    //Comentarios de la publicacion 3:

    ComentarioPublicacion comentarioPublicacion7 = new ComentarioPublicacion();
    comentarioPublicacion7.setPublicacion(publicacion3);
    comentarioPublicacion7.setUsuario(usuario3);
    comentarioPublicacion7.setContenido("Cojo palomitas");
    comentarioPublicacion7.setFecha(LocalDateTime.of(2025, 5, 27, 13, 34, 51));

    ComentarioPublicacion comentarioPublicacion8 = new ComentarioPublicacion();
    comentarioPublicacion8.setPublicacion(publicacion3);
    comentarioPublicacion8.setUsuario(usuario2);
    comentarioPublicacion8.setContenido("Al zanahorio que le den por culo jajajajaja");
    comentarioPublicacion8.setFecha(LocalDateTime.of(2025, 5, 27, 14, 2, 23));

    ComentarioPublicacion comentarioPublicacion9 = new ComentarioPublicacion();
    comentarioPublicacion9.setPublicacion(publicacion3);
    comentarioPublicacion9.setUsuario(usuario1);
    comentarioPublicacion9.setContenido("Pero qué dices?? Sin el no hay peli");
    comentarioPublicacion9.setFecha(LocalDateTime.of(2025, 5, 27, 14, 31, 48));

    publicacion3.setComentariosPublicacion(Arrays.asList(comentarioPublicacion7, comentarioPublicacion8, comentarioPublicacion9));

    //--------------------------------------------------------------

    Publicacion publicacion4 = new Publicacion();
    publicacion4.setTitulo("Mi perro se parece a Dobby");

    //Comentarios de la publicacion 4:

    ComentarioPublicacion comentarioPublicacion10 = new ComentarioPublicacion();
    comentarioPublicacion10.setPublicacion(publicacion4);
    comentarioPublicacion10.setUsuario(usuario5);
    comentarioPublicacion10.setContenido("Es igual de feo");
    comentarioPublicacion10.setFecha(LocalDateTime.of(2025, 5, 27, 15, 12, 10));

    ComentarioPublicacion comentarioPublicacion11 = new ComentarioPublicacion();
    comentarioPublicacion11.setPublicacion(publicacion4);
    comentarioPublicacion11.setUsuario(usuario8);
    comentarioPublicacion11.setContenido("Sube foto o fake");
    comentarioPublicacion11.setFecha(LocalDateTime.of(2025, 5, 27, 15, 15, 47));

    ComentarioPublicacion comentarioPublicacion12 = new ComentarioPublicacion();
    comentarioPublicacion12.setPublicacion(publicacion4);
    comentarioPublicacion12.setUsuario(usuario4);
    comentarioPublicacion12.setContenido("JAJAJAJA mi gato parece Voldemort");
    comentarioPublicacion12.setFecha(LocalDateTime.of(2025, 5, 27, 15, 20, 5));

    ComentarioPublicacion comentarioPublicacion13 = new ComentarioPublicacion();
    comentarioPublicacion13.setPublicacion(publicacion4);
    comentarioPublicacion13.setUsuario(usuario6);
    comentarioPublicacion13.setContenido("Dobby libre, pero tu perro tiene cara de preso");
    comentarioPublicacion13.setFecha(LocalDateTime.of(2025, 5, 27, 15, 23, 33));

    ComentarioPublicacion comentarioPublicacion14 = new ComentarioPublicacion();
    comentarioPublicacion14.setPublicacion(publicacion4);
    comentarioPublicacion14.setUsuario(usuario7);
    comentarioPublicacion14.setContenido("Denunciado por maltrato psicológico a Dobby");
    comentarioPublicacion14.setFecha(LocalDateTime.of(2025, 5, 27, 15, 28, 14));

    ComentarioPublicacion comentarioPublicacion15 = new ComentarioPublicacion();
    comentarioPublicacion15.setPublicacion(publicacion4);
    comentarioPublicacion15.setUsuario(usuario9);
    comentarioPublicacion15.setContenido("Lo importante: ¿le das calcetines?");
    comentarioPublicacion15.setFecha(LocalDateTime.of(2025, 5, 27, 15, 32, 45));

    ComentarioPublicacion comentarioPublicacion16 = new ComentarioPublicacion();
    comentarioPublicacion16.setPublicacion(publicacion4);
    comentarioPublicacion16.setUsuario(usuario10);
    comentarioPublicacion16.setContenido("Dale una capa y llévalo a Hogwarts ya");
    comentarioPublicacion16.setFecha(LocalDateTime.of(2025, 5, 27, 15, 35, 51));

    publicacion4.setComentariosPublicacion(Arrays.asList(
            comentarioPublicacion10,
            comentarioPublicacion11,
            comentarioPublicacion12,
            comentarioPublicacion13,
            comentarioPublicacion14,
            comentarioPublicacion15,
            comentarioPublicacion16
    ));

    //--------------------------------------------------------------

    Publicacion publicacion5 = new Publicacion();
    publicacion5.setTitulo("¿Alguien entiende por qué Voldemort no usaba una pistola?");

    //Comentarios de la publicacion 5:

    ComentarioPublicacion comentarioPublicacion17 = new ComentarioPublicacion();
    comentarioPublicacion17.setPublicacion(publicacion5);
    comentarioPublicacion17.setUsuario(usuario11);
    comentarioPublicacion17.setContenido("Un AK-47 y se acababa Harry Potter en el primer libro.");
    comentarioPublicacion17.setFecha(LocalDateTime.of(2025, 5, 28, 11, 10, 5));

    ComentarioPublicacion comentarioPublicacion18 = new ComentarioPublicacion();
    comentarioPublicacion18.setPublicacion(publicacion5);
    comentarioPublicacion18.setUsuario(usuario12);
    comentarioPublicacion18.setContenido("Imagínatelo sacando la Glock de la túnica JAJA");
    comentarioPublicacion18.setFecha(LocalDateTime.of(2025, 5, 28, 11, 14, 22));

    ComentarioPublicacion comentarioPublicacion19 = new ComentarioPublicacion();
    comentarioPublicacion19.setPublicacion(publicacion5);
    comentarioPublicacion19.setUsuario(usuario13);
    comentarioPublicacion19.setContenido("Porque el .45 no mata horrocruxes");
    comentarioPublicacion19.setFecha(LocalDateTime.of(2025, 5, 28, 11, 19, 38));

    ComentarioPublicacion comentarioPublicacion20 = new ComentarioPublicacion();
    comentarioPublicacion20.setPublicacion(publicacion5);
    comentarioPublicacion20.setUsuario(usuario14);
    comentarioPublicacion20.setContenido("Porque no tenía licencia de armas, bro");
    comentarioPublicacion20.setFecha(LocalDateTime.of(2025, 5, 28, 11, 22, 9));

    ComentarioPublicacion comentarioPublicacion21 = new ComentarioPublicacion();
    comentarioPublicacion21.setPublicacion(publicacion5);
    comentarioPublicacion21.setUsuario(usuario15);
    comentarioPublicacion21.setContenido("Harry sería 'el niño que esquivó balas'");
    comentarioPublicacion21.setFecha(LocalDateTime.of(2025, 5, 28, 11, 24, 44));

    ComentarioPublicacion comentarioPublicacion22 = new ComentarioPublicacion();
    comentarioPublicacion22.setPublicacion(publicacion5);
    comentarioPublicacion22.setUsuario(usuario16);
    comentarioPublicacion22.setContenido("Expelliarmus no funciona contra balas");
    comentarioPublicacion22.setFecha(LocalDateTime.of(2025, 5, 28, 11, 27, 16));

    ComentarioPublicacion comentarioPublicacion23 = new ComentarioPublicacion();
    comentarioPublicacion23.setPublicacion(publicacion5);
    comentarioPublicacion23.setUsuario(usuario17);
    comentarioPublicacion23.setContenido("Los muggles lo tenían todo pensado");
    comentarioPublicacion23.setFecha(LocalDateTime.of(2025, 5, 28, 11, 30, 59));

    publicacion5.setComentariosPublicacion(Arrays.asList(
            comentarioPublicacion17,
            comentarioPublicacion18,
            comentarioPublicacion19,
            comentarioPublicacion20,
            comentarioPublicacion21,
            comentarioPublicacion22,
            comentarioPublicacion23
    ));

    //--------------------------------------------------------------

    Publicacion publicacion6 = new Publicacion();
    publicacion6.setTitulo("¿Qué casa de Hogwarts es la más chusta?");

    //Comentarios de la publicacion 6:

    ComentarioPublicacion comentarioPublicacion24 = new ComentarioPublicacion();
    comentarioPublicacion24.setPublicacion(publicacion6);
    comentarioPublicacion24.setUsuario(usuario7);
    comentarioPublicacion24.setContenido("Hufflepuff es literalmente la casa de relleno");
    comentarioPublicacion24.setFecha(LocalDateTime.of(2025, 5, 28, 13, 10, 0));

    ComentarioPublicacion comentarioPublicacion25 = new ComentarioPublicacion();
    comentarioPublicacion25.setPublicacion(publicacion6);
    comentarioPublicacion25.setUsuario(usuario12);
    comentarioPublicacion25.setContenido("Ravenclaw y su elitismo random, meh");
    comentarioPublicacion25.setFecha(LocalDateTime.of(2025, 5, 28, 13, 12, 45));

    ComentarioPublicacion comentarioPublicacion26 = new ComentarioPublicacion();
    comentarioPublicacion26.setPublicacion(publicacion6);
    comentarioPublicacion26.setUsuario(usuario3);
    comentarioPublicacion26.setContenido("Todos querían ser Gryffindor hasta que salieron los cringe");
    comentarioPublicacion26.setFecha(LocalDateTime.of(2025, 5, 28, 13, 15, 12));

    ComentarioPublicacion comentarioPublicacion27 = new ComentarioPublicacion();
    comentarioPublicacion27.setPublicacion(publicacion6);
    comentarioPublicacion27.setUsuario(usuario18);
    comentarioPublicacion27.setContenido("Yo soy Slytherin pero no soy mala persona, lo juro");
    comentarioPublicacion27.setFecha(LocalDateTime.of(2025, 5, 28, 13, 17, 8));

    ComentarioPublicacion comentarioPublicacion28 = new ComentarioPublicacion();
    comentarioPublicacion28.setPublicacion(publicacion6);
    comentarioPublicacion28.setUsuario(usuario2);
    comentarioPublicacion28.setContenido("Hufflepuff suena a marca de cereales rancios");
    comentarioPublicacion28.setFecha(LocalDateTime.of(2025, 5, 28, 13, 20, 33));

    ComentarioPublicacion comentarioPublicacion29 = new ComentarioPublicacion();
    comentarioPublicacion29.setPublicacion(publicacion6);
    comentarioPublicacion29.setUsuario(usuario15);
    comentarioPublicacion29.setContenido("Gryffindor = casa de flipados. Lo tenía que decir");
    comentarioPublicacion29.setFecha(LocalDateTime.of(2025, 5, 28, 13, 23, 17));

    ComentarioPublicacion comentarioPublicacion30 = new ComentarioPublicacion();
    comentarioPublicacion30.setPublicacion(publicacion6);
    comentarioPublicacion30.setUsuario(usuario5);
    comentarioPublicacion30.setContenido("En Hufflepuff te meten si no saben dónde meterte");
    comentarioPublicacion30.setFecha(LocalDateTime.of(2025, 5, 28, 13, 26, 40));

    ComentarioPublicacion comentarioPublicacion31 = new ComentarioPublicacion();
    comentarioPublicacion31.setPublicacion(publicacion6);
    comentarioPublicacion31.setUsuario(usuario20);
    comentarioPublicacion31.setContenido("Ravenclaw es solo para los que no ligan ni con poción de amor");
    comentarioPublicacion31.setFecha(LocalDateTime.of(2025, 5, 28, 13, 29, 55));

    ComentarioPublicacion comentarioPublicacion32 = new ComentarioPublicacion();
    comentarioPublicacion32.setPublicacion(publicacion6);
    comentarioPublicacion32.setUsuario(usuario9);
    comentarioPublicacion32.setContenido("Slytherin ganaría Eurovisión, fijo");
    comentarioPublicacion32.setFecha(LocalDateTime.of(2025, 5, 28, 13, 31, 29));

    ComentarioPublicacion comentarioPublicacion33 = new ComentarioPublicacion();
    comentarioPublicacion33.setPublicacion(publicacion6);
    comentarioPublicacion33.setUsuario(usuario14);
    comentarioPublicacion33.setContenido("Los de Gryffindor viven del cuento de Harry, nada más");
    comentarioPublicacion33.setFecha(LocalDateTime.of(2025, 5, 28, 13, 34, 10));

    ComentarioPublicacion comentarioPublicacion34 = new ComentarioPublicacion();
    comentarioPublicacion34.setPublicacion(publicacion6);
    comentarioPublicacion34.setUsuario(usuario6);
    comentarioPublicacion34.setContenido("Yo estuve en Hufflepuff... y aún no sé por qué");
    comentarioPublicacion34.setFecha(LocalDateTime.of(2025, 5, 28, 13, 37, 5));

    ComentarioPublicacion comentarioPublicacion35 = new ComentarioPublicacion();
    comentarioPublicacion35.setPublicacion(publicacion6);
    comentarioPublicacion35.setUsuario(usuario11);
    comentarioPublicacion35.setContenido("Ravenclaw son empollones sin alma, lo sabéis");
    comentarioPublicacion35.setFecha(LocalDateTime.of(2025, 5, 28, 13, 40, 22));

    ComentarioPublicacion comentarioPublicacion36 = new ComentarioPublicacion();
    comentarioPublicacion36.setPublicacion(publicacion6);
    comentarioPublicacion36.setUsuario(usuario17);
    comentarioPublicacion36.setContenido("Hufflepuff tiene nombre de Pokémon, no me jodáis");
    comentarioPublicacion36.setFecha(LocalDateTime.of(2025, 5, 28, 13, 43, 59));

    publicacion6.setComentariosPublicacion(Arrays.asList(
            comentarioPublicacion24,
            comentarioPublicacion25,
            comentarioPublicacion26,
            comentarioPublicacion27,
            comentarioPublicacion28,
            comentarioPublicacion29,
            comentarioPublicacion30,
            comentarioPublicacion31,
            comentarioPublicacion32,
            comentarioPublicacion33,
            comentarioPublicacion34,
            comentarioPublicacion35,
            comentarioPublicacion36
    ));

    //--------------------------------------------------------------

    Publicacion publicacion7 = new Publicacion();
    publicacion7.setTitulo("¿Por qué los videojuegos antiguos de Harry Potter molaban más?");

    //Comentarios de la publicacion 7:

    ComentarioPublicacion comentarioPublicacion37 = new ComentarioPublicacion();
    comentarioPublicacion37.setPublicacion(publicacion7);
    comentarioPublicacion37.setUsuario(usuario4);
    comentarioPublicacion37.setContenido("Molaba cuando en el minijuego de tirar elfos-bomba decían Mbappé todo el rato");
    comentarioPublicacion37.setFecha(LocalDateTime.of(2025, 5, 28, 16, 2, 12));

    ComentarioPublicacion comentarioPublicacion38 = new ComentarioPublicacion();
    comentarioPublicacion38.setPublicacion(publicacion7);
    comentarioPublicacion38.setUsuario(usuario16);
    comentarioPublicacion38.setContenido("Nada como lanzar Flipendo en bucle al pobre Neville.");
    comentarioPublicacion38.setFecha(LocalDateTime.of(2025, 5, 28, 16, 5, 33));

    ComentarioPublicacion comentarioPublicacion39 = new ComentarioPublicacion();
    comentarioPublicacion39.setPublicacion(publicacion7);
    comentarioPublicacion39.setUsuario(usuario9);
    comentarioPublicacion39.setContenido("El de PS1 era tan feo que daba miedo, arte puro.");
    comentarioPublicacion39.setFecha(LocalDateTime.of(2025, 5, 28, 16, 8, 57));

    ComentarioPublicacion comentarioPublicacion40 = new ComentarioPublicacion();
    comentarioPublicacion40.setPublicacion(publicacion7);
    comentarioPublicacion40.setUsuario(usuario1);
    comentarioPublicacion40.setContenido("Porque eran cutres pero con alma, como tu ex.");
    comentarioPublicacion40.setFecha(LocalDateTime.of(2025, 5, 28, 16, 12, 21));

    ComentarioPublicacion comentarioPublicacion41 = new ComentarioPublicacion();
    comentarioPublicacion41.setPublicacion(publicacion7);
    comentarioPublicacion41.setUsuario(usuario20);
    comentarioPublicacion41.setContenido("Porque ahora todo es mundo abierto y microtransacciones.");
    comentarioPublicacion41.setFecha(LocalDateTime.of(2025, 5, 28, 16, 15, 11));

    ComentarioPublicacion comentarioPublicacion42 = new ComentarioPublicacion();
    comentarioPublicacion42.setPublicacion(publicacion7);
    comentarioPublicacion42.setUsuario(usuario11);
    comentarioPublicacion42.setContenido("¿Alguien más se perdía en Hogwarts en el de PC? Yo aún estoy atrapado.");
    comentarioPublicacion42.setFecha(LocalDateTime.of(2025, 5, 28, 16, 17, 42));

    ComentarioPublicacion comentarioPublicacion43 = new ComentarioPublicacion();
    comentarioPublicacion43.setPublicacion(publicacion7);
    comentarioPublicacion43.setUsuario(usuario6);
    comentarioPublicacion43.setContenido("Flipendo tenía más peso argumental que Voldemort.");
    comentarioPublicacion43.setFecha(LocalDateTime.of(2025, 5, 28, 16, 20, 31));

    ComentarioPublicacion comentarioPublicacion44 = new ComentarioPublicacion();
    comentarioPublicacion44.setPublicacion(publicacion7);
    comentarioPublicacion44.setUsuario(usuario13);
    comentarioPublicacion44.setContenido("El doblaje en latino era oro puro, no acepto debate.");
    comentarioPublicacion44.setFecha(LocalDateTime.of(2025, 5, 28, 16, 23, 59));

    ComentarioPublicacion comentarioPublicacion45 = new ComentarioPublicacion();
    comentarioPublicacion45.setPublicacion(publicacion7);
    comentarioPublicacion45.setUsuario(usuario3);
    comentarioPublicacion45.setContenido("Lo retro siempre gana. Hasta con gráficos de PS1 fundida.");
    comentarioPublicacion45.setFecha(LocalDateTime.of(2025, 5, 28, 16, 27, 6));

    ComentarioPublicacion comentarioPublicacion46 = new ComentarioPublicacion();
    comentarioPublicacion46.setPublicacion(publicacion7);
    comentarioPublicacion46.setUsuario(usuario18);
    comentarioPublicacion46.setContenido("El juego nuevo es bonito, pero no tiene alma. Como tu currículum.");
    comentarioPublicacion46.setFecha(LocalDateTime.of(2025, 5, 28, 16, 30, 42));

    ComentarioPublicacion comentarioPublicacion47 = new ComentarioPublicacion();
    comentarioPublicacion47.setPublicacion(publicacion7);
    comentarioPublicacion47.setUsuario(usuario10);
    comentarioPublicacion47.setContenido("La cámara del juego era una maldición imperdonable.");
    comentarioPublicacion47.setFecha(LocalDateTime.of(2025, 5, 28, 16, 33, 19));

    ComentarioPublicacion comentarioPublicacion48 = new ComentarioPublicacion();
    comentarioPublicacion48.setPublicacion(publicacion7);
    comentarioPublicacion48.setUsuario(usuario8);
    comentarioPublicacion48.setContenido("Flipendo >>>> cualquier spell de Hogwarts Legacy.");
    comentarioPublicacion48.setFecha(LocalDateTime.of(2025, 5, 28, 16, 36, 2));

    ComentarioPublicacion comentarioPublicacion49 = new ComentarioPublicacion();
    comentarioPublicacion49.setPublicacion(publicacion7);
    comentarioPublicacion49.setUsuario(usuario17);
    comentarioPublicacion49.setContenido("No había DLCs ni pases de batalla. Eras libre.");
    comentarioPublicacion49.setFecha(LocalDateTime.of(2025, 5, 28, 16, 39, 38));

    ComentarioPublicacion comentarioPublicacion50 = new ComentarioPublicacion();
    comentarioPublicacion50.setPublicacion(publicacion7);
    comentarioPublicacion50.setUsuario(usuario5);
    comentarioPublicacion50.setContenido("Porque los hacían con amor, no con IA.");
    comentarioPublicacion50.setFecha(LocalDateTime.of(2025, 5, 28, 16, 42, 50));

    publicacion7.setComentariosPublicacion(Arrays.asList(
            comentarioPublicacion37,
            comentarioPublicacion38,
            comentarioPublicacion39,
            comentarioPublicacion40,
            comentarioPublicacion41,
            comentarioPublicacion42,
            comentarioPublicacion43,
            comentarioPublicacion44,
            comentarioPublicacion45,
            comentarioPublicacion46,
            comentarioPublicacion47,
            comentarioPublicacion48,
            comentarioPublicacion49,
            comentarioPublicacion50
    ));

    //Publicaciones de la comunidad de Interstellar

    // Publicacion 8

    Publicacion publicacion8 = new Publicacion();
    publicacion8.setTitulo("He vuelto a ver Interstellar y cada vez me explota más la cabeza");

    // Comentarios de la publicacion 8

    ComentarioPublicacion comentarioPublicacion51 = new ComentarioPublicacion();
    comentarioPublicacion51.setPublicacion(publicacion8);
    comentarioPublicacion51.setUsuario(usuario7);
    comentarioPublicacion51.setContenido("Es que es brutal chavales. La música, el guión, el viaje al planeta agua... Nolan está loco");
    comentarioPublicacion51.setFecha(LocalDateTime.of(2025, 5, 28, 20, 10, 11));

    ComentarioPublicacion comentarioPublicacion52 = new ComentarioPublicacion();
    comentarioPublicacion52.setPublicacion(publicacion8);
    comentarioPublicacion52.setUsuario(usuario13);
    comentarioPublicacion52.setContenido("Yo lloré con el vídeo de los hijos, no me escondo");
    comentarioPublicacion52.setFecha(LocalDateTime.of(2025, 5, 28, 20, 15, 49));

    ComentarioPublicacion comentarioPublicacion53 = new ComentarioPublicacion();
    comentarioPublicacion53.setPublicacion(publicacion8);
    comentarioPublicacion53.setUsuario(usuario2);
    comentarioPublicacion53.setContenido("Te imaginas que en vez de una nave van en un Seat Panda? Oscar asegurado.");
    comentarioPublicacion53.setFecha(LocalDateTime.of(2025, 5, 28, 20, 19, 2));

    ComentarioPublicacion comentarioPublicacion54 = new ComentarioPublicacion();
    comentarioPublicacion54.setPublicacion(publicacion8);
    comentarioPublicacion54.setUsuario(usuario18);
    comentarioPublicacion54.setContenido("La escena del reloj es de genios. Pero aún no la entiendo bien, la verdad.");
    comentarioPublicacion54.setFecha(LocalDateTime.of(2025, 5, 28, 20, 25, 33));

    ComentarioPublicacion comentarioPublicacion55 = new ComentarioPublicacion();
    comentarioPublicacion55.setPublicacion(publicacion8);
    comentarioPublicacion55.setUsuario(usuario5);
    comentarioPublicacion55.setContenido("No sé qué es más jodido: la relatividad del tiempo o que Matt Damon saliera como traidor.");
    comentarioPublicacion55.setFecha(LocalDateTime.of(2025, 5, 28, 20, 27, 55));

    ComentarioPublicacion comentarioPublicacion56 = new ComentarioPublicacion();
    comentarioPublicacion56.setPublicacion(publicacion8);
    comentarioPublicacion56.setUsuario(usuario9);
    comentarioPublicacion56.setContenido("Tremendo peliculón, hermano. Esto sí que es cine y no la mierda de Avatar.");
    comentarioPublicacion56.setFecha(LocalDateTime.of(2025, 5, 28, 20, 31, 12));

    ComentarioPublicacion comentarioPublicacion57 = new ComentarioPublicacion();
    comentarioPublicacion57.setPublicacion(publicacion8);
    comentarioPublicacion57.setUsuario(usuario14);
    comentarioPublicacion57.setContenido("Me eché una siesta en el planeta del agua y perdí 7 años, maldita sea.");
    comentarioPublicacion57.setFecha(LocalDateTime.of(2025, 5, 28, 20, 34, 58));

    ComentarioPublicacion comentarioPublicacion58 = new ComentarioPublicacion();
    comentarioPublicacion58.setPublicacion(publicacion8);
    comentarioPublicacion58.setUsuario(usuario1);
    comentarioPublicacion58.setContenido("La banda sonora la tengo puesta hasta en el coche. Subes al Lidl y te crees en una misión espacial.");
    comentarioPublicacion58.setFecha(LocalDateTime.of(2025, 5, 28, 20, 37, 42));

    ComentarioPublicacion comentarioPublicacion59 = new ComentarioPublicacion();
    comentarioPublicacion59.setPublicacion(publicacion8);
    comentarioPublicacion59.setUsuario(usuario20);
    comentarioPublicacion59.setContenido("Me flipa todo menos el final. No lo pillé, ¿Murph ve al padre o lo imagina?");
    comentarioPublicacion59.setFecha(LocalDateTime.of(2025, 5, 28, 20, 42, 15));

    ComentarioPublicacion comentarioPublicacion60 = new ComentarioPublicacion();
    comentarioPublicacion60.setPublicacion(publicacion8);
    comentarioPublicacion60.setUsuario(usuario6);
    comentarioPublicacion60.setContenido("Lo peor es que ahora quiero estudiar física cuántica y soy repartidor.");
    comentarioPublicacion60.setFecha(LocalDateTime.of(2025, 5, 28, 20, 45, 59));

    ComentarioPublicacion comentarioPublicacion61 = new ComentarioPublicacion();
    comentarioPublicacion61.setPublicacion(publicacion8);
    comentarioPublicacion61.setUsuario(usuario11);
    comentarioPublicacion61.setContenido("A mí me reventó la cabeza cuando explican lo del agujero de gusano con un papel.");
    comentarioPublicacion61.setFecha(LocalDateTime.of(2025, 5, 28, 20, 49, 26));

    ComentarioPublicacion comentarioPublicacion62 = new ComentarioPublicacion();
    comentarioPublicacion62.setPublicacion(publicacion8);
    comentarioPublicacion62.setUsuario(usuario4);
    comentarioPublicacion62.setContenido("El robot ese, TARS, es más carismático que media industria de Hollywood.");
    comentarioPublicacion62.setFecha(LocalDateTime.of(2025, 5, 28, 20, 52, 18));

    ComentarioPublicacion comentarioPublicacion63 = new ComentarioPublicacion();
    comentarioPublicacion63.setPublicacion(publicacion8);
    comentarioPublicacion63.setUsuario(usuario16);
    comentarioPublicacion63.setContenido("Mi padre no entendió nada pero dijo que era buena porque salía el vaquero de los anuncios.");
    comentarioPublicacion63.setFecha(LocalDateTime.of(2025, 5, 28, 20, 56, 43));

    publicacion8.setComentariosPublicacion(Arrays.asList(
            comentarioPublicacion51, comentarioPublicacion52, comentarioPublicacion53,
            comentarioPublicacion54, comentarioPublicacion55, comentarioPublicacion56,
            comentarioPublicacion57, comentarioPublicacion58, comentarioPublicacion59,
            comentarioPublicacion60, comentarioPublicacion61, comentarioPublicacion62,
            comentarioPublicacion63
    ));

    // Publicacion 9

    Publicacion publicacion9 = new Publicacion();
    publicacion9.setTitulo("La explicación del tiempo en Interstellar es una fumada o ciencia real?");

    // Comentarios de la publicacion 9

    ComentarioPublicacion comentarioPublicacion64 = new ComentarioPublicacion();
    comentarioPublicacion64.setPublicacion(publicacion9);
    comentarioPublicacion64.setUsuario(usuario12); // Usuario que inicia la publicación
    comentarioPublicacion64.setContenido("Estoy viendo la peli por cuarta vez y sigo sin saber si lo del tiempo en el planeta ese es real o Nolan estaba con setas.");
    comentarioPublicacion64.setFecha(LocalDateTime.of(2025, 5, 29, 18, 12, 3));

    ComentarioPublicacion comentarioPublicacion65 = new ComentarioPublicacion();
    comentarioPublicacion65.setPublicacion(publicacion9);
    comentarioPublicacion65.setUsuario(usuario8);
    comentarioPublicacion65.setContenido("Eso es 100% ciencia, bro. Relatividad general. Que se lo pregunten a Einstein.");
    comentarioPublicacion65.setFecha(LocalDateTime.of(2025, 5, 29, 18, 15, 34));

    ComentarioPublicacion comentarioPublicacion66 = new ComentarioPublicacion();
    comentarioPublicacion66.setPublicacion(publicacion9);
    comentarioPublicacion66.setUsuario(usuario1);
    comentarioPublicacion66.setContenido("Yo lo único que entiendo es que en el planeta ese no hay tiempo para nada, como cuando tienes novia.");
    comentarioPublicacion66.setFecha(LocalDateTime.of(2025, 5, 29, 18, 18, 49));

    ComentarioPublicacion comentarioPublicacion67 = new ComentarioPublicacion();
    comentarioPublicacion67.setPublicacion(publicacion9);
    comentarioPublicacion67.setUsuario(usuario17);
    comentarioPublicacion67.setContenido("Es ciencia, pero pasada por el filtro Nolan. O sea, sí, pero con fuegos artificiales.");
    comentarioPublicacion67.setFecha(LocalDateTime.of(2025, 5, 29, 18, 20, 11));

    ComentarioPublicacion comentarioPublicacion68 = new ComentarioPublicacion();
    comentarioPublicacion68.setPublicacion(publicacion9);
    comentarioPublicacion68.setUsuario(usuario3);
    comentarioPublicacion68.setContenido("Yo solo sé que cada vez que veo esa parte me rayo y tengo que pausar la peli.");
    comentarioPublicacion68.setFecha(LocalDateTime.of(2025, 5, 29, 18, 24, 3));

    ComentarioPublicacion comentarioPublicacion69 = new ComentarioPublicacion();
    comentarioPublicacion69.setPublicacion(publicacion9);
    comentarioPublicacion69.setUsuario(usuario19);
    comentarioPublicacion69.setContenido("Explicación rápida: gravedad fuerte = tiempo lento. Tú con resaca = planeta agua.");
    comentarioPublicacion69.setFecha(LocalDateTime.of(2025, 5, 29, 18, 29, 21));

    ComentarioPublicacion comentarioPublicacion70 = new ComentarioPublicacion();
    comentarioPublicacion70.setPublicacion(publicacion9);
    comentarioPublicacion70.setUsuario(usuario4);
    comentarioPublicacion70.setContenido("Yo pensaba que era mentira pero lo busqué en Google y resulta que sí, es posible. LOL.");
    comentarioPublicacion70.setFecha(LocalDateTime.of(2025, 5, 29, 18, 33, 44));

    ComentarioPublicacion comentarioPublicacion71 = new ComentarioPublicacion();
    comentarioPublicacion71.setPublicacion(publicacion9);
    comentarioPublicacion71.setUsuario(usuario15);
    comentarioPublicacion71.setContenido("Ojalá hacerme viejo en la Tierra mientras espero en la cola del Mercadona.");
    comentarioPublicacion71.setFecha(LocalDateTime.of(2025, 5, 29, 18, 36, 17));

    ComentarioPublicacion comentarioPublicacion72 = new ComentarioPublicacion();
    comentarioPublicacion72.setPublicacion(publicacion9);
    comentarioPublicacion72.setUsuario(usuario10);
    comentarioPublicacion72.setContenido("Es ciencia, pero si no eres físico te suena a que se lo han inventado. Yo igual.");
    comentarioPublicacion72.setFecha(LocalDateTime.of(2025, 5, 29, 18, 41, 50));

    ComentarioPublicacion comentarioPublicacion73 = new ComentarioPublicacion();
    comentarioPublicacion73.setPublicacion(publicacion9);
    comentarioPublicacion73.setUsuario(usuario6);
    comentarioPublicacion73.setContenido("Nolan y Kip Thorne son los culpables de que tenga pesadillas con relojes.");
    comentarioPublicacion73.setFecha(LocalDateTime.of(2025, 5, 29, 18, 47, 23));

    ComentarioPublicacion comentarioPublicacion74 = new ComentarioPublicacion();
    comentarioPublicacion74.setPublicacion(publicacion9);
    comentarioPublicacion74.setUsuario(usuario20);
    comentarioPublicacion74.setContenido("Yo creo que se fliparon, pero con estilo. Y eso ya vale la entrada del cine.");
    comentarioPublicacion74.setFecha(LocalDateTime.of(2025, 5, 29, 18, 53, 41));

    ComentarioPublicacion comentarioPublicacion75 = new ComentarioPublicacion();
    comentarioPublicacion75.setPublicacion(publicacion9);
    comentarioPublicacion75.setUsuario(usuario5);
    comentarioPublicacion75.setContenido("Ni ciencia ni leches. Si te tiras un cuesco fuerte en ese planeta igual viajas al pasado.");
    comentarioPublicacion75.setFecha(LocalDateTime.of(2025, 5, 29, 18, 58, 12));

    publicacion9.setComentariosPublicacion(Arrays.asList(
            comentarioPublicacion64, comentarioPublicacion65, comentarioPublicacion66, comentarioPublicacion67,
            comentarioPublicacion68, comentarioPublicacion69, comentarioPublicacion70, comentarioPublicacion71,
            comentarioPublicacion72, comentarioPublicacion73, comentarioPublicacion74, comentarioPublicacion75
    ));

    // Publicación 10

    Publicacion publicacion10 = new Publicacion();
    publicacion10.setTitulo("¿Qué parte de Interstellar os voló más la cabeza?");

    // Comentarios de la publicacion 10

    ComentarioPublicacion comentarioPublicacion76 = new ComentarioPublicacion();
    comentarioPublicacion76.setPublicacion(publicacion10);
    comentarioPublicacion76.setUsuario(usuario7);
    comentarioPublicacion76.setContenido("Para mí lo del tesseracto ese. No entendí nada y lloré igual.");
    comentarioPublicacion76.setFecha(LocalDateTime.of(2025, 5, 30, 12, 10, 1));

    ComentarioPublicacion comentarioPublicacion77 = new ComentarioPublicacion();
    comentarioPublicacion77.setPublicacion(publicacion10);
    comentarioPublicacion77.setUsuario(usuario2);
    comentarioPublicacion77.setContenido("El robot con forma de ladrillo. Quiero uno en mi coche.");
    comentarioPublicacion77.setFecha(LocalDateTime.of(2025, 5, 30, 12, 12, 44));

    ComentarioPublicacion comentarioPublicacion78 = new ComentarioPublicacion();
    comentarioPublicacion78.setPublicacion(publicacion10);
    comentarioPublicacion78.setUsuario(usuario16);
    comentarioPublicacion78.setContenido("Cuando vuelve y la hija es una abuela. Me rompí en 2.");
    comentarioPublicacion78.setFecha(LocalDateTime.of(2025, 5, 30, 12, 14, 23));

    ComentarioPublicacion comentarioPublicacion79 = new ComentarioPublicacion();
    comentarioPublicacion79.setPublicacion(publicacion10);
    comentarioPublicacion79.setUsuario(usuario11);
    comentarioPublicacion79.setContenido("Lo del tiempo me dejó más tieso que mi cuenta el día 25.");
    comentarioPublicacion79.setFecha(LocalDateTime.of(2025, 5, 30, 12, 17, 39));

    ComentarioPublicacion comentarioPublicacion80 = new ComentarioPublicacion();
    comentarioPublicacion80.setPublicacion(publicacion10);
    comentarioPublicacion80.setUsuario(usuario14);
    comentarioPublicacion80.setContenido("Yo sigo sin saber qué pasó con Matt Damon. ¿Estaba bien?");
    comentarioPublicacion80.setFecha(LocalDateTime.of(2025, 5, 30, 12, 20, 11));

    publicacion10.setComentariosPublicacion(Arrays.asList(
            comentarioPublicacion76, comentarioPublicacion77, comentarioPublicacion78,
            comentarioPublicacion79, comentarioPublicacion80
    ));

    // Publicación 11

    Publicacion publicacion11 = new Publicacion();
    publicacion11.setTitulo("¿TARS mejor personaje de Interstellar?");

    //Comentarios de la publicacion 11

    ComentarioPublicacion comentarioPublicacion81 = new ComentarioPublicacion();
    comentarioPublicacion81.setPublicacion(publicacion11);
    comentarioPublicacion81.setUsuario(usuario4);
    comentarioPublicacion81.setContenido("Sí. Punto. Que no se discuta más.");
    comentarioPublicacion81.setFecha(LocalDateTime.of(2025, 5, 30, 15, 0, 0));

    ComentarioPublicacion comentarioPublicacion82 = new ComentarioPublicacion();
    comentarioPublicacion82.setPublicacion(publicacion11);
    comentarioPublicacion82.setUsuario(usuario19);
    comentarioPublicacion82.setContenido("Mejor que Cooper. Al menos no llora cada 10 minutos.");
    comentarioPublicacion82.setFecha(LocalDateTime.of(2025, 5, 30, 15, 1, 20));

    ComentarioPublicacion comentarioPublicacion83 = new ComentarioPublicacion();
    comentarioPublicacion83.setPublicacion(publicacion11);
    comentarioPublicacion83.setUsuario(usuario6);
    comentarioPublicacion83.setContenido("Sarcasmo al 100%. No se puede mejorar.");
    comentarioPublicacion83.setFecha(LocalDateTime.of(2025, 5, 30, 15, 4, 47));

    ComentarioPublicacion comentarioPublicacion84 = new ComentarioPublicacion();
    comentarioPublicacion84.setPublicacion(publicacion11);
    comentarioPublicacion84.setUsuario(usuario13);
    comentarioPublicacion84.setContenido("Es el R2-D2 de los que hemos pasado por ingeniería.");
    comentarioPublicacion84.setFecha(LocalDateTime.of(2025, 5, 30, 15, 6, 33));

    ComentarioPublicacion comentarioPublicacion85 = new ComentarioPublicacion();
    comentarioPublicacion85.setPublicacion(publicacion11);
    comentarioPublicacion85.setUsuario(usuario9);
    comentarioPublicacion85.setContenido("El único que hace cosas útiles sin drama. MVP.");
    comentarioPublicacion85.setFecha(LocalDateTime.of(2025, 5, 30, 15, 10, 10));

    publicacion11.setComentariosPublicacion(Arrays.asList(
            comentarioPublicacion81, comentarioPublicacion82, comentarioPublicacion83,
            comentarioPublicacion84, comentarioPublicacion85
    ));


    // Publicación 12

    Publicacion publicacion12 = new Publicacion();
    publicacion12.setTitulo("¿Interstellar es mejor que 2001 Odisea en el Espacio?");

    //Comentarios de la publicacion 12

    ComentarioPublicacion comentarioPublicacion86 = new ComentarioPublicacion();
    comentarioPublicacion86.setPublicacion(publicacion12);
    comentarioPublicacion86.setUsuario(usuario8);
    comentarioPublicacion86.setContenido("Aquí vengo a abrir melones. Dadle.");
    comentarioPublicacion86.setFecha(LocalDateTime.of(2025, 5, 30, 17, 1, 0));

    ComentarioPublicacion comentarioPublicacion87 = new ComentarioPublicacion();
    comentarioPublicacion87.setPublicacion(publicacion12);
    comentarioPublicacion87.setUsuario(usuario17);
    comentarioPublicacion87.setContenido("Interstellar para emocionar, 2001 para dormir. Sorry Kubrick");
    comentarioPublicacion87.setFecha(LocalDateTime.of(2025, 5, 30, 17, 3, 34));

    ComentarioPublicacion comentarioPublicacion88 = new ComentarioPublicacion();
    comentarioPublicacion88.setPublicacion(publicacion12);
    comentarioPublicacion88.setUsuario(usuario5);
    comentarioPublicacion88.setContenido("No compares churras con Nolan.");
    comentarioPublicacion88.setFecha(LocalDateTime.of(2025, 5, 30, 17, 7, 58));

    ComentarioPublicacion comentarioPublicacion89 = new ComentarioPublicacion();
    comentarioPublicacion89.setPublicacion(publicacion12);
    comentarioPublicacion89.setUsuario(usuario18);
    comentarioPublicacion89.setContenido("2001 es más arte. Interstellar es más feels.");
    comentarioPublicacion89.setFecha(LocalDateTime.of(2025, 5, 30, 17, 11, 42));

    ComentarioPublicacion comentarioPublicacion90 = new ComentarioPublicacion();
    comentarioPublicacion90.setPublicacion(publicacion12);
    comentarioPublicacion90.setUsuario(usuario20);
    comentarioPublicacion90.setContenido("A mí dame robots cuadrados antes que bebés estelares.");
    comentarioPublicacion90.setFecha(LocalDateTime.of(2025, 5, 30, 17, 14, 55));

    publicacion12.setComentariosPublicacion(Arrays.asList(
            comentarioPublicacion86, comentarioPublicacion87, comentarioPublicacion88,
            comentarioPublicacion89, comentarioPublicacion90
    ));

    // Publicación 13

    Publicacion publicacion13 = new Publicacion();
    publicacion13.setTitulo("La banda sonora de Interstellar me dio taquicardia, ¿os pasó?");

    //Comentarios de la publicacion 13

    ComentarioPublicacion comentarioPublicacion91 = new ComentarioPublicacion();
    comentarioPublicacion91.setPublicacion(publicacion13);
    comentarioPublicacion91.setUsuario(usuario3);
    comentarioPublicacion91.setContenido("Cuando empieza a sonar el órgano pensé que era el fin del mundo real.");
    comentarioPublicacion91.setFecha(LocalDateTime.of(2025, 6, 1, 10, 12, 5));

    ComentarioPublicacion comentarioPublicacion92 = new ComentarioPublicacion();
    comentarioPublicacion92.setPublicacion(publicacion13);
    comentarioPublicacion92.setUsuario(usuario15);
    comentarioPublicacion92.setContenido("Zimmer se pasó el juego con eso. Me dan escalofríos aún.");
    comentarioPublicacion92.setFecha(LocalDateTime.of(2025, 6, 1, 10, 13, 40));

    ComentarioPublicacion comentarioPublicacion93 = new ComentarioPublicacion();
    comentarioPublicacion93.setPublicacion(publicacion13);
    comentarioPublicacion93.setUsuario(usuario6);
    comentarioPublicacion93.setContenido("Yo la escucho para dormir. En serio.");
    comentarioPublicacion93.setFecha(LocalDateTime.of(2025, 6, 1, 10, 16, 18));

    ComentarioPublicacion comentarioPublicacion94 = new ComentarioPublicacion();
    comentarioPublicacion94.setPublicacion(publicacion13);
    comentarioPublicacion94.setUsuario(usuario1);
    comentarioPublicacion94.setContenido("No sabía si me iba a emocionar o explotar el pecho. Brutal.");
    comentarioPublicacion94.setFecha(LocalDateTime.of(2025, 6, 1, 10, 19, 50));

    publicacion13.setComentariosPublicacion(Arrays.asList(
            comentarioPublicacion91, comentarioPublicacion92,
            comentarioPublicacion93, comentarioPublicacion94
    ));


    // Publicación 14

    Publicacion publicacion14 = new Publicacion();
    publicacion14.setTitulo("¿El planeta del agua tenía sentido o Nolan flipó?");

    //Comentarios de la publicacion 14

    ComentarioPublicacion comentarioPublicacion95 = new ComentarioPublicacion();
    comentarioPublicacion95.setPublicacion(publicacion14);
    comentarioPublicacion95.setUsuario(usuario10);
    comentarioPublicacion95.setContenido("Ese tsunami venía con mala leche. Aún no lo supero.");
    comentarioPublicacion95.setFecha(LocalDateTime.of(2025, 6, 2, 12, 40, 15));

    ComentarioPublicacion comentarioPublicacion96 = new ComentarioPublicacion();
    comentarioPublicacion96.setPublicacion(publicacion14);
    comentarioPublicacion96.setUsuario(usuario12);
    comentarioPublicacion96.setContenido("Una hora allí son 7 años... pues me da algo. Flipante.");
    comentarioPublicacion96.setFecha(LocalDateTime.of(2025, 6, 2, 12, 43, 33));

    ComentarioPublicacion comentarioPublicacion97 = new ComentarioPublicacion();
    comentarioPublicacion97.setPublicacion(publicacion14);
    comentarioPublicacion97.setUsuario(usuario5);
    comentarioPublicacion97.setContenido("Yo pensaba que era una metáfora hasta que vi la ola.");
    comentarioPublicacion97.setFecha(LocalDateTime.of(2025, 6, 2, 12, 46, 12));

    publicacion14.setComentariosPublicacion(Arrays.asList(
            comentarioPublicacion95, comentarioPublicacion96, comentarioPublicacion97
    ));


    // Publicación 15

    Publicacion publicacion15 = new Publicacion();
    publicacion15.setTitulo("¿Por qué no mandaron a TARS solo a la misión?");

    //Comentarios de la publicacion 15

    ComentarioPublicacion comentarioPublicacion98 = new ComentarioPublicacion();
    comentarioPublicacion98.setPublicacion(publicacion15);
    comentarioPublicacion98.setUsuario(usuario7);
    comentarioPublicacion98.setContenido("Honestamente, lo habría hecho mejor solo. TARS presidente.");
    comentarioPublicacion98.setFecha(LocalDateTime.of(2025, 6, 3, 9, 2, 0));

    ComentarioPublicacion comentarioPublicacion99 = new ComentarioPublicacion();
    comentarioPublicacion99.setPublicacion(publicacion15);
    comentarioPublicacion99.setUsuario(usuario11);
    comentarioPublicacion99.setContenido("La humanidad no se lo merecía. Nolan lo sabía.");
    comentarioPublicacion99.setFecha(LocalDateTime.of(2025, 6, 3, 9, 5, 11));

    ComentarioPublicacion comentarioPublicacion100 = new ComentarioPublicacion();
    comentarioPublicacion100.setPublicacion(publicacion15);
    comentarioPublicacion100.setUsuario(usuario18);
    comentarioPublicacion100.setContenido("Porque si no no hay peli. Pero en lógica, sí.");
    comentarioPublicacion100.setFecha(LocalDateTime.of(2025, 6, 3, 9, 7, 22));

    ComentarioPublicacion comentarioPublicacion101 = new ComentarioPublicacion();
    comentarioPublicacion101.setPublicacion(publicacion15);
    comentarioPublicacion101.setUsuario(usuario2);
    comentarioPublicacion101.setContenido("Con 100% de honestidad y sin dramas. TARS MVP de nuevo.");
    comentarioPublicacion101.setFecha(LocalDateTime.of(2025, 6, 3, 9, 11, 43));

    publicacion15.setComentariosPublicacion(Arrays.asList(
            comentarioPublicacion98, comentarioPublicacion99,
            comentarioPublicacion100, comentarioPublicacion101
    ));

    // Publicación 16 - The Gentlemen

    Publicacion publicacion16 = new Publicacion();
    publicacion16.setTitulo("¿La mejor escena de The Gentlemen?");

    ComentarioPublicacion comentarioPublicacion102 = new ComentarioPublicacion();
    comentarioPublicacion102.setPublicacion(publicacion16);
    comentarioPublicacion102.setUsuario(usuario4);
    comentarioPublicacion102.setContenido("Cuando Fletcher empieza a contar la historia con estilo cómic.");
    comentarioPublicacion102.setFecha(LocalDateTime.of(2025, 6, 4, 10, 15, 0));

    ComentarioPublicacion comentarioPublicacion103 = new ComentarioPublicacion();
    comentarioPublicacion103.setPublicacion(publicacion16);
    comentarioPublicacion103.setUsuario(usuario7);
    comentarioPublicacion103.setContenido("La pelea en el chiringuito. Brutalidad británica pura.");
    comentarioPublicacion103.setFecha(LocalDateTime.of(2025, 6, 4, 10, 18, 10));

    ComentarioPublicacion comentarioPublicacion104 = new ComentarioPublicacion();
    comentarioPublicacion104.setPublicacion(publicacion16);
    comentarioPublicacion104.setUsuario(usuario11);
    comentarioPublicacion104.setContenido("Cuando Ray se lía a golpes en el almacén. Tensión y risas.");
    comentarioPublicacion104.setFecha(LocalDateTime.of(2025, 6, 4, 10, 21, 30));

    ComentarioPublicacion comentarioPublicacion105 = new ComentarioPublicacion();
    comentarioPublicacion105.setPublicacion(publicacion16);
    comentarioPublicacion105.setUsuario(usuario14);
    comentarioPublicacion105.setContenido("Colin Farrell enseñando modales a los chavales. ORO.");
    comentarioPublicacion105.setFecha(LocalDateTime.of(2025, 6, 4, 10, 25, 12));

    ComentarioPublicacion comentarioPublicacion106 = new ComentarioPublicacion();
    comentarioPublicacion106.setPublicacion(publicacion16);
    comentarioPublicacion106.setUsuario(usuario9);
    comentarioPublicacion106.setContenido("La conversación final entre Mickey y Berger. Tensión máxima.");
    comentarioPublicacion106.setFecha(LocalDateTime.of(2025, 6, 4, 10, 29, 45));

    publicacion16.setComentariosPublicacion(Arrays.asList(
            comentarioPublicacion102, comentarioPublicacion103, comentarioPublicacion104,
            comentarioPublicacion105, comentarioPublicacion106
    ));


// Publicación 17 - The Gentlemen

    Publicacion publicacion17 = new Publicacion();
    publicacion17.setTitulo("¿Quién se roba la peli en The Gentlemen?");

    ComentarioPublicacion comentarioPublicacion107 = new ComentarioPublicacion();
    comentarioPublicacion107.setPublicacion(publicacion17);
    comentarioPublicacion107.setUsuario(usuario6);
    comentarioPublicacion107.setContenido("Colin Farrell sin dudas. Poca pantalla pero se la come.");
    comentarioPublicacion107.setFecha(LocalDateTime.of(2025, 6, 4, 12, 0, 0));

    ComentarioPublicacion comentarioPublicacion108 = new ComentarioPublicacion();
    comentarioPublicacion108.setPublicacion(publicacion17);
    comentarioPublicacion108.setUsuario(usuario13);
    comentarioPublicacion108.setContenido("Hugh Grant, no esperaba ese nivel. Qué papelazo.");
    comentarioPublicacion108.setFecha(LocalDateTime.of(2025, 6, 4, 12, 3, 19));

    ComentarioPublicacion comentarioPublicacion109 = new ComentarioPublicacion();
    comentarioPublicacion109.setPublicacion(publicacion17);
    comentarioPublicacion109.setUsuario(usuario3);
    comentarioPublicacion109.setContenido("Matthew McConaughey como siempre: dominante y elegante.");
    comentarioPublicacion109.setFecha(LocalDateTime.of(2025, 6, 4, 12, 7, 50));

    ComentarioPublicacion comentarioPublicacion110 = new ComentarioPublicacion();
    comentarioPublicacion110.setPublicacion(publicacion17);
    comentarioPublicacion110.setUsuario(usuario18);
    comentarioPublicacion110.setContenido("Me sorprendió Charlie Hunnam. Serio y calmado, pero potente.");
    comentarioPublicacion110.setFecha(LocalDateTime.of(2025, 6, 4, 12, 11, 0));

    ComentarioPublicacion comentarioPublicacion111 = new ComentarioPublicacion();
    comentarioPublicacion111.setPublicacion(publicacion17);
    comentarioPublicacion111.setUsuario(usuario1);
    comentarioPublicacion111.setContenido("Fletcher contando la historia como si fuera cine dentro del cine.");
    comentarioPublicacion111.setFecha(LocalDateTime.of(2025, 6, 4, 12, 14, 20));

    ComentarioPublicacion comentarioPublicacion112 = new ComentarioPublicacion();
    comentarioPublicacion112.setPublicacion(publicacion17);
    comentarioPublicacion112.setUsuario(usuario20);
    comentarioPublicacion112.setContenido("Coach y sus chicos. Quiero una serie solo de ellos.");
    comentarioPublicacion112.setFecha(LocalDateTime.of(2025, 6, 4, 12, 17, 45));

    publicacion17.setComentariosPublicacion(Arrays.asList(
            comentarioPublicacion107, comentarioPublicacion108, comentarioPublicacion109,
            comentarioPublicacion110, comentarioPublicacion111, comentarioPublicacion112
    ));


// Publicación 18 - The Gentlemen

    Publicacion publicacion18 = new Publicacion();
    publicacion18.setTitulo("¿Qué opináis del estilo visual en The Gentlemen?");

    ComentarioPublicacion comentarioPublicacion113 = new ComentarioPublicacion();
    comentarioPublicacion113.setPublicacion(publicacion18);
    comentarioPublicacion113.setUsuario(usuario2);
    comentarioPublicacion113.setContenido("Cada plano parece una editorial de moda. Espectacular.");
    comentarioPublicacion113.setFecha(LocalDateTime.of(2025, 6, 4, 14, 0, 0));

    ComentarioPublicacion comentarioPublicacion114 = new ComentarioPublicacion();
    comentarioPublicacion114.setPublicacion(publicacion18);
    comentarioPublicacion114.setUsuario(usuario5);
    comentarioPublicacion114.setContenido("Ritchie tiene estilo, eso está claro. Estética muy marcada.");
    comentarioPublicacion114.setFecha(LocalDateTime.of(2025, 6, 4, 14, 2, 13));

    ComentarioPublicacion comentarioPublicacion115 = new ComentarioPublicacion();
    comentarioPublicacion115.setPublicacion(publicacion18);
    comentarioPublicacion115.setUsuario(usuario17);
    comentarioPublicacion115.setContenido("Me gustó más que Snatch en ese aspecto. Más pulido.");
    comentarioPublicacion115.setFecha(LocalDateTime.of(2025, 6, 4, 14, 5, 22));

    ComentarioPublicacion comentarioPublicacion116 = new ComentarioPublicacion();
    comentarioPublicacion116.setPublicacion(publicacion18);
    comentarioPublicacion116.setUsuario(usuario10);
    comentarioPublicacion116.setContenido("La ropa, los coches, las casas... puro lujo sucio.");
    comentarioPublicacion116.setFecha(LocalDateTime.of(2025, 6, 4, 14, 8, 36));

    publicacion18.setComentariosPublicacion(Arrays.asList(
            comentarioPublicacion113, comentarioPublicacion114,
            comentarioPublicacion115, comentarioPublicacion116
    ));


// Publicación 19 - The Gentlemen

    Publicacion publicacion19 = new Publicacion();
    publicacion19.setTitulo("¿Qué os pareció la trama secundaria de los jóvenes raperos?");

    ComentarioPublicacion comentarioPublicacion117 = new ComentarioPublicacion();
    comentarioPublicacion117.setPublicacion(publicacion19);
    comentarioPublicacion117.setUsuario(usuario6);
    comentarioPublicacion117.setContenido("Un desvío gracioso. Le da frescura al tono serio.");
    comentarioPublicacion117.setFecha(LocalDateTime.of(2025, 6, 4, 16, 0, 0));

    ComentarioPublicacion comentarioPublicacion118 = new ComentarioPublicacion();
    comentarioPublicacion118.setPublicacion(publicacion19);
    comentarioPublicacion118.setUsuario(usuario19);
    comentarioPublicacion118.setContenido("Les habría dado más minutos. Buen ritmo y humor.");
    comentarioPublicacion118.setFecha(LocalDateTime.of(2025, 6, 4, 16, 2, 40));

    ComentarioPublicacion comentarioPublicacion119 = new ComentarioPublicacion();
    comentarioPublicacion119.setPublicacion(publicacion19);
    comentarioPublicacion119.setUsuario(usuario12);
    comentarioPublicacion119.setContenido("No aportan mucho a la trama, pero me reí.");
    comentarioPublicacion119.setFecha(LocalDateTime.of(2025, 6, 4, 16, 5, 55));

    ComentarioPublicacion comentarioPublicacion120 = new ComentarioPublicacion();
    comentarioPublicacion120.setPublicacion(publicacion19);
    comentarioPublicacion120.setUsuario(usuario15);
    comentarioPublicacion120.setContenido("Le dan ese toque 'Ritchie' clásico. Buen añadido.");
    comentarioPublicacion120.setFecha(LocalDateTime.of(2025, 6, 4, 16, 8, 29));

    publicacion19.setComentariosPublicacion(Arrays.asList(
            comentarioPublicacion117, comentarioPublicacion118,
            comentarioPublicacion119, comentarioPublicacion120
    ));


// Publicación 20 - The Gentlemen

    Publicacion publicacion20 = new Publicacion();
    publicacion20.setTitulo("¿La escena de la carnicería fue demasiado?");

    ComentarioPublicacion comentarioPublicacion121 = new ComentarioPublicacion();
    comentarioPublicacion121.setPublicacion(publicacion20);
    comentarioPublicacion121.setUsuario(usuario16);
    comentarioPublicacion121.setContenido("Un poco de shock, pero necesaria para mostrar el tono serio.");
    comentarioPublicacion121.setFecha(LocalDateTime.of(2025, 6, 4, 18, 0, 0));

    ComentarioPublicacion comentarioPublicacion122 = new ComentarioPublicacion();
    comentarioPublicacion122.setPublicacion(publicacion20);
    comentarioPublicacion122.setUsuario(usuario8);
    comentarioPublicacion122.setContenido("Guy Ritchie siempre mete algo así. No me sorprendió.");
    comentarioPublicacion122.setFecha(LocalDateTime.of(2025, 6, 4, 18, 3, 10));

    ComentarioPublicacion comentarioPublicacion123 = new ComentarioPublicacion();
    comentarioPublicacion123.setPublicacion(publicacion20);
    comentarioPublicacion123.setUsuario(usuario2);
    comentarioPublicacion123.setContenido("Demasiado para mí, pero efectiva.");
    comentarioPublicacion123.setFecha(LocalDateTime.of(2025, 6, 4, 18, 6, 20));

    ComentarioPublicacion comentarioPublicacion124 = new ComentarioPublicacion();
    comentarioPublicacion124.setPublicacion(publicacion20);
    comentarioPublicacion124.setUsuario(usuario4);
    comentarioPublicacion124.setContenido("La violencia en Guy Ritchie siempre tiene estilo.");
    comentarioPublicacion124.setFecha(LocalDateTime.of(2025, 6, 4, 18, 8, 47));

    publicacion20.setComentariosPublicacion(Arrays.asList(
            comentarioPublicacion121, comentarioPublicacion122,
            comentarioPublicacion123, comentarioPublicacion124
    ));

    // Publicación 21 - The Gentlemen

    Publicacion publicacion21 = new Publicacion();
    publicacion21.setTitulo("¿Qué os pareció el personaje de Rosalind?");

    ComentarioPublicacion comentarioPublicacion125 = new ComentarioPublicacion();
    comentarioPublicacion125.setPublicacion(publicacion21);
    comentarioPublicacion125.setUsuario(usuario7);
    comentarioPublicacion125.setContenido("Fuerte, elegante y sin miedo. Me encantó.");
    comentarioPublicacion125.setFecha(LocalDateTime.of(2025, 6, 4, 20, 0, 0));

    ComentarioPublicacion comentarioPublicacion126 = new ComentarioPublicacion();
    comentarioPublicacion126.setPublicacion(publicacion21);
    comentarioPublicacion126.setUsuario(usuario3);
    comentarioPublicacion126.setContenido("Una de las mejores mujeres en el cine de Ritchie.");
    comentarioPublicacion126.setFecha(LocalDateTime.of(2025, 6, 4, 20, 2, 30));

    ComentarioPublicacion comentarioPublicacion127 = new ComentarioPublicacion();
    comentarioPublicacion127.setPublicacion(publicacion21);
    comentarioPublicacion127.setUsuario(usuario19);
    comentarioPublicacion127.setContenido("Su garaje de lujo me dejó sin palabras.");
    comentarioPublicacion127.setFecha(LocalDateTime.of(2025, 6, 4, 20, 5, 12));

    ComentarioPublicacion comentarioPublicacion128 = new ComentarioPublicacion();
    comentarioPublicacion128.setPublicacion(publicacion21);
    comentarioPublicacion128.setUsuario(usuario10);
    comentarioPublicacion128.setContenido("Quiero un spin-off solo con ella.");
    comentarioPublicacion128.setFecha(LocalDateTime.of(2025, 6, 4, 20, 8, 0));

    ComentarioPublicacion comentarioPublicacion129 = new ComentarioPublicacion();
    comentarioPublicacion129.setPublicacion(publicacion21);
    comentarioPublicacion129.setUsuario(usuario1);
    comentarioPublicacion129.setContenido("Su escena con la pistola en el escritorio fue brutal.");
    comentarioPublicacion129.setFecha(LocalDateTime.of(2025, 6, 4, 20, 10, 45));

    publicacion21.setComentariosPublicacion(Arrays.asList(
            comentarioPublicacion125, comentarioPublicacion126,
            comentarioPublicacion127, comentarioPublicacion128, comentarioPublicacion129
    ));


// Publicación 22 - The Gentlemen

    Publicacion publicacion22 = new Publicacion();
    publicacion22.setTitulo("¿Recomendaríais The Gentlemen a alguien que no conoce a Guy Ritchie?");

    ComentarioPublicacion comentarioPublicacion130 = new ComentarioPublicacion();
    comentarioPublicacion130.setPublicacion(publicacion22);
    comentarioPublicacion130.setUsuario(usuario8);
    comentarioPublicacion130.setContenido("Sí, es perfecta para iniciarse con su estilo.");
    comentarioPublicacion130.setFecha(LocalDateTime.of(2025, 6, 4, 21, 0, 0));

    ComentarioPublicacion comentarioPublicacion131 = new ComentarioPublicacion();
    comentarioPublicacion131.setPublicacion(publicacion22);
    comentarioPublicacion131.setUsuario(usuario11);
    comentarioPublicacion131.setContenido("Tiene todo lo bueno de Ritchie sin ser tan caótica.");
    comentarioPublicacion131.setFecha(LocalDateTime.of(2025, 6, 4, 21, 2, 22));

    ComentarioPublicacion comentarioPublicacion132 = new ComentarioPublicacion();
    comentarioPublicacion132.setPublicacion(publicacion22);
    comentarioPublicacion132.setUsuario(usuario6);
    comentarioPublicacion132.setContenido("La estructura narrativa es amigable para novatos.");
    comentarioPublicacion132.setFecha(LocalDateTime.of(2025, 6, 4, 21, 5, 10));

    ComentarioPublicacion comentarioPublicacion133 = new ComentarioPublicacion();
    comentarioPublicacion133.setPublicacion(publicacion22);
    comentarioPublicacion133.setUsuario(usuario15);
    comentarioPublicacion133.setContenido("¡Sí! Y si les gusta, que sigan con Snatch.");
    comentarioPublicacion133.setFecha(LocalDateTime.of(2025, 6, 4, 21, 8, 36));

    ComentarioPublicacion comentarioPublicacion134 = new ComentarioPublicacion();
    comentarioPublicacion134.setPublicacion(publicacion22);
    comentarioPublicacion134.setUsuario(usuario17);
    comentarioPublicacion134.setContenido("Totalmente recomendable. Engancha desde el minuto uno.");
    comentarioPublicacion134.setFecha(LocalDateTime.of(2025, 6, 4, 21, 11, 0));

    ComentarioPublicacion comentarioPublicacion135 = new ComentarioPublicacion();
    comentarioPublicacion135.setPublicacion(publicacion22);
    comentarioPublicacion135.setUsuario(usuario20);
    comentarioPublicacion135.setContenido("Y además tiene humor, acción y estilo. Va de cabeza.");
    comentarioPublicacion135.setFecha(LocalDateTime.of(2025, 6, 4, 21, 13, 47));

    publicacion22.setComentariosPublicacion(Arrays.asList(
            comentarioPublicacion130, comentarioPublicacion131, comentarioPublicacion132,
            comentarioPublicacion133, comentarioPublicacion134, comentarioPublicacion135
    ));




    //Comunidades de prueba

    Comunidad comunidad1 = new Comunidad();
    comunidad1.setNombreComunidad("Harry Potter");
    comunidad1.setDescripcion("En la comunidad de Harry Potter podrás hablar de todas " +
            "las películas, series y videojuegos relacionados. ¡Anímate y haz una publicación!");
    comunidad1.setObjetos(Arrays.asList(objeto));
    comunidad1.setPublicaciones(Arrays.asList(publicacion1, publicacion2, publicacion3, publicacion4, publicacion5, publicacion6, publicacion7));
    comunidad1.setUsuarios(new HashSet<>(Arrays.asList(usuario1,
            usuario2, usuario3, usuario4, usuario5, usuario6, usuario7,
            usuario8, usuario9, usuario10, usuario11, usuario12, usuario13,
            usuario14, usuario15, usuario16, usuario17, usuario18, usuario20)));
    comunidad1.setUrlImg("https://static.posters.cz/image/1300/104639.jpg");

    Comunidad comunidad2 = new Comunidad();
    comunidad2.setNombreComunidad("Interstellar");
    comunidad2.setDescripcion("En la comunidad de Interstellar podrás hablar de todas " +
            "las películas, series y videojuegos relacionados. ¡Anímate y haz una publicación!");
    comunidad2.setObjetos(Arrays.asList(objeto2));
    comunidad2.setPublicaciones(Arrays.asList(
            publicacion8, publicacion9, publicacion10, publicacion11,
            publicacion12, publicacion13, publicacion14, publicacion15
    ));
    comunidad2.setUsuarios(new HashSet<>(Arrays.asList(
            usuario1, usuario2, usuario3, usuario4, usuario5, usuario6,
            usuario7, usuario8, usuario9, usuario10, usuario11, usuario12,
            usuario13, usuario14, usuario15, usuario16, usuario17, usuario18,
            usuario19, usuario20
    )));
    comunidad2.setUrlImg("https://m.media-amazon.com/images/M/MV5BYzdjMDAxZGItMjI2My00ODA1LTlkNzItOWFjMDU5ZDJlYWY3XkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg");

    Comunidad comunidad3 = new Comunidad();
    comunidad3.setNombreComunidad("The Gentlemen");
    comunidad3.setDescripcion("En la comunidad de The Gentlemen podrás hablar de todas " +
            "las películas, series y videojuegos relacionados. ¡Anímate y haz una publicación!");
    comunidad3.setObjetos(Arrays.asList(objeto3));
    comunidad3.setPublicaciones(Arrays.asList(publicacion16, publicacion17, publicacion18, publicacion19, publicacion20, publicacion21, publicacion22));
    comunidad3.setUsuarios(new HashSet<>(Arrays.asList(
            usuario1, usuario2, usuario3, usuario4, usuario5, usuario6, usuario7, usuario8,
            usuario9, usuario10, usuario11, usuario12, usuario13, usuario14, usuario15,
            usuario16, usuario17, usuario18, usuario19, usuario20
    )));
    comunidad3.setUrlImg("https://pics.filmaffinity.com/The_Gentlemen_Los_seanores_de_la_mafia-425828685-large.jpg");

    Comunidad comunidad4 = new Comunidad();
    comunidad4.setNombreComunidad("Misión Imposible");
    comunidad4.setDescripcion("En la comunidad de Misión Imposible podrás hablar de todas " +
            "las películas, series y videojuegos relacionados. ¡Anímate y haz una publicación!");
    comunidad4.setObjetos(Arrays.asList(objeto10));
//        comunidad4.setPublicaciones(Arrays.asList(publicacion1));
//        comunidad4.setUsuarios(Arrays.asList(usuario1, usuario2, usuario3));
    comunidad4.setUrlImg("https://pics.filmaffinity.com/Misiaon_imposible_Sentencia_final-784079226-large.jpg");

    Comunidad comunidad5 = new Comunidad();
    comunidad5.setNombreComunidad("El club de la lucha");
    comunidad5.setDescripcion("En la comunidad de El club de la lucha podrás hablar de todas " +
            "las películas, series y videojuegos relacionados. ¡Anímate y haz una publicación!");
    comunidad5.setObjetos(Arrays.asList(objeto9));
//        comunidad5.setPublicaciones(Arrays.asList(publicacion1));
//        comunidad5.setUsuarios(Arrays.asList(usuario1, usuario2, usuario3));
    comunidad5.setUrlImg("https://www.cinepazmadrid.es/data/fotos/afiche5-elclubdelalucha.jpg");

    Comunidad comunidad6 = new Comunidad();
    comunidad6.setNombreComunidad("Cadena Perpetua");
    comunidad6.setDescripcion("En la comunidad de Cadena Perpetua podrás hablar de todas " +
            "las películas, series y videojuegos relacionados. ¡Anímate y haz una publicación!");
    comunidad6.setObjetos(Arrays.asList(objeto4));
//        comunidad6.setPublicaciones(Arrays.asList(publicacion1));
//        comunidad6.setUsuarios(Arrays.asList(usuario1, usuario2, usuario3));
    comunidad6.setUrlImg("https://www.ecartelera.com/carteles/5600/5676/003_m.jpg");

    Comunidad comunidad7 = new Comunidad();
    comunidad7.setNombreComunidad("El Padrino");
    comunidad7.setDescripcion("En la comunidad de El Padrino podrás hablar de todas " +
            "las películas, series y videojuegos relacionados. ¡Anímate y haz una publicación!");
    comunidad7.setObjetos(Arrays.asList(objeto5));
//        comunidad7.setPublicaciones(Arrays.asList(publicacion1));
//        comunidad7.setUsuarios(Arrays.asList(usuario1, usuario2, usuario3));
    comunidad7.setUrlImg("https://www.ecartelera.com/carteles/2500/2521/002.jpg");

    Comunidad comunidad8 = new Comunidad();
    comunidad8.setNombreComunidad("Pulp Fiction");
    comunidad8.setDescripcion("En la comunidad de Pulp Fiction podrás hablar de todas " +
            "las películas, series y videojuegos relacionados. ¡Anímate y haz una publicación!");
    comunidad8.setObjetos(Arrays.asList(objeto8));
//        comunidad8.setPublicaciones(Arrays.asList(publicacion1));
//        comunidad8.setUsuarios(Arrays.asList(usuario1, usuario2, usuario3));
    comunidad8.setUrlImg("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1cYyaS50kL0g7JCqP8Dd3-T1NFmcVoFbQcQ&s");

    Comunidad comunidad9 = new Comunidad();
    comunidad9.setNombreComunidad("La lista de Schindler");
    comunidad9.setDescripcion("En la comunidad de La lista de Schindler podrás hablar de todas " +
            "las películas, series y videojuegos relacionados. ¡Anímate y haz una publicación!");
    comunidad9.setObjetos(Arrays.asList(objeto7));
//        comunidad9.setPublicaciones(Arrays.asList(publicacion1));
//        comunidad9.setUsuarios(Arrays.asList(usuario1, usuario2, usuario3));
    comunidad9.setUrlImg("https://pics.filmaffinity.com/schindler_s_list-473662617-large.jpg");

    Comunidad comunidad10 = new Comunidad();
    comunidad10.setNombreComunidad("12 hombres sin piedad");
    comunidad10.setDescripcion("En la comunidad de 12 hombres sin piedad podrás hablar de todas " +
            "las películas, series y videojuegos relacionados. ¡Anímate y haz una publicación!");
    comunidad10.setObjetos(Arrays.asList(objeto6));
//        comunidad10.setPublicaciones(Arrays.asList(publicacion1));
//        comunidad10.setUsuarios(Arrays.asList(usuario1, usuario2, usuario3));
    comunidad10.setUrlImg("https://cinesembajadores.es/wp-content/uploads/2025/01/12-hombres-sin-piedad-cartel.jpeg");

    Comunidad comunidad11 = new Comunidad();
    comunidad11.setNombreComunidad("The Last Of Us");
    comunidad11.setDescripcion("En la comunidad de The Last Of Us podrás hablar de todas " +
            "las películas, series y videojuegos relacionados. ¡Anímate y haz una publicación!");
    comunidad11.setObjetos(Arrays.asList(objeto11, objeto12, objeto13, objeto14, objeto15));
//        comunidad11.setPublicaciones(Arrays.asList(publicacion1));
//        comunidad11.setUsuarios(Arrays.asList(usuario1, usuario2, usuario3));
    comunidad11.setUrlImg("https://www.movistarplus.es/recorte/n/caratula5/F4174243?od[]=Z1V:HBOMAX_V");

    Comunidad comunidad12 = new Comunidad();
    comunidad12.setNombreComunidad("Breaking Bad");
    comunidad12.setDescripcion("En la comunidad de Breaking Bad podrás hablar de todas " +
            "las películas, series y videojuegos relacionados. ¡Anímate y haz una publicación!");
    comunidad12.setObjetos(Arrays.asList(objeto16, objeto17, objeto18));
//        comunidad12.setPublicaciones(Arrays.asList(publicacion1));
//        comunidad12.setUsuarios(Arrays.asList(usuario1, usuario2, usuario3));
    comunidad12.setUrlImg("https://es.web.img2.acsta.net/pictures/18/07/23/11/26/1237965.jpg");

    Comunidad comunidad13 = new Comunidad();
    comunidad13.setNombreComunidad("Elden Ring: Nightreign");
    comunidad13.setDescripcion("En la comunidad de Elden Ring: Nightreign podrás hablar de todas " +
            "las películas, series y videojuegos relacionados. ¡Anímate y haz una publicación!");
    comunidad13.setObjetos(Arrays.asList(objeto19));
//        comunidad13.setPublicaciones(Arrays.asList(publicacion1));
//        comunidad13.setUsuarios(Arrays.asList(usuario1, usuario2, usuario3));
    comunidad13.setUrlImg("https://i.3djuegos.com/juegos/20132/fotos/ficha/-5938515.webp");

    Comunidad comunidad14 = new Comunidad();
    comunidad14.setNombreComunidad("Clair Obscur: Expedition 33");
    comunidad14.setDescripcion("En la comunidad de Clair Obscur: Expedition 33 podrás hablar de todas " +
            "las películas, series y videojuegos relacionados. ¡Anímate y haz una publicación!");
    comunidad14.setObjetos(Arrays.asList(objeto20));
//        comunidad14.setPublicaciones(Arrays.asList(publicacion1));
//        comunidad14.setUsuarios(Arrays.asList(usuario1, usuario2, usuario3));
    comunidad14.setUrlImg("https://i.3djuegos.com/juegos/19783/clair_obscur_expedition_33/fotos/ficha/clair_obscur_expedition_33-5897212.webp");

    Comunidad comunidad15 = new Comunidad();
    comunidad15.setNombreComunidad("Assassin's Creed Shadows");
    comunidad15.setDescripcion("En la comunidad de Assassin's Creed Shadows podrás hablar de todas " +
            "las películas, series y videojuegos relacionados. ¡Anímate y haz una publicación!");
    comunidad15.setObjetos(Arrays.asList(objeto21));
//        comunidad15.setPublicaciones(Arrays.asList(publicacion1));
//        comunidad15.setUsuarios(Arrays.asList(usuario1, usuario2, usuario3));
    comunidad15.setUrlImg("https://i.3djuegos.com/juegos/18752/assassin__039_s_creed_codename_red/fotos/ficha/assassin__039_s_creed_codename_red-5904359.jpg");

    Comunidad comunidad16 = new Comunidad();
    comunidad16.setNombreComunidad("The Legend of Zelda: Tears of the Kingdom");
    comunidad16.setDescripcion("En la comunidad de The Legend of Zelda: Tears of the Kingdom podrás hablar de todas " +
            "las películas, series y videojuegos relacionados. ¡Anímate y haz una publicación!");
    comunidad16.setObjetos(Arrays.asList(objeto22));
//        comunidad16.setPublicaciones(Arrays.asList(publicacion1));
//        comunidad16.setUsuarios(Arrays.asList(usuario1, usuario2, usuario3));
    comunidad16.setUrlImg("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQoeTQgBfKZev-V2Z2chfc604vTI2hxHRv57g&s");

    Comunidad comunidad17 = new Comunidad();
    comunidad17.setNombreComunidad("Super Mario World");
    comunidad17.setDescripcion("En la comunidad de Super Mario World podrás hablar de todas " +
            "las películas, series y videojuegos relacionados. ¡Anímate y haz una publicación!");
    comunidad17.setObjetos(Arrays.asList(objeto23));
//        comunidad17.setPublicaciones(Arrays.asList(publicacion1));
//        comunidad17.setUsuarios(Arrays.asList(usuario1, usuario2, usuario3));
    comunidad17.setUrlImg("https://m.media-amazon.com/images/M/MV5BZjZkZWVjNTYtZDMzZC00N2YxLTg2NjUtMTdlMzJiMDk3MTNhXkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg");

    objeto.setComunidad(comunidad1);
    objeto2.setComunidad(comunidad2);
    objeto3.setComunidad(comunidad3);
    objeto10.setComunidad(comunidad4);
    objeto9.setComunidad(comunidad5);
    objeto4.setComunidad(comunidad6);
    objeto5.setComunidad(comunidad7);
    objeto8.setComunidad(comunidad8);
    objeto7.setComunidad(comunidad9);
    objeto6.setComunidad(comunidad10);
    objeto11.setComunidad(comunidad11);
    objeto12.setComunidad(comunidad11);
    objeto13.setComunidad(comunidad11);
    objeto14.setComunidad(comunidad11);
    objeto15.setComunidad(comunidad11);
    objeto16.setComunidad(comunidad12);
    objeto17.setComunidad(comunidad12);
    objeto18.setComunidad(comunidad12);
    objeto19.setComunidad(comunidad13);
    objeto20.setComunidad(comunidad14);
    objeto21.setComunidad(comunidad15);
    objeto22.setComunidad(comunidad16);
    objeto23.setComunidad(comunidad17);


    publicacion1.setComunidad(comunidad1);
    publicacion2.setComunidad(comunidad1);
    publicacion3.setComunidad(comunidad1);
    publicacion4.setComunidad(comunidad1);
    publicacion5.setComunidad(comunidad1);
    publicacion6.setComunidad(comunidad1);
    publicacion7.setComunidad(comunidad1);
    publicacion8.setComunidad(comunidad2);
    publicacion9.setComunidad(comunidad2);
    publicacion10.setComunidad(comunidad2);
    publicacion11.setComunidad(comunidad2);
    publicacion12.setComunidad(comunidad2);
    publicacion13.setComunidad(comunidad2);
    publicacion14.setComunidad(comunidad2);
    publicacion15.setComunidad(comunidad2);
    publicacion16.setComunidad(comunidad3);
    publicacion17.setComunidad(comunidad3);
    publicacion18.setComunidad(comunidad3);
    publicacion19.setComunidad(comunidad3);
    publicacion20.setComunidad(comunidad3);
    publicacion21.setComunidad(comunidad3);
    publicacion22.setComunidad(comunidad3);

    comunidadRepository.saveAll(Arrays.asList(comunidad1, comunidad2, comunidad3, comunidad4, comunidad5, comunidad6,
            comunidad7, comunidad8, comunidad9, comunidad10, comunidad11, comunidad12, comunidad13, comunidad14, comunidad15, comunidad16, comunidad17));
    publicacionRepository.saveAll(Arrays.asList(
            publicacion1, publicacion2, publicacion3, publicacion4, publicacion5,
            publicacion6, publicacion7, publicacion8, publicacion9, publicacion10,
            publicacion11, publicacion12, publicacion13, publicacion14, publicacion15,
            publicacion16, publicacion17, publicacion18, publicacion19, publicacion20,
            publicacion21, publicacion22
    ));
    comentarioPublicacionRepository.saveAll(Arrays.asList(
            comentarioPublicacion1, comentarioPublicacion2, comentarioPublicacion3, comentarioPublicacion4, comentarioPublicacion5,
            comentarioPublicacion6, comentarioPublicacion7, comentarioPublicacion8, comentarioPublicacion9, comentarioPublicacion10,
            comentarioPublicacion11, comentarioPublicacion12, comentarioPublicacion13, comentarioPublicacion14, comentarioPublicacion15,
            comentarioPublicacion16, comentarioPublicacion17, comentarioPublicacion18, comentarioPublicacion19, comentarioPublicacion20,
            comentarioPublicacion21, comentarioPublicacion22, comentarioPublicacion23, comentarioPublicacion24, comentarioPublicacion25,
            comentarioPublicacion26, comentarioPublicacion27, comentarioPublicacion28, comentarioPublicacion29, comentarioPublicacion30,
            comentarioPublicacion31, comentarioPublicacion32, comentarioPublicacion33, comentarioPublicacion34, comentarioPublicacion35,
            comentarioPublicacion36, comentarioPublicacion37, comentarioPublicacion38, comentarioPublicacion39, comentarioPublicacion40,
            comentarioPublicacion41, comentarioPublicacion42, comentarioPublicacion43, comentarioPublicacion44, comentarioPublicacion45,
            comentarioPublicacion46, comentarioPublicacion47, comentarioPublicacion48, comentarioPublicacion49, comentarioPublicacion50,
            comentarioPublicacion51, comentarioPublicacion52, comentarioPublicacion53, comentarioPublicacion54, comentarioPublicacion55,
            comentarioPublicacion56, comentarioPublicacion57, comentarioPublicacion58, comentarioPublicacion59, comentarioPublicacion60,
            comentarioPublicacion61, comentarioPublicacion62, comentarioPublicacion63, comentarioPublicacion64, comentarioPublicacion65,
            comentarioPublicacion66, comentarioPublicacion67, comentarioPublicacion68, comentarioPublicacion69, comentarioPublicacion70,
            comentarioPublicacion71, comentarioPublicacion72, comentarioPublicacion73, comentarioPublicacion74, comentarioPublicacion75,
            comentarioPublicacion76, comentarioPublicacion77, comentarioPublicacion78, comentarioPublicacion79, comentarioPublicacion80,
            comentarioPublicacion81, comentarioPublicacion82, comentarioPublicacion83, comentarioPublicacion84, comentarioPublicacion85,
            comentarioPublicacion86, comentarioPublicacion87, comentarioPublicacion88, comentarioPublicacion89, comentarioPublicacion90,
            comentarioPublicacion91, comentarioPublicacion92, comentarioPublicacion93, comentarioPublicacion94, comentarioPublicacion95,
            comentarioPublicacion96, comentarioPublicacion97, comentarioPublicacion98, comentarioPublicacion99, comentarioPublicacion100,
            comentarioPublicacion101, comentarioPublicacion102, comentarioPublicacion103, comentarioPublicacion104, comentarioPublicacion105,
            comentarioPublicacion106, comentarioPublicacion107, comentarioPublicacion108, comentarioPublicacion109, comentarioPublicacion110,
            comentarioPublicacion111, comentarioPublicacion112, comentarioPublicacion113, comentarioPublicacion114, comentarioPublicacion115,
            comentarioPublicacion116, comentarioPublicacion117, comentarioPublicacion118, comentarioPublicacion119, comentarioPublicacion120,
            comentarioPublicacion121, comentarioPublicacion122, comentarioPublicacion123, comentarioPublicacion124, comentarioPublicacion125,
            comentarioPublicacion126, comentarioPublicacion127, comentarioPublicacion128, comentarioPublicacion129, comentarioPublicacion130,
            comentarioPublicacion131, comentarioPublicacion132, comentarioPublicacion133, comentarioPublicacion134, comentarioPublicacion135
    ));

    objetoRepository.saveAll(Arrays.asList(objeto,
            objeto2, objeto3, objeto4, objeto5, objeto6, objeto7,
            objeto8, objeto9, objeto10, objeto11, objeto12, objeto13,
            objeto14, objeto15, objeto16, objeto17, objeto18, objeto19,
            objeto20, objeto21, objeto22, objeto23));


    log.info("Datos de entidades cargados correctamente.");


    log.info("Datos de entidades cargados correctamente.");

}



}

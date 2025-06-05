package com.atm.buenas_practicas_java.loaders;

import com.atm.buenas_practicas_java.entities.*;
import com.atm.buenas_practicas_java.repositories.*;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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


    public LocalDataLoader(ObjetoRepository objetoRepository,
                           PersonaRepository personaRepository,
                           PersonaObjetoRepository personaObjetoRepository,
                           UsuarioRepository usuarioRepository,
                           ResenaRepository resenaRepository,
                           TipoRepository tipoRepository,
                           GeneroRepository generoRepository,
                           ComentarioPublicacionRepository comentarioPublicacionRepository,
                           PublicacionRepository publicacionRepository,
                           ComunidadRepository comunidadRepository,
                           ComentarioResenaRepository comentarioResenaRepository) {
        this.objetoRepository = objetoRepository;
        this.personaRepository = personaRepository;
        this.personaObjetoRepository = personaObjetoRepository;
        this.usuarioRepository = usuarioRepository;
        this.resenaRepository = resenaRepository;
        this.tipoRepository = tipoRepository;
        this.generoRepository = generoRepository;
        this.comentarioPublicacionRepository = comentarioPublicacionRepository;
        this.publicacionRepository = publicacionRepository;
        this.comunidadRepository = comunidadRepository;
        this.comentarioResenaRepository = comentarioResenaRepository;
    }

    @PostConstruct
    public void loadDataLocal() {

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
        objeto.setTitulo("Harry Potter");
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
        objeto8.setDescripcion("Pulp fiction es una película que trata de " +
                " Lorem ipsum dolor sit amet, consectetur adipisicing elit. Autem commodi" +
                " delectus, deleniti dolorem dolores ducimus eos ex facere laudantium magnam minus nihil odit quaerat" +
                " quibusdam quisquam quos repellat sunt vitae");
        objeto8.setDuracionMinutos(154);
        objeto8.setTipo(tipoPeliculas);
        objeto8.setImagenUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1cYyaS50kL0g7JCqP8Dd3-T1NFmcVoFbQcQ&s");
        objeto8.setTrailerUrl("https://www.youtube.com/embed/ZFYCXAG6fdo");
        objeto8.setFechaPublicacion(LocalDate.of(1995, 1, 13));

        objeto8.setGeneros(new HashSet<>(Arrays.asList(generoComediaP, generoMafiaP, generoAccionP, generoSuspenseP)));

        Objeto objeto9 = new Objeto();
        objeto9.setTitulo("El club de la lucha");
        objeto9.setDescripcion("El club de la lucha es una película que trata de " +
                " Lorem ipsum dolor sit amet, consectetur adipisicing elit. Autem commodi" +
                " delectus, deleniti dolorem dolores ducimus eos ex facere laudantium magnam minus nihil odit quaerat" +
                " quibusdam quisquam quos repellat sunt vitae");
        objeto9.setDuracionMinutos(139);
        objeto9.setTipo(tipoPeliculas);
        objeto9.setImagenUrl("https://www.cinepazmadrid.es/data/fotos/afiche5-elclubdelalucha.jpg");
        objeto9.setTrailerUrl("https://www.youtube.com/embed/iBsiL3NaqhE");
        objeto9.setFechaPublicacion(LocalDate.of(1999, 11, 5));

        objeto9.setGeneros(new HashSet<>(Arrays.asList(generoAccionP, generoSuspenseP, generoDramaP)));

        Objeto objeto10 = new Objeto();
        objeto10.setTitulo("Misión: Imposible - Sentencia Final");
        objeto10.setDescripcion("Misión: Imposible - Sentencia Final es una película que trata de " +
                " Lorem ipsum dolor sit amet, consectetur adipisicing elit. Autem commodi" +
                " delectus, deleniti dolorem dolores ducimus eos ex facere laudantium magnam minus nihil odit quaerat" +
                " quibusdam quisquam quos repellat sunt vitae");
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
        objeto23.setImagenUrl("https://static.wikia.nocookie.net/mario/images/2/20/Super_Mario_World_SFC_JAP.png/revision/latest?cb=20180531002139&path-prefix=es");
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
        Usuario usuario1 = new Usuario();
        usuario1.setNombreUsuario("Usuario1");
        usuario1.setEmail("hola@gmail.com");
        usuario1.setContrasena("1234");
        usuario1.setRole("USER");

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
        resena1.setAbuso(true);

        ComentarioResena comentarioResena1 = new ComentarioResena();
        comentarioResena1.setResena(resena1);
        comentarioResena1.setUsuario(usuario2);
        comentarioResena1.setContenido("La verdad es que tu reseña se ha quedao flojilla. Podrías haber añadido esto:" +
                " Lorem ipsum dolor sit amet, consectetur adipisicing elit. Autem commodi\" +\n" +
                "                \" delectus, deleniti dolorem dolores ducimus eos ex facere laudantium magnam minus nihil odit quaerat\" +\n" +
                "                \" quibusdam quisquam quos repellat sunt vitae.");
        comentarioResena1.setAbuso(true);

        ComentarioResena comentarioResena2 = new ComentarioResena();
        comentarioResena2.setResena(resena1);
        comentarioResena2.setUsuario(usuario3);
        comentarioResena2.setContenido("Me ha gustao mucho tu reseña, mi pana. Ánimo con lo tuyo.");
        comentarioResena2.setAbuso(true);

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
        resena2.setAbuso(true);


        resenaRepository.saveAll(Arrays.asList(resena1, resena2));

        comentarioResenaRepository.saveAll(Arrays.asList(comentarioResena1, comentarioResena2));

        // Datos de prueba para las publicaciones, comunidades y comentarios


        //Publicaciones de prueba

        Publicacion publicacion1 = new Publicacion();
        publicacion1.setTitulo("Recomendaciones para ver la película _________ de harry potter");

        Publicacion publicacion2 = new Publicacion();
        publicacion2.setTitulo("Vendo Opel Corsa 250mil KM como nuevo");

        Publicacion publicacion3 = new Publicacion();
        publicacion3.setTitulo("Ron estaba de relleno, no pinta nada en las pelis");

        //Comunidades de prueba

        Comunidad comunidad1 = new Comunidad();
        comunidad1.setNombreComunidad("Harry Potter");
        comunidad1.setDescripcion("En la comunidad de Harry Potter podrás hablar de todas " +
                "las películas, series y videojuegos relacionados. ¡Anímate y haz una publicación!");
        comunidad1.setObjetos(Arrays.asList(objeto));
        comunidad1.setPublicaciones(Arrays.asList(publicacion1, publicacion2, publicacion3));
        comunidad1.setUsuarios(Arrays.asList(usuario1, usuario2, usuario3));
        comunidad1.setUrlImg("https://static.posters.cz/image/1300/104639.jpg");

        Comunidad comunidad2 = new Comunidad();
        comunidad2.setNombreComunidad("Interstellar");
        comunidad2.setDescripcion("En la comunidad de Interstellar podrás hablar de todas " +
                "las películas, series y videojuegos relacionados. ¡Anímate y haz una publicación!");
        comunidad2.setObjetos(Arrays.asList(objeto2));
//        comunidad2.setPublicaciones(Arrays.asList(publicacion2));
//        comunidad2.setUsuarios(Arrays.asList(usuario1, usuario2, usuario3));
        comunidad2.setUrlImg("https://m.media-amazon.com/images/M/MV5BYzdjMDAxZGItMjI2My00ODA1LTlkNzItOWFjMDU5ZDJlYWY3XkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg");

        Comunidad comunidad3 = new Comunidad();
        comunidad3.setNombreComunidad("The Gentlemen");
        comunidad3.setDescripcion("En la comunidad de The Gentlemen podrás hablar de todas " +
                "las películas, series y videojuegos relacionados. ¡Anímate y haz una publicación!");
        comunidad3.setObjetos(Arrays.asList(objeto3));
//        comunidad3.setPublicaciones(Arrays.asList(publicacion1));
//        comunidad3.setUsuarios(Arrays.asList(usuario1, usuario2, usuario3));
        comunidad3.setUrlImg("https://pics.filmaffinity.com/The_Gentlemen_Los_seanores_de_la_mafia-425828685-large.jpg");

        objeto.setComunidad(comunidad1);
        objeto2.setComunidad(comunidad2);
        objeto3.setComunidad(comunidad3);


        publicacion1.setComunidad(comunidad1);
        publicacion2.setComunidad(comunidad1);
        publicacion3.setComunidad(comunidad1);


        ComentarioPublicacion comentarioPublicacion1 = new ComentarioPublicacion();
        comentarioPublicacion1.setPublicacion(publicacion1);
        comentarioPublicacion1.setUsuario(usuario1);
        comentarioPublicacion1.setContenido("Este es el primer comentario de la publicación 1." +
                " Prueba para ver si sólo sale el primero de todos los comentarios de cada una de" +
                "las publicaciones asociadas a la comunidad.");
        comentarioPublicacion1.setFecha(LocalDateTime.of(2025, 5, 25, 16, 32, 28));

        ComentarioPublicacion comentarioPublicacion2 = new ComentarioPublicacion();
        comentarioPublicacion2.setPublicacion(publicacion1);
        comentarioPublicacion2.setUsuario(usuario2);
        comentarioPublicacion2.setContenido("Este es el segundo comentario de la publicación 1." +
                " Prueba para ver si sólo sale el primero de todos lso comentarios de cada una de" +
                " las publicaciones asociadas a la comunidad.");
        comentarioPublicacion2.setFecha(LocalDateTime.of(2025, 5, 25, 16, 39, 14));
        comentarioPublicacion2.setAbuso(true);

        ComentarioPublicacion comentarioPublicacion3 = new ComentarioPublicacion();
        comentarioPublicacion3.setPublicacion(publicacion1);
        comentarioPublicacion3.setUsuario(usuario3);
        comentarioPublicacion3.setContenido("Este es el tercer comentario de la publicación 1." +
                " Prueba para ver si sólo sale el primero de todos lso comentarios de cada una de" +
                " las publicaciones asociadas a la comunidad.");
        comentarioPublicacion3.setFecha(LocalDateTime.of(2025, 5, 25, 16, 48, 54));

        publicacion1.setComentariosPublicacion(Arrays.asList(comentarioPublicacion1, comentarioPublicacion2, comentarioPublicacion3));

        ComentarioPublicacion comentarioPublicacion4 = new ComentarioPublicacion();
        comentarioPublicacion4.setPublicacion(publicacion2);
        comentarioPublicacion4.setUsuario(usuario2);
        comentarioPublicacion4.setContenido("Este es el primer comentario de la publicación 2." +
                " Prueba para ver si sólo sale el primero de todos los comentarios de cada una de" +
                "las publicaciones asociadas a la comunidad.");
        comentarioPublicacion4.setFecha(LocalDateTime.of(2025, 5, 26, 18, 36, 32));

        ComentarioPublicacion comentarioPublicacion5 = new ComentarioPublicacion();
        comentarioPublicacion5.setPublicacion(publicacion2);
        comentarioPublicacion5.setUsuario(usuario1);
        comentarioPublicacion5.setContenido("Este es el segundo comentario de la publicación 2." +
                " Prueba para ver si sólo sale el primero de todos lso comentarios de cada una de" +
                " las publicaciones asociadas a la comunidad.");
        comentarioPublicacion5.setFecha(LocalDateTime.of(2025, 5, 26, 18, 48, 41));
        comentarioPublicacion5.setAbuso(true);

        ComentarioPublicacion comentarioPublicacion6 = new ComentarioPublicacion();
        comentarioPublicacion6.setPublicacion(publicacion2);
        comentarioPublicacion6.setUsuario(usuario3);
        comentarioPublicacion6.setContenido("Este es el tercer comentario de la publicación 2." +
                " Prueba para ver si sólo sale el primero de todos lso comentarios de cada una de" +
                " las publicaciones asociadas a la comunidad.");
        comentarioPublicacion6.setFecha(LocalDateTime.of(2025, 5, 26, 19, 2, 12));

        publicacion2.setComentariosPublicacion(Arrays.asList(comentarioPublicacion4, comentarioPublicacion5, comentarioPublicacion6));

        ComentarioPublicacion comentarioPublicacion7 = new ComentarioPublicacion();
        comentarioPublicacion7.setPublicacion(publicacion3);
        comentarioPublicacion7.setUsuario(usuario3);
        comentarioPublicacion7.setContenido("Este es el primer comentario de la publicación 3." +
                " Prueba para ver si sólo sale el primero de todos los comentarios de cada una de" +
                "las publicaciones asociadas a la comunidad.");
        comentarioPublicacion7.setFecha(LocalDateTime.of(2025, 5, 27, 13, 34, 51));

        ComentarioPublicacion comentarioPublicacion8 = new ComentarioPublicacion();
        comentarioPublicacion8.setPublicacion(publicacion3);
        comentarioPublicacion8.setUsuario(usuario2);
        comentarioPublicacion8.setContenido("Este es el segundo comentario de la publicación 3." +
                " Prueba para ver si sólo sale el primero de todos lso comentarios de cada una de" +
                " las publicaciones asociadas a la comunidad.");
        comentarioPublicacion8.setFecha(LocalDateTime.of(2025, 5, 27, 14, 2, 23));

        ComentarioPublicacion comentarioPublicacion9 = new ComentarioPublicacion();
        comentarioPublicacion9.setPublicacion(publicacion3);
        comentarioPublicacion9.setUsuario(usuario1);
        comentarioPublicacion9.setContenido("Este es el tercer comentario de la publicación 3." +
                " Prueba para ver si sólo sale el primero de todos lso comentarios de cada una de" +
                " las publicaciones asociadas a la comunidad.");
        comentarioPublicacion8.setFecha(LocalDateTime.of(2025, 5, 27, 14, 31, 48));

        publicacion3.setComentariosPublicacion(Arrays.asList(comentarioPublicacion7, comentarioPublicacion8, comentarioPublicacion9));


        comunidadRepository.saveAll(Arrays.asList(comunidad1, comunidad2, comunidad3));
        publicacionRepository.saveAll(Arrays.asList(publicacion1, publicacion2, publicacion3));
        comentarioPublicacionRepository.saveAll(Arrays.asList(comentarioPublicacion1,
                comentarioPublicacion2, comentarioPublicacion3, comentarioPublicacion4, comentarioPublicacion5,
                comentarioPublicacion6, comentarioPublicacion7, comentarioPublicacion8));

        objetoRepository.save(objeto);


        log.info("Datos de entidades cargados correctamente.");
    }
}

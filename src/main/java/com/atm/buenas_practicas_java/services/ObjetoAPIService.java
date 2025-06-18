package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.dtos.GeneroDTO;
import com.atm.buenas_practicas_java.dtos.ObjetoCrearDTO;
import com.atm.buenas_practicas_java.dtos.PersonaDTO;
import com.atm.buenas_practicas_java.dtos.auxiliarDTOs.CapituloDTO;
import com.atm.buenas_practicas_java.dtos.auxiliarDTOs.TemporadaDTO;
import info.movito.themoviedbapi.*;
import info.movito.themoviedbapi.model.core.Movie;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.core.TvSeriesResultsPage;
import info.movito.themoviedbapi.model.core.video.Video;
import info.movito.themoviedbapi.model.core.video.VideoResults;
import info.movito.themoviedbapi.model.movies.Credits;
import info.movito.themoviedbapi.model.movies.MovieDb;
import info.movito.themoviedbapi.model.people.PersonDb;
import info.movito.themoviedbapi.model.tv.core.TvSeason;
import info.movito.themoviedbapi.model.tv.season.TvSeasonDb;
import info.movito.themoviedbapi.model.tv.season.TvSeasonEpisode;
import info.movito.themoviedbapi.model.tv.series.TvSeriesDb;
import info.movito.themoviedbapi.tools.TmdbException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ObjetoAPIService {

    private final TmdbApi tmdbApi = new TmdbApi("eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3ZmQ3ZjI5ODZhNWU3M2I2NDA1YmY5ZGQ1ZDU4Y2MxMSIsIm5iZiI6MTc0OTY0ODQ4Mi43NzksInN1YiI6IjY4NDk4NDYyN2YzZGVmNmY4MTMwMjQ2ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.ueqIfjlrnRyU96FGrK-HQfEOyeBmQ1xvY-uXYtK3G_o");
    private final TmdbMovies tmdbMovies = new TmdbMovies(tmdbApi);
    private static final String BASE_PATH_FOTO = "https://image.tmdb.org/t/p/w342";
    private static final String BASE_PATH_VIDEO = "https://youtube.com/embed/";
    private static final String LENGUAJE = "es-ES";

    private String buscarTrailerUrlPelicula(Movie movie) {
        try {
            VideoResults videos = tmdbMovies.getVideos(movie.getId(), LENGUAJE);
            if (videos != null && videos.getResults() != null) {
                for (Video video : videos.getResults()) {
                    if ("Trailer".equalsIgnoreCase(video.getType()) && "YouTube".equalsIgnoreCase(video.getSite())) {
                        String key = video.getKey();
                        return BASE_PATH_VIDEO + key;
                    }
                }            }
        } catch (TmdbException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private String buscarCarteleraPelicula(Movie movie) {
        try {
            MovieDb movieDb = tmdbApi.getMovies().getDetails(movie.getId(), LENGUAJE);
            String posterPath = movieDb.getPosterPath();
            if (posterPath != null && !posterPath.isBlank()) {
                return BASE_PATH_FOTO + posterPath;
            } else {
                return null;
            }
        } catch (TmdbException e) {
            throw new RuntimeException(e);
        }
    }

    private List<GeneroDTO> buscarGenerosPelicula(Movie movie) throws TmdbException {
        MovieDb pelicula = tmdbMovies.getDetails(movie.getId(), LENGUAJE);
        if (pelicula.getGenres() == null) {
            return new ArrayList<>();
        }
        return pelicula.getGenres().stream()
                .map(genero -> new GeneroDTO(genero.getName()))
                .toList();
    }

    private PersonaDTO obtenerDetallesPersona(Integer idPersona) throws TmdbException {
        TmdbPeople tmdbPeople = tmdbApi.getPeople();
        PersonDb persona = tmdbPeople.getDetails(idPersona, LENGUAJE);
        if (persona == null) {
            return null;
        }

        String profilePath = persona.getProfilePath();
        String imagenUrl = null;
        if (profilePath != null && !profilePath.isBlank()) {
            imagenUrl = BASE_PATH_FOTO + profilePath;
        }

        return new PersonaDTO(
                persona.getName(),
                persona.getBirthday(),
                persona.getBiography(),
                imagenUrl
        );
    }


    private List<PersonaDTO> buscarActoresPelicula(Movie movie) throws TmdbException {
        Credits credits = tmdbMovies.getCredits(movie.getId(), LENGUAJE);
        if (credits == null ||credits.getCast() == null) {
            return new ArrayList<>();
        }

        return credits.getCast().stream()
                .limit(3)
                .map(cast -> {
                    try {
                        return obtenerDetallesPersona(cast.getId());
                    } catch (TmdbException e) {
                        return null;
                    }
                })
                .toList();
    }

    private List<PersonaDTO> buscarDirectoresPelicula(Movie movie) throws TmdbException {
        Credits credits = tmdbMovies.getCredits(movie.getId(), LENGUAJE);
        if (credits == null || credits.getCrew() == null) {
            return new ArrayList<>();
        }

        return credits.getCrew().stream()
                .filter(crew -> "Director".equalsIgnoreCase(crew.getJob()))
                .limit(3)
                .map(crew -> {
                    try {
                        return obtenerDetallesPersona(crew.getId());
                    } catch (TmdbException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList();
    }

    public List<ObjetoCrearDTO> buscarPeliculas(String query) throws TmdbException {
        TmdbSearch buscador = tmdbApi.getSearch();
        MovieResultsPage resultado = buscador.searchMovie(query, true, LENGUAJE, null, 0, null, null);
        List<Movie> peliculas = resultado.getResults();
        return peliculas.stream()
                .map(pelicula -> {
                    try {
                        return new ObjetoCrearDTO(
                                pelicula.getTitle(),
                                buscarCarteleraPelicula(pelicula),
                                buscarTrailerUrlPelicula(pelicula),
                                pelicula.getReleaseDate(),
                                pelicula.getOverview(),
                                buscarGenerosPelicula(pelicula),
                                buscarDirectoresPelicula(pelicula),
                                buscarActoresPelicula(pelicula),
                                pelicula.getId()
                        );
                    } catch (TmdbException e) {
                        throw new RuntimeException(e);
                    }
                }).toList();
    }

    public ObjetoCrearDTO obtenerDetallesCompletos(Integer tmdbId) throws TmdbException {
        MovieDb pelicula = tmdbMovies.getDetails(tmdbId, LENGUAJE);

        Movie movie = new Movie();
        movie.setId(pelicula.getId());
        movie.setTitle(pelicula.getTitle());
        movie.setReleaseDate(pelicula.getReleaseDate());

        return new ObjetoCrearDTO(
                pelicula.getTitle(),
                buscarCarteleraPelicula(movie),
                buscarTrailerUrlPelicula(movie),
                pelicula.getReleaseDate(),
                pelicula.getOverview(),
                buscarGenerosPelicula(movie),
                buscarDirectoresPelicula(movie),
                buscarActoresPelicula(movie),
                tmdbId
        );
    }

    /**
     * Funcionalidad de crear un nuevo capitulo
     */
    public List<ObjetoCrearDTO> buscarSeries(String query) throws TmdbException {
        TmdbSearch buscador = tmdbApi.getSearch();
        TvSeriesResultsPage resultado = buscador.searchTv(query, null, true, LENGUAJE, 0, null);
        return resultado.getResults().stream()
                .map(serie -> new ObjetoCrearDTO(
                        serie.getName(),
                        BASE_PATH_FOTO + serie.getPosterPath(),
                        buscarTrailerUrlSerie(serie.getId()),
                        serie.getFirstAirDate(),
                        serie.getOverview(),
                        new ArrayList<>(),
                        new ArrayList<>(),
                        new ArrayList<>(),
                        serie.getId()
                )).toList();
    }

    public ObjetoCrearDTO obtenerDetallesSerie(Integer tmdbId) throws TmdbException {
        TmdbTvSeries tmdbTv = tmdbApi.getTvSeries();
        TvSeriesDb serie = tmdbTv.getDetails(tmdbId, LENGUAJE);

        return new ObjetoCrearDTO(
                serie.getName(),
                BASE_PATH_FOTO + serie.getPosterPath(),
                buscarTrailerUrlSerie(tmdbId),
                serie.getFirstAirDate(),
                serie.getOverview(),
                obtenerGenerosSerie(serie),
                obtenerCreadoresSerie(serie),
                obtenerActoresPrincipalesSerie(tmdbId),
                tmdbId
        );
    }

    public List<TemporadaDTO> obtenerTemporadas(Integer tmdbId) throws TmdbException {
        TmdbTvSeries tmdbTv = tmdbApi.getTvSeries();
        TvSeriesDb serie = tmdbTv.getDetails(tmdbId, LENGUAJE);

        List<TemporadaDTO> temporadas = new ArrayList<>();
        for (TvSeason temporada : serie.getSeasons()) {
            TemporadaDTO temporadaDTO = new TemporadaDTO(temporada.getSeasonNumber());

            TmdbTvSeasons seasons = tmdbApi.getTvSeasons();
            TvSeasonDb temporadaDetalles = seasons.getDetails(tmdbId, temporada.getSeasonNumber(), LENGUAJE);

            for (TvSeasonEpisode episodio : temporadaDetalles.getEpisodes()) {
                temporadaDTO.agregarCapitulo(new CapituloDTO(
                        episodio.getEpisodeNumber(),
                        episodio.getName(),
                        episodio.getOverview(),
                        BASE_PATH_FOTO + episodio.getStillPath(),
                        episodio.getAirDate()
                ));
            }
            temporadas.add(temporadaDTO);
        }

        return temporadas;
    }

    public ObjetoCrearDTO obtenerDetallesCapitulo(Integer tmdbId, Integer temporada, Integer capitulo) throws TmdbException {
        TmdbTvSeasons seasons = tmdbApi.getTvSeasons();
        TvSeasonDb season = seasons.getDetails(tmdbId, temporada, LENGUAJE);

        TvSeasonEpisode episode = null;
        for (TvSeasonEpisode ep : season.getEpisodes()) {
            if (ep.getEpisodeNumber().equals(capitulo)) {
                episode = ep;
                break;
            }
        }

        if (episode == null) {
            throw new RuntimeException("Episodio no encontrado");
        }

        ObjetoCrearDTO serie = obtenerDetallesSerie(tmdbId);

        return new ObjetoCrearDTO(
                String.format("%s: T.%s Ep.%s - %s", serie.titulo(), temporada, capitulo, episode.getName()),
                BASE_PATH_FOTO + episode.getStillPath(),
                serie.trailerUrl(),
                episode.getAirDate(),
                episode.getOverview(),
                serie.generos(),
                serie.direccion(),
                serie.reparto(),
                episode.getId()
        );
    }

    private String buscarTrailerUrlSerie(Integer tmdbId) {
        try {
            VideoResults videos = tmdbApi.getTvSeries().getVideos(tmdbId, LENGUAJE);
            for (Video video : videos.getResults()) {
                if ("Trailer".equalsIgnoreCase(video.getType())) {
                    return BASE_PATH_VIDEO + video.getKey();
                }
            }
        } catch (TmdbException e) {
            e.printStackTrace();
        }
        return "";
    }

    private List<GeneroDTO> obtenerGenerosSerie(TvSeriesDb serie) {
        return serie.getGenres().stream()
                .map(genero -> new GeneroDTO(genero.getName()))
                .toList();
    }


    private List<PersonaDTO> obtenerCreadoresSerie(TvSeriesDb serie) {
        if (serie.getCreatedBy() == null) {
            return new ArrayList<>();
        }

        return serie.getCreatedBy().stream()
                .map(creator -> {
                    try {
                        return obtenerDetallesPersona(creator.getId());
                    } catch (TmdbException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
    }

    private List<PersonaDTO> obtenerActoresPrincipalesSerie(Integer tmdbId) throws TmdbException {
        info.movito.themoviedbapi.model.tv.core.credits.Credits credits = tmdbApi.getTvSeries().getCredits(tmdbId, LENGUAJE);
        if (credits == null || credits.getCast() == null) {
            return new ArrayList<>();
        }

        return credits.getCast().stream()
                .limit(3)
                .map(cast -> {
                    try {
                        PersonDb actorDetails = tmdbApi.getPeople().getDetails(cast.getId(), LENGUAJE);

                        return new PersonaDTO(
                                actorDetails.getName(),
                                actorDetails.getBirthday(),
                                actorDetails.getBiography(),
                                BASE_PATH_FOTO + actorDetails.getProfilePath()
                        );
                    } catch (TmdbException _) {
                        return new PersonaDTO(
                                cast.getName(),
                                null,"",
                                BASE_PATH_FOTO + cast.getProfilePath()
                        );
                    }
                })
                .toList();
    }
}

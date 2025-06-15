package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.dtos.ObjetoCrearDTO;
import com.atm.buenas_practicas_java.dtos.ObjetoDTO;
import com.atm.buenas_practicas_java.dtos.PersonaDTO;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.TmdbSearch;
import info.movito.themoviedbapi.model.core.Movie;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.core.video.Video;
import info.movito.themoviedbapi.model.core.video.VideoResults;
import info.movito.themoviedbapi.model.movies.Images;
import info.movito.themoviedbapi.model.movies.MovieDb;
import info.movito.themoviedbapi.tools.TmdbException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ObjetoAPIService {

    private final TmdbApi tmdbApi = new TmdbApi("eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3ZmQ3ZjI5ODZhNWU3M2I2NDA1YmY5ZGQ1ZDU4Y2MxMSIsIm5iZiI6MTc0OTY0ODQ4Mi43NzksInN1YiI6IjY4NDk4NDYyN2YzZGVmNmY4MTMwMjQ2ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.ueqIfjlrnRyU96FGrK-HQfEOyeBmQ1xvY-uXYtK3G_o");

    public String buscarTrailerUrlPelicula(Movie movie) {
        TmdbMovies tmdbMovies = tmdbApi.getMovies();
        try {
            VideoResults videos = tmdbMovies.getVideos(movie.getId(), "es-ES");
            if (videos != null && videos.getResults() != null) {
                for (Video video : videos.getResults()) {
                    if ("Trailer".equalsIgnoreCase(video.getType()) && "YouTube".equalsIgnoreCase(video.getSite())) {
                        String key = video.getKey();
                        String youtubeUrl = "https://youtube.com/embed/" + key;
                        return youtubeUrl;
                    }
                }
            }
        } catch (TmdbException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public String buscarCarteleraPelicula(Movie movie) {
        try {
            MovieDb movieDb = tmdbApi.getMovies().getDetails(movie.getId(), "es-ES");
            String posterPath = movie.getPosterPath();
            if (posterPath != null && !posterPath.isBlank()) {
                return "https://image.tmdb.org/t/p/w342/" + posterPath;
            } else {
                return null;
            }
        } catch (TmdbException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ObjetoCrearDTO> buscarPeliculas(String query) throws TmdbException {
        TmdbSearch buscador = tmdbApi.getSearch();
        MovieResultsPage resultado = buscador.searchMovie(query, false, "es-ES", null, 1, null, null);
        List<Movie> peliculas = resultado.getResults();
        return peliculas.stream()
                .map(pelicula -> new ObjetoCrearDTO(
                            pelicula.getTitle(),
                            buscarCarteleraPelicula(pelicula),
                            buscarTrailerUrlPelicula(pelicula),
                            LocalDateTime.now()
                )).toList();
    }
}

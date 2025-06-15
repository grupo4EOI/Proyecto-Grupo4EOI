package com.atm.buenas_practicas_java.services.facade;

import com.atm.buenas_practicas_java.dtos.*;
import com.atm.buenas_practicas_java.dtos.composedDTOs.EstadisticasReportesDTO;
import com.atm.buenas_practicas_java.dtos.composedDTOs.PanelAdminDTO;
import com.atm.buenas_practicas_java.entities.*;
import com.atm.buenas_practicas_java.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AdminServiceFacade {

    private final ResenaService resenaService;
    private final ComentarioResenaService comentarioResenaService;
    private final ComentarioPublicacionService comentarioPublicacionService;
    private final UsuarioService usuarioService;
    private final TipoService tipoService;
    private final GeneroService generoService;
    private final ObjetoService objetoService;
    private final PersonaService personaService;
    private final PersonaObjetoService personaObjetoService;

    public EstadisticasReportesDTO crearPanelAdmin() {
        return new EstadisticasReportesDTO(
                resenaService.contarResenasConAbuso(),
                comentarioResenaService.contarComentariosResenaConAbuso(),
                comentarioPublicacionService.contarComentariosPublicacionesConAbuso()
        );
    }

    public List<ResenaDTO> obtenerResenasConAbuso(String nombreUsuario) {
        return resenaService.obtenerResenasConAbuso(nombreUsuario);
    }

    public List<ComentarioResenaDTO> obtenerComentariosResenasConAbuso() {
        return comentarioResenaService.obtenerComentariosResenasConAbuso();
    }

    public List<ComentarioPublicacionDTO> obtenerComentariosPublicacionesConAbuso() {
        return comentarioPublicacionService.obtenerComentariosPublicacionConAbuso();
    }


    /**
     * Funcionalidades de borrar
     */
    // Este metodo (asociado al admin) borra también los comentarios asociados a la reseña
    public void borrarResena(Long idResena) {
        resenaService.eliminarResena(idResena);
    }

    public void borrarComentarioResena(Long idComentarioResena) {
        comentarioResenaService.borrarComentarioResena(idComentarioResena);
    }

    /**
     * Funcionalidades de banear
     */
    public void banComentarioPublicacion(Long idComentarioPublicacion) {
        comentarioPublicacionService.banComentarioPublicacion(idComentarioPublicacion);
    }

    public void banUsuario(Long idUsuario) {
        usuarioService.banUsuario(idUsuario);
    }

    /**
     * Funcionalidades de aprobar (quitar el ban reportado por el usuario)
     */
    public void aprobarResena(Long idResena) {
        resenaService.aprobarResena(idResena);
    }

    public void aprobarComentarioResena(Long idComentarioResena) {
        comentarioResenaService.aprobarComentarioResena(idComentarioResena);
    }

    public void aprobarComentarioPublicacion(Long idComentarioPublicacion) {
        comentarioPublicacionService.aprobarComentarioPublicacion(idComentarioPublicacion);
    }

    public void guardarPelicula(ObjetoCrearDTO objetoDTO) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Obtener tipo "Película"
        Tipo tipoPelicula = tipoService.findByNombre("pelicula");

        // Crear objeto principal
        Objeto objeto = new Objeto();
        objeto.setTitulo(objetoDTO.titulo());
        objeto.setDescripcion(objetoDTO.sinopsis());
        objeto.setFechaPublicacion(LocalDate.parse(objetoDTO.fechaPublicacion(), formatter));
        objeto.setImagenUrl(objetoDTO.imagenUrl());
        objeto.setTrailerUrl(objetoDTO.trailerUrl());
        objeto.setDuracionMinutos(0); // Valor temporal, se podría obtener de TMDB
        objeto.setTipo(tipoPelicula);

        // Guardar géneros
        Set<Genero> generos = new HashSet<>();
        for (GeneroDTO generoDTO : objetoDTO.generos()) {
            Genero genero = generoService.findByNombreAndTipo(generoDTO.nombre(), "pelicula")
                    .orElseGet(() -> {
                        Genero nuevoGenero = new Genero();
                        nuevoGenero.setNombre(generoDTO.nombre());
                        nuevoGenero.setTipo(tipoPelicula);
                        return generoService.save(nuevoGenero);
                    });
            generos.add(genero);
        }
        objeto.setGeneros(generos);

        // Guardar objeto principal
        Objeto objetoGuardado = objetoService.save(objeto);

        // Guardar directores y sus relaciones
        guardarPersonasConRol(objetoDTO.direccion(), objetoGuardado, true);

        // Guardar actores y sus relaciones
        guardarPersonasConRol(objetoDTO.reparto(), objetoGuardado, false);
    }

    private void guardarPersonasConRol(List<PersonaDTO> personasDTO,
                                       Objeto objeto,
                                       boolean esDirector) {
        for (PersonaDTO personaDTO : personasDTO) {
            // Buscar o crear persona
            Persona persona = personaService.findByNombreCompleto(personaDTO.nombreCompleto())
                    .orElseGet(() -> {
                        Persona nuevaPersona = new Persona();
                        nuevaPersona.setNombreCompleto(personaDTO.nombreCompleto());
                        if (personaDTO.fechaNacimiento() != null) {
                            nuevaPersona.setFechaNacimiento(
                                    LocalDate.parse(personaDTO.fechaNacimiento())
                            );
                        }
                        nuevaPersona.setBiografia(personaDTO.biografia());
                        nuevaPersona.setFotoUrl(personaDTO.fotoUrl());
                        return personaService.save(nuevaPersona);
                    });

            // Crear relación Persona-Objeto
            PersonaObjeto personaObjeto = new PersonaObjeto();
            personaObjeto.setPersona(persona);
            personaObjeto.setObjeto(objeto);
            personaObjeto.setRol(esDirector); // true = director, false = actor

            personaObjetoService.save(personaObjeto);
        }
    }
}

package com.atm.buenas_practicas_java.services.facade;

import com.atm.buenas_practicas_java.dtos.*;
import com.atm.buenas_practicas_java.dtos.composedDTOs.FichaObjetoDTO;
import com.atm.buenas_practicas_java.entities.*;
import com.atm.buenas_practicas_java.mapper.ComentarioResenaMapper;
import com.atm.buenas_practicas_java.mapper.FichaObjetoMapper;
import com.atm.buenas_practicas_java.mapper.ResenaCrearMapper;
import com.atm.buenas_practicas_java.services.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FichaObjetoFacade {

    private final ObjetoService objetoService;
    private final PersonaService personaService;
    private final UsuarioService usuarioService;
    private final ObjetoUsuarioService objetoUsuarioService;
    private final ResenaService resenaService;
    private final ComentarioResenaService comentarioResenaService;
    private final ComentarioPublicacionService comentarioPublicacionService;
    private final ResenaCrearMapper resenaCrearMapper;
    private final FichaObjetoMapper fichaObjetoMapper;
    private final ComentarioResenaMapper comentarioResenaMapper;


    // Metodo para el GetMapping de la ficha de objeto entera
    public FichaObjetoDTO construirFichaObjeto(Long idObjeto) {
        Objeto objeto = objetoService.findById(idObjeto);

        FichaObjetoDTO dto = fichaObjetoMapper.toDto(objeto);

        DecimalFormat df = new DecimalFormat("#.##");

        List<ComentarioPublicacionDTO> primerasPublicaciones = comentarioPublicacionService.getPrimerosComentariosObjeto(idObjeto);
        String puntuacion = df.format(objetoService.calcularPuntuacionObjeto(idObjeto));
        Integer numeroResenas = objetoService.calcularNumeroResenas(idObjeto);
        List<PersonaDTO> directores = personaService.getDirectoresByObjetoId(idObjeto);
        List<PersonaDTO> actores = personaService.getActoresByObjetoId(idObjeto);
        List<ResenaDTO> resenas = resenaService.findResenasByObjeto(idObjeto);
        return new FichaObjetoDTO(
                dto.idObjeto(),
                dto.titulo(),
                dto.descripcion(),
                dto.fechaYDuracion(),
                dto.imagenUrl(),
                dto.trailerUrl(),
                dto.tipo(),
                dto.generos(),
                resenas,
                primerasPublicaciones,
                puntuacion,
                numeroResenas,
                directores,
                actores,
                dto.comunidad()
        );
    }

    // Metodo para el PostMapping de nueva reseña
    public void agregarResena(Long idObjeto, ResenaCrearDTO resenaDTO, String nombreUsuario) {
        Usuario usuario = usuarioService.findByNombreUsuario(nombreUsuario);
        Objeto objeto = objetoService.findById(idObjeto);

        Resena entidadResena = resenaCrearMapper.toEntity(resenaDTO);
        entidadResena.setUsuario(usuario);
        entidadResena.setObjeto(objeto);
        entidadResena.setReacciones(new ArrayList<>());
        entidadResena.setComentariosResena(new ArrayList<>());

        objeto.getResenas().add(entidadResena);
        usuario.getResenas().add(entidadResena);

        objetoService.save(objeto);
        usuarioService.save(usuario);
        resenaService.save(entidadResena);
    }

    // Metodo para el PostMapping de nuevo comentario reseña
    @Transactional
    public void agregarComentarioResena(Long idResena, ComentarioResenaDTO comentarioResenaDTO, String nombreUsuario) {
        Usuario usuario = usuarioService.findByNombreUsuario(nombreUsuario);
        Resena resena = resenaService.findById(idResena);

        ComentarioResena comentarioEntidad = comentarioResenaMapper.toEntity(comentarioResenaDTO);
        comentarioEntidad.setUsuario(usuario);
        comentarioEntidad.setResena(resena);
        comentarioEntidad.setFecha(LocalDateTime.now());
        resena.getComentariosResena().add(comentarioEntidad);

        comentarioResenaService.save(comentarioEntidad);
        resenaService.save(resena);
    }

    // Metodo para postmapping de actualizar estado objeto (visto o pendiente)
    public void marcarEstadoObjeto(Long idObjeto, String nombreUsuario, Boolean estado) {
        usuarioService.marcarEstadoObjeto(idObjeto, nombreUsuario, estado);
    }

    // Metodo para putMapping de reportar reseña
    public void reportarResena(Long idResena) {
        resenaService.reportarResena(idResena);
    }

    // Metodo para putMapping de reportar comentario reseña
    public void reportarSpoilerResena(Long idResena) {
        resenaService.reportarSpoilerResena(idResena);
    }

}

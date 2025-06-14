package com.atm.buenas_practicas_java.services.facade;

import com.atm.buenas_practicas_java.dtos.ComentarioPublicacionDTO;
import com.atm.buenas_practicas_java.dtos.ComentarioResenaDTO;
import com.atm.buenas_practicas_java.dtos.ResenaDTO;
import com.atm.buenas_practicas_java.dtos.composedDTOs.EstadisticasReportesDTO;
import com.atm.buenas_practicas_java.dtos.composedDTOs.PanelAdminDTO;
import com.atm.buenas_practicas_java.services.ComentarioPublicacionService;
import com.atm.buenas_practicas_java.services.ComentarioResenaService;
import com.atm.buenas_practicas_java.services.ResenaService;
import com.atm.buenas_practicas_java.services.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceFacade {

    private final ResenaService resenaService;
    private final ComentarioResenaService comentarioResenaService;
    private final ComentarioPublicacionService comentarioPublicacionService;
    private final UsuarioService usuarioService;

    public AdminServiceFacade(ResenaService resenaService,
                              ComentarioResenaService comentarioResenaService,
                              ComentarioPublicacionService comentarioPublicacionService,
                              UsuarioService usuarioService) {
        this.resenaService = resenaService;
        this.comentarioResenaService = comentarioResenaService;
        this.comentarioPublicacionService = comentarioPublicacionService;
        this.usuarioService = usuarioService;
    }

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
}

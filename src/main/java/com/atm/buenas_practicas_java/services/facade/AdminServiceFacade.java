package com.atm.buenas_practicas_java.services.facade;

import com.atm.buenas_practicas_java.dtos.composedDTOs.PanelAdminDTO;
import com.atm.buenas_practicas_java.services.ComentarioPublicacionService;
import com.atm.buenas_practicas_java.services.ComentarioResenaService;
import com.atm.buenas_practicas_java.services.ResenaService;
import com.atm.buenas_practicas_java.services.UsuarioService;
import org.springframework.stereotype.Service;

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

    public PanelAdminDTO crearPanelAdminDTO(String nombreUsuario) {
        return new PanelAdminDTO(
                resenaService.obtenerResenasConAbuso(nombreUsuario),
                comentarioResenaService.obtenerComentariosResenasConAbuso(),
                comentarioPublicacionService.buscarComentariosPublicacionConAbuso()
        );
    }

    // Este metodo (asociado al admin) borra también los comentarios asociados a la reseña
    public void borrarResena(Long idResena) {
        resenaService.eliminarResena(idResena);
    }

    public void borrarComentarioResena(Long idComentarioResena) {
        comentarioResenaService.borrarComentarioResena(idComentarioResena);
    }

    public void banComentarioPublicacion(Long idComentarioPublicacion) {
        comentarioPublicacionService.banComentarioPublicacion(idComentarioPublicacion);
    }

    public void banUsuario(Long idUsuario) {
        usuarioService.banUsuario(idUsuario);
    }
}

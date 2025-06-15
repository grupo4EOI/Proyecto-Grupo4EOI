package com.atm.buenas_practicas_java.services.facade;

import com.atm.buenas_practicas_java.dtos.AjustesPerfilDTO;
import com.atm.buenas_practicas_java.dtos.UsuarioPerfilDTO;
import com.atm.buenas_practicas_java.entities.Usuario;
import com.atm.buenas_practicas_java.services.AjustesPerfilService;
import com.atm.buenas_practicas_java.services.GeneroService;
import com.atm.buenas_practicas_java.services.PerfilService;
import com.atm.buenas_practicas_java.services.UsuarioService;
import org.springframework.stereotype.Service;

@Service
public class PerfilServiceFacade {

    private final PerfilService perfilService;
    private final AjustesPerfilService ajustesPerfilService;
    private final GeneroService generoService;

    public PerfilServiceFacade(PerfilService perfilService, AjustesPerfilService ajustesPerfilService, GeneroService generoService) {
        this.perfilService = perfilService;
        this.ajustesPerfilService = ajustesPerfilService;
        this.generoService = generoService;
    }

    public UsuarioPerfilDTO obtenerPerfilDTO(Long idPerfil, Long idOtro) {
        return perfilService.obtenerPerfilDTO(idPerfil, idOtro);
    }


    public void editarBiografia(Long idUsuario, String biografia) {
        Usuario usuario = perfilService.findByIdUsuario(idUsuario);
        usuario.setBiografia(biografia);
        perfilService.saveAndFlush(usuario);
    }

    public Long obtenerIdUsuarioPorNombreUsuario(String nombreUsuario) {
        Usuario usuario = perfilService.findByNombreUsuario(nombreUsuario);
        return usuario.getIdUsuario();
    }


    public AjustesPerfilDTO obtenerAjustesPerfil(Long id) {
        return ajustesPerfilService.obtenerAjustesPerfil(id);
    }

    public void guardarAjustesPerfil(Long idUsuario, AjustesPerfilDTO ajustesPerfil) {
        ajustesPerfilService.actualizarAjustesPerfil(idUsuario, ajustesPerfil);
    }
}

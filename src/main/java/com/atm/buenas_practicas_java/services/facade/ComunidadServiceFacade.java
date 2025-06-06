package com.atm.buenas_practicas_java.services.facade;

import com.atm.buenas_practicas_java.dtos.ComunidadDTO;
import com.atm.buenas_practicas_java.dtos.ComunidadSimpleDTO;
import com.atm.buenas_practicas_java.dtos.PublicacionDTO;
import com.atm.buenas_practicas_java.mapper.ComentarioPublicacionMapper;
import com.atm.buenas_practicas_java.mapper.ComunidadMapper;
import com.atm.buenas_practicas_java.mapper.PublicacionMapper;
import com.atm.buenas_practicas_java.services.ComentarioPublicacionService;
import com.atm.buenas_practicas_java.services.ComunidadService;
import com.atm.buenas_practicas_java.services.PublicacionService;
import com.atm.buenas_practicas_java.services.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComunidadServiceFacade {
    private ComunidadService comunidadService;
    private PublicacionService publicacionService;
    private ComentarioPublicacionService comPubService;
    private UsuarioService usuarioService;
    private ComunidadMapper comunidadMapper;
    private PublicacionMapper publicacionMapper;
    private ComentarioPublicacionMapper comPubMapper;

    public ComunidadServiceFacade(ComunidadService comunidadService, PublicacionService publicacionService,
                                  ComentarioPublicacionService comPubService, UsuarioService usuarioService,
                                  ComunidadMapper comunidadMapper, PublicacionMapper publicacionMapper, ComentarioPublicacionMapper comPubMapper) {
        this.comunidadService = comunidadService;
        this.publicacionService = publicacionService;
        this.comPubService = comPubService;
        this.usuarioService = usuarioService;
        this.comunidadMapper = comunidadMapper;
        this.publicacionMapper = publicacionMapper;
        this.comPubMapper = comPubMapper;
    }

    public List<ComunidadDTO> buscarComunidades() {
        return comunidadService.buscarComunidades();
    }

    public ComunidadSimpleDTO findByID(Long id) {
        return comunidadService.findByID(id);
    }

    public List<PublicacionDTO> buscarPublicacionesPorComunidad(Long idComunidad) {
        return publicacionService.buscarPublicacionesPorComunidad(idComunidad);
    }

}

package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.dtos.ComunidadDTO;
import com.atm.buenas_practicas_java.dtos.ComunidadSimpleDTO;
import com.atm.buenas_practicas_java.entities.Comunidad;
import com.atm.buenas_practicas_java.mapper.ComunidadMapper;
import com.atm.buenas_practicas_java.mapper.ComunidadSimpleMapper;
import com.atm.buenas_practicas_java.repositories.ComunidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComunidadService {

    private final ComunidadRepository comunidadRepository;
    private final ComunidadMapper comunidadMapper;
    private final ComunidadSimpleMapper comunidadSimpleMapper;

    public ComunidadService(ComunidadRepository comunidadRepository, ComunidadMapper comunidadMapper, ComunidadSimpleMapper comunidadSimpleMapper) {
        this.comunidadRepository = comunidadRepository;
        this.comunidadMapper = comunidadMapper;
        this.comunidadSimpleMapper = comunidadSimpleMapper;
    }

    public Comunidad save (Comunidad comunidad) {return comunidadRepository.save(comunidad);}

    public List<Comunidad> findAll() {return comunidadRepository.findAll();}

    public Comunidad findById(Long id) {return comunidadRepository.findComunidadByIdComunidad(id);}

    public ComunidadSimpleDTO findByID(Long id) {
        return comunidadSimpleMapper.toDTO(comunidadRepository.findComunidadByIdComunidad(id));
    }

    public List<ComunidadDTO> buscarComunidades(){
        List<ComunidadDTO> comunidadesDTO = comunidadMapper.toDTO(findAll());
        return comunidadesDTO.stream()
                .map(comunidadDTO -> {
                    Comunidad comunidad = findById(comunidadDTO.idComunidad());
                    return new ComunidadDTO(
                        comunidadDTO.idComunidad(),
                        comunidadDTO.nombreComunidad(),
                        comunidadDTO.descripcion(),
                        comunidadDTO.urlImg(),
                        comunidad.getPublicaciones().size(),
                        comunidad.getUsuarios().size()
                    );
                }).toList();
    }
}

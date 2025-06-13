package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.dtos.ObjetoDTO;
import com.atm.buenas_practicas_java.dtos.composedDTOs.SeccionDTO;
import com.atm.buenas_practicas_java.entities.Objeto;
import com.atm.buenas_practicas_java.entities.Resena;
import com.atm.buenas_practicas_java.mapper.ObjetoMapper;
import com.atm.buenas_practicas_java.repositories.ObjetoRepository;
import com.atm.buenas_practicas_java.repositories.ResenaRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class ObjetoService {

    private final ObjetoRepository objetoRepository;
    private final ResenaRepository resenaRepository;
    private final ObjetoMapper objetoMapper;

    public ObjetoService(ObjetoRepository objetoRepository, ResenaRepository resenaRepository, ObjetoMapper objetoMapper) {
        this.objetoRepository = objetoRepository;
        this.resenaRepository = resenaRepository;
        this.objetoMapper = objetoMapper;
    }

    public Objeto save(Objeto objeto) {
        return objetoRepository.save(objeto);
    }

    public List<Objeto> findAll() {
        return objetoRepository.findAll();
    }

    public Objeto findById(Long id) {
        return objetoRepository.findById(id).orElseThrow(() -> new RuntimeException("Objeto no encontrado"));
    }

    public Double calcularPuntuacionObjeto(Long idObjeto) {
        List<Resena> resenas = resenaRepository.findResenasByObjeto_IdObjeto(idObjeto);
        if (resenas.isEmpty()) {
            return 0.0;
        }
        return resenas.stream().mapToDouble(Resena::getPuntuacion).sum() / (resenas.size());
    }

    public int calcularNumeroResenas(Long idObjeto) {
        return resenaRepository.findResenasByObjeto_IdObjeto(idObjeto).size();
    }

    private List<ObjetoDTO> rellenarListaObjetosDTO(List<ObjetoDTO> objetosDTO) {
        DecimalFormat df = new DecimalFormat("#.##");
        return objetosDTO.stream()
                .map(objeto -> new ObjetoDTO(
                        objeto.idObjeto(),
                        objeto.titulo(),
                        objeto.imagenUrl(),
                        objeto.trailerUrl(),
                        objeto.anoPublicacion(),
                        df.format(calcularPuntuacionObjeto(objeto.idObjeto())),
                        calcularNumeroResenas(objeto.idObjeto())
                )).toList();
    }

    public List<ObjetoDTO> obtenerObjetosMasRecientesPorTipo(String tipo, Limit limit) {
        List<Objeto> objetos = objetoRepository.findObjetosByTipo_NombreOrderByFechaPublicacionDesc(tipo, limit);
        List<ObjetoDTO> objetosMasRecientes = objetoMapper.toDtoList(objetos);
        return rellenarListaObjetosDTO(objetosMasRecientes);
    }

    public List<ObjetoDTO> obtenerObjetosMejorValoradosPorTipo(String tipo, Limit limit) {
        List<Objeto> objetos = objetoRepository.buscarListaObjetosMejorValoradosPorTipo(tipo, limit);
        List<ObjetoDTO> objetosMasValorados = objetoMapper.toDtoList(objetos);
        return rellenarListaObjetosDTO(objetosMasValorados);
    }

    public List<ObjetoDTO> obtenerObjetosMasPopularesPorTipo(String tipo, Limit limit) {
        List<Objeto> objetos = objetoRepository.buscarListaObjetosMasPopularesPorTipo(tipo, limit);
        List<ObjetoDTO> objetosMasVistos = objetoMapper.toDtoList(objetos);
        return rellenarListaObjetosDTO(objetosMasVistos);
    }

    @Transactional
    public SeccionDTO construirSeccionDTO(String tipo) {
        String tipoSeccion = "";
        if (tipo.equals("pelicula")) {
            tipoSeccion = "Películas";
        } else if (tipo.equals("serie")) {
            tipoSeccion = "Series";
        } else {
            tipoSeccion = "Videojuegos";
        }

        return new SeccionDTO(
                tipoSeccion,
                obtenerObjetosMasRecientesPorTipo(tipo, Limit.of(8)),
                obtenerObjetosMejorValoradosPorTipo(tipo, Limit.of(8))
        );
    }
}

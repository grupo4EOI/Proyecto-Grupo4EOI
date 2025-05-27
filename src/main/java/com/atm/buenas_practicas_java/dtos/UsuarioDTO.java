package com.atm.buenas_practicas_java.dtos;


import com.atm.buenas_practicas_java.entities.Amistad;
import com.atm.buenas_practicas_java.entities.ComentarioResena;
import com.atm.buenas_practicas_java.entities.GeneroUsuario;
import com.atm.buenas_practicas_java.entities.Reaccion;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class UsuarioDTO {
    private Long idUsuario;
    private String nombreUsuario;
    private String email;
    private String contrasena;
    private String avatarUrl;
    private String biografia;
    private LocalDateTime ultimaConexion;
    private List<ResenaDTO> resenas;
    private Set<ComentarioPublicacionDTO> comentariosPublicaciones;
    private Set<AmistadDTO> amigos;
    private List<ReaccionDTO> reacciones;
    private Set<ComentarioResenaDTO> comentariosResenas;
    private Set<String> generosUsuario;
}

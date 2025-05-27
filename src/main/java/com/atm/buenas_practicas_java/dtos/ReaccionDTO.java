package com.atm.buenas_practicas_java.dtos;

import com.atm.buenas_practicas_java.entities.ComentarioPublicacion;
import com.atm.buenas_practicas_java.entities.ComentarioResena;
import com.atm.buenas_practicas_java.entities.Resena;
import com.atm.buenas_practicas_java.entities.Usuario;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

public class ReaccionDTO {
    private Long idReaccion;
    private Boolean meGusta;
    private UsuarioDTO usuario;
    private ComentarioPublicacionDTO comentarioPublicacion;
    private ResenaDTO resena;
    private ComentarioResenaDTO comentarioResena;
}

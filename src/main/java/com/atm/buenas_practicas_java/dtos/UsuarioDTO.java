package com.atm.buenas_practicas_java.dtos;


import com.atm.buenas_practicas_java.entities.*;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


public record UsuarioDTO(
        Long idUsuario,
        String nombreUsuario,
        String avatarUrl,
        String role,
        Boolean baneado
) { }

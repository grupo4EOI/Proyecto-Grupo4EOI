package com.atm.buenas_practicas_java.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usuarios")
public class Usuario implements UserDetails, CredentialsContainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INTEGER")
    private Long idUsuario;
    @Column(unique = true, nullable = false)
    private String nombreUsuario;
    @Column(unique = true, nullable = false)
    private String email;
    @NotNull
    private String contrasena;
    private LocalDateTime fechaRegistro;
    private String avatarUrl;
    private String biografia;
    private LocalDateTime ultimaConexion;
    @Column(nullable = false)
    private String role;

    // Relación 1:N con reseñas
    @OneToMany(mappedBy = "usuario")
    private List<Resena> resenas;

    // Relación M:N entre las tablas objetos y usuarios
    @OneToMany(mappedBy = "usuario")
    private Set<ObjetoUsuario> objetos;

    // Relación con comentarios de las publicaciones
    @OneToMany(mappedBy = "usuario")
    private Set<ComentarioPublicacion> comentariosPublicacion;

    // Relación con comunidad
    @ManyToMany(mappedBy = "usuarios")
    private List<Comunidad> comunidades;

    // Relación con la tabla Amistad (M:N autorelación de Usuario)
    @OneToMany
    @JoinColumn(name = "id_usuario")
    private Set<Amistad> usuarios;

    @OneToMany
    @JoinColumn(name = "id_amigo")
    private Set<Amistad> amigos;

    @OneToMany(mappedBy = "usuario")
    private List<Reaccion> reacciones;

    @OneToMany(mappedBy = "usuario")
    private Set<ComentarioResena> comentariosResenas;

    @ManyToMany
    @JoinTable(
            name = "generos_usuarios",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_genero")
    )
    private Set<Genero> generos;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return nombreUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void eraseCredentials() {

    }
}

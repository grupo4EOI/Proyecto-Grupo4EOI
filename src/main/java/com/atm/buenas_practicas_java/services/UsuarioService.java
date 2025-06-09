package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.entities.ComentarioPublicacion;
import com.atm.buenas_practicas_java.entities.Publicacion;
import com.atm.buenas_practicas_java.entities.UserForm;
import com.atm.buenas_practicas_java.entities.Usuario;
import com.atm.buenas_practicas_java.repositories.PublicacionRepository;
import com.atm.buenas_practicas_java.repositories.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PublicacionRepository publicacionRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PublicacionRepository publicacionRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.publicacionRepository = publicacionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public Usuario findUsuarioByPublicacion(Long idPublicacion) {
        Publicacion publicacion = publicacionRepository.findById(idPublicacion).get();
        ComentarioPublicacion comentario = publicacion.getComentariosPublicacion().getFirst();
        return comentario.getUsuario();
    }

    public List<Usuario> findUsuariosByPublicaciones(List<Publicacion> publicaciones) {
        List<Usuario> usuarios = new ArrayList<>();
        for (Publicacion publicacion : publicaciones) {
            usuarios.add(findUsuarioByPublicacion(publicacion.getIdPublicacion()));
        }
        return usuarios;
    }

    public Usuario findByNombreUsuario(String nombreUsuario) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new UsernameNotFoundException("Email " +
                        nombreUsuario + " not found"));

        return User.builder()
                .username(usuario.getNombreUsuario())
                .password(usuario.getContrasena())
                .roles(usuario.getRole())
                .build();
    }

    public void registerUser(UserForm userForm) {
        Usuario usuario = userForm.toUserWithPassword(passwordEncoder);
        usuario.setContrasena(passwordEncoder.encode(userForm.getContrasena()));
        usuarioRepository.save(usuario);
    }

}

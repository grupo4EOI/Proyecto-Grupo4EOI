package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.dtos.UsuarioDTO;
import com.atm.buenas_practicas_java.entities.*;
import com.atm.buenas_practicas_java.mapper.UsuarioMapper;
import com.atm.buenas_practicas_java.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final ObjetoUsuarioRepository objetoUsuarioRepository;
    private final ObjetoRepository objetoRepository;
    private final UsuarioMapper usuarioMapper;
    private final AmistadRepository amistadRepository;

    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public void saveAndFlush(Usuario usuario) {
        usuarioRepository.saveAndFlush(usuario);
    }

    public Usuario findById(Long idUsuario) {
        return usuarioRepository.findById(idUsuario).orElseThrow(EntityNotFoundException::new);
    }

    public Usuario findByNombreUsuario(String nombreUsuario) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

    public boolean existsByNombreUsuarioAndIdNot(String nombreUsuario, Long idUsuario) {
        return usuarioRepository.existsByNombreUsuarioAndIdUsuarioNot(nombreUsuario, idUsuario);
    }

    // Métodos relacionados con el usuario en la ficha de objeto
    @Transactional
    public void marcarEstadoObjeto(Long idObjeto, String nombreUsuario, Boolean estado) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario).orElseThrow(EntityNotFoundException::new);
        Objeto objeto = objetoRepository.findById(idObjeto).orElseThrow(EntityNotFoundException::new);

        Optional<ObjetoUsuario> existente = objetoUsuarioRepository.findByObjeto_IdObjetoAndUsuario_IdUsuario(idObjeto, usuario.getIdUsuario());

        if (existente.isPresent()) {
            ObjetoUsuario registro = existente.get();
            registro.setEstado(estado);
            objetoUsuarioRepository.save(registro);
        } else {
            ObjetoUsuario nuevo = new ObjetoUsuario();
            nuevo.setUsuario(usuario);
            nuevo.setObjeto(objeto);
            nuevo.setEstado(estado);
            objetoUsuarioRepository.save(nuevo);
        }
    }

    @Transactional
    public void marcarObjetoFavorito(Long idObjeto, String nombreUsuario, Boolean favorito) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario).orElseThrow(EntityNotFoundException::new);
        Objeto objeto = objetoRepository.findById(idObjeto).orElseThrow(EntityNotFoundException::new);

        Optional<ObjetoUsuario> existente = objetoUsuarioRepository.findByObjeto_IdObjetoAndUsuario_IdUsuario(idObjeto, usuario.getIdUsuario());

        if (existente.isPresent()) {
            ObjetoUsuario registro = existente.get();
            registro.setFavorito(favorito);
            objetoUsuarioRepository.save(registro);
        } else {
            ObjetoUsuario nuevo = new ObjetoUsuario();
            nuevo.setUsuario(usuario);
            nuevo.setObjeto(objeto);
            nuevo.setFavorito(favorito);
            objetoUsuarioRepository.save(nuevo);
        }
    }

    // Métodos panel admin
    public void banUsuario(Long idUsuario) {
        usuarioRepository.banUsuario(idUsuario);
    }

    // Métodos para perfil de usuario
    public List<UsuarioDTO> buscarAmigosUsuario(Long idUsuario) {
        return usuarioMapper.toDtoList(usuarioRepository.buscarAmistadesUsuario(idUsuario));
    }

    public boolean sonAmigos(Long id1, Long id2) {
        Usuario u1 = findById(id1);
        Usuario u2 = findById(id2);
        return amistadRepository.findByUsuarioAndAmigo(u1, u2).isPresent()
                || amistadRepository.findByUsuarioAndAmigo(u2, u1).isPresent();
    }


    // Métodos para el registro y el login
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

    public boolean existeNombreUsuario(String nombreUsuario) {
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

    public boolean existeEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    @Transactional
    public void registerUser(UserForm userForm) {
        if (existeNombreUsuario(userForm.getNombreUsuario())) {
            throw new IllegalArgumentException("El nombre de usuario ya está en uso");
        }

        if (existeEmail(userForm.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(userForm.getNombreUsuario());
        usuario.setEmail(userForm.getEmail());
        usuario.setContrasena(passwordEncoder.encode(userForm.getContrasena()));
        usuario.setRole("USER"); // Rol por defecto
        usuario.setFechaRegistro(LocalDateTime.now());
        usuario.setBaneado(false);

        usuarioRepository.save(usuario);
    }
}

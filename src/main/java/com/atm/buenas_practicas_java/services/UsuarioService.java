package com.atm.buenas_practicas_java.services;

import com.atm.buenas_practicas_java.dtos.UsuarioDTO;
import com.atm.buenas_practicas_java.entities.*;
import com.atm.buenas_practicas_java.mapper.UsuarioMapper;
import com.atm.buenas_practicas_java.repositories.ObjetoRepository;
import com.atm.buenas_practicas_java.repositories.ObjetoUsuarioRepository;
import com.atm.buenas_practicas_java.repositories.PublicacionRepository;
import com.atm.buenas_practicas_java.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PublicacionRepository publicacionRepository;
    private final PasswordEncoder passwordEncoder;
    private final ObjetoUsuarioRepository objetoUsuarioRepository;
    private final ObjetoRepository objetoRepository;
    private final UsuarioMapper usuarioMapper;

    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public Usuario findById(Long idUsuario) {
        return usuarioRepository.findById(idUsuario).orElseThrow(EntityNotFoundException::new);
    }

    public Usuario findByNombreUsuario(String nombreUsuario) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
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

    public void registerUser(UserForm userForm) {
        Usuario usuario = userForm.toUserWithPassword(passwordEncoder);
        usuario.setContrasena(passwordEncoder.encode(userForm.getContrasena()));
        usuarioRepository.save(usuario);
    }

}

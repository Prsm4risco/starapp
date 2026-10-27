package com.cefetmg.starapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cefetmg.starapp.entity.Usuario;
import com.cefetmg.starapp.repository.UsuarioRepository;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByLogin(login)
                .orElseThrow(() ->
                    new UsernameNotFoundException("Usuário não encontrado.")
                );

        return User.builder()
            .username(usuario.getLogin())
            .password(usuario.getSenha())
            .authorities(usuario.getTipoUsuario().name())
            .build();
    }
}
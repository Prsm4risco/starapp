package com.cefetmg.starapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefetmg.starapp.dto.AutenticacaoRequestDTO;
import com.cefetmg.starapp.dto.AutenticacaoResponseDTO;
import com.cefetmg.starapp.entity.Usuario;
import com.cefetmg.starapp.exception.BusinessException;
import com.cefetmg.starapp.exception.ResourceNotFoundException;
import com.cefetmg.starapp.repository.UsuarioRepository;
import com.cefetmg.starapp.security.JwtService;

@Service
public class AutenticacaoService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    @Transactional(readOnly = true)
    public AutenticacaoResponseDTO autenticar(AutenticacaoRequestDTO dto) {

        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            dto.getLogin(),
                            dto.getSenha()));

        } catch (AuthenticationException e) {
            throw new BusinessException("Login ou senha inválidos.");
        }
        Usuario usuario = usuarioRepository.findByLogin(dto.getLogin())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));

        String token = jwtService.gerarToken(usuario);

        return new AutenticacaoResponseDTO(token);
    }
}
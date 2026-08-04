package com.cefetmg.starapp.dto;

import com.cefetmg.starapp.entity.TipoUsuario;
import com.cefetmg.starapp.entity.Usuario;

import lombok.Getter;

@Getter
public class AutenticacaoResponseDTO {

    private Long id;
    private String login;
    private TipoUsuario tipoUsuario;

    public AutenticacaoResponseDTO(Usuario usuario) {
        id = usuario.getId();
        login = usuario.getLogin();
        tipoUsuario = usuario.getTipoUsuario();
    }
}
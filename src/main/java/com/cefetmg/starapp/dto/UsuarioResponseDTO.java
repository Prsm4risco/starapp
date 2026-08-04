package com.cefetmg.starapp.dto;

import com.cefetmg.starapp.entity.TipoUsuario;
import com.cefetmg.starapp.entity.Usuario;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioResponseDTO {


    private Long id;
    private String login;
    private String senha;
    private TipoUsuario tipoUsuario;

    public UsuarioResponseDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.login = usuario.getLogin();
        this.senha = usuario.getSenha();
        this.tipoUsuario = usuario.getTipoUsuario();
    }
}

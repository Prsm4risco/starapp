package com.cefetmg.starapp.dto;

import com.cefetmg.starapp.entity.TipoUsuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsuarioRequestDTO {


    @NotBlank(message = "O campo login é obrigatório")
    private String login;
    
    @NotNull(message = "O campo senha é obrigatório")
    private String senha;
    
    @NotBlank(message = "O campo login é obrigatório")
    private TipoUsuario tipoUsuario;


}

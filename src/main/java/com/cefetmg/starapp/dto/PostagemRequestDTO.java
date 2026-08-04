package com.cefetmg.starapp.dto;

import com.cefetmg.starapp.entity.Usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostagemRequestDTO {

    @NotBlank(message = "O campo login é obrigatório")
    private String conteudo;

    @NotNull(message = "O campo perfil é obrigatório")
    private Usuario usuario;
}

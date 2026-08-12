package com.cefetmg.starapp.dto;

import com.cefetmg.starapp.entity.Usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostagemRequestDTO {

    @NotBlank(message = "O campo conteudo é obrigatório")
    private String conteudo;

    private Usuario usuario;
}

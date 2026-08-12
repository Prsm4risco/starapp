package com.cefetmg.starapp.dto;

import com.cefetmg.starapp.entity.Usuario;
import com.cefetmg.starapp.entity.Postagem;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class ComentarioRequestDTO {


    @NotBlank(message = "O campo conteúdo é obrigatório")
    private String conteudo;

    private Usuario usuario;

    @NotNull(message = "O campo postagem é obrigatório")
    private Postagem postagem;
}

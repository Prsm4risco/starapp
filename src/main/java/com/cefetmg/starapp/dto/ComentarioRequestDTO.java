package com.cefetmg.starapp.dto;

import com.cefetmg.starapp.entity.Usuario;
import com.cefetmg.starapp.entity.Postagem;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ComentarioRequestDTO {


    @NotBlank(message = "O campo login é obrigatório")
    private String conteudo;

    @NotNull(message = "O campo perfil é obrigatório")
    private Usuario usuario;

    @NotNull(message = "O campo perfil é obrigatório")
    private Postagem postagem;
}

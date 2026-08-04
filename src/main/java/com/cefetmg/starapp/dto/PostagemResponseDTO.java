package com.cefetmg.starapp.dto;

import com.cefetmg.starapp.entity.Postagem;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostagemResponseDTO {


    private Long id;
    private String conteudo;
    private Long usuarioId;

    public PostagemResponseDTO(Postagem postagem) {
        this.id = postagem.getId();
        this.conteudo = postagem.getConteudo();
        this.usuarioId = postagem.getUsuario().getId();
    }
}

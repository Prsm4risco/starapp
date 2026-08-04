package com.cefetmg.starapp.dto;

import com.cefetmg.starapp.entity.Comentario;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ComentarioResponseDTO {


    private Long id;
    private String conteudo;
    private Long usuarioId;
    private Long postagemId;

    public ComentarioResponseDTO(Comentario comentario) {
        this.id = comentario.getId();
        this.conteudo = comentario.getConteudo();
        this.usuarioId = comentario.getUsuario().getId();
        this.postagemId = comentario.getPostagem().getId();
    }
}

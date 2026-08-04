package com.cefetmg.starapp.dto;

import com.cefetmg.starapp.entity.Galeria;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GaleriaResponseDTO {


    private Long id;
    private String imgLink;
    private Long usuarioId;
    private Long postagemId;

    public GaleriaResponseDTO(Galeria galeria) {
        this.id = galeria.getId();
        this.imgLink = galeria.getImgLink();
    }
}

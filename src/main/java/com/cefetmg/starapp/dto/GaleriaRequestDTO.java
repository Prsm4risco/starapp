package com.cefetmg.starapp.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GaleriaRequestDTO {
    @NotBlank(message = "O link da imagem é obrigatório")
    private String imgLink;
}

package com.cefetmg.starapp.dto;
import lombok.Getter;

@Getter
public class AutenticacaoResponseDTO {


	private String token;


    public AutenticacaoResponseDTO(String token) {
    	this.token = token;

    }   
}
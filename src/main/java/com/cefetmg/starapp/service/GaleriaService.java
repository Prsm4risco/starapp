package com.cefetmg.starapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefetmg.starapp.dto.GaleriaRequestDTO;
import com.cefetmg.starapp.dto.GaleriaResponseDTO;
import com.cefetmg.starapp.entity.Galeria;
import com.cefetmg.starapp.exception.BusinessException;
import com.cefetmg.starapp.exception.ResourceNotFoundException;
import com.cefetmg.starapp.repository.GaleriaRepository;

@Service
public class GaleriaService {

    @Autowired
    private GaleriaRepository GaleriaRepository;

    @Transactional(readOnly = true)
    public List<GaleriaResponseDTO> listar() {
        List<Galeria> imagens = GaleriaRepository.findAll();
        return imagens.stream().map(GaleriaResponseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public GaleriaResponseDTO buscarPorId(Long id) {
        Galeria Galeria = GaleriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não encontrado. Id: " + id));

        return new GaleriaResponseDTO(Galeria);
    }

    @Transactional
    public GaleriaResponseDTO inserir(GaleriaRequestDTO dto) {

        Galeria Galeria = new Galeria();
        Galeria.setImgLink(dto.getImgLink());

        return new GaleriaResponseDTO(GaleriaRepository.save(Galeria));
    }

    @Transactional
    public GaleriaResponseDTO atualizar(Long id, GaleriaRequestDTO dto) {

        Galeria Galeria = GaleriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Imagem não encontrada. Id: " + id));

        return new GaleriaResponseDTO(GaleriaRepository.save(Galeria));
    }

    @Transactional
    public void excluir(Long id) {
        if (!GaleriaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não encontrado com ID: " + id);
        }

        GaleriaRepository.deleteById(id);
    }

}
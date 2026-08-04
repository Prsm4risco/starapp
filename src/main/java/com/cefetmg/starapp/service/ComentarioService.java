package com.cefetmg.starapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefetmg.starapp.dto.ComentarioRequestDTO;
import com.cefetmg.starapp.dto.ComentarioResponseDTO;
import com.cefetmg.starapp.entity.Comentario;
import com.cefetmg.starapp.exception.BusinessException;
import com.cefetmg.starapp.exception.ResourceNotFoundException;
import com.cefetmg.starapp.repository.ComentarioRepository;
@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Transactional(readOnly = true)
    public List<ComentarioResponseDTO> listar() {
        List<Comentario> comentarios = comentarioRepository.findAll();
        return comentarios.stream().map(ComentarioResponseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public ComentarioResponseDTO buscarPorId(Long id) {
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado. Id: " + id));

        return new ComentarioResponseDTO(comentario);
    }

    @Transactional
    public ComentarioResponseDTO inserir(ComentarioRequestDTO dto) {

        Comentario comentario = new Comentario();
        comentario.setConteudo(dto.getConteudo());
        comentario.setUsuario(dto.getUsuario());
        comentario.setPostagem(dto.getPostagem());

        return new ComentarioResponseDTO(comentarioRepository.save(comentario));
    }

    @Transactional
    public ComentarioResponseDTO atualizar(Long id, ComentarioRequestDTO dto) {

        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Comentario não encontrad. Id: " + id));


        return new ComentarioResponseDTO(comentarioRepository.save(comentario));
    }   


    @Transactional
    public void excluir(Long id) {
        if (!comentarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não encontrado com ID: " + id);
        }

        comentarioRepository.deleteById(id);
    }
      
}
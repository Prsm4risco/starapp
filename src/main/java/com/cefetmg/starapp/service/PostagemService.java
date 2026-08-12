package com.cefetmg.starapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefetmg.starapp.dto.PostagemRequestDTO;
import com.cefetmg.starapp.dto.PostagemResponseDTO;
import com.cefetmg.starapp.entity.Postagem;
import com.cefetmg.starapp.entity.Usuario;
import com.cefetmg.starapp.exception.BusinessException;
import com.cefetmg.starapp.exception.ResourceNotFoundException;
import com.cefetmg.starapp.repository.PostagemRepository;
import com.cefetmg.starapp.repository.UsuarioRepository;

@Service

public class PostagemService {

    @Autowired
    private PostagemRepository PostagemRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public List<PostagemResponseDTO> listar() {
        List<Postagem> postagens = PostagemRepository.findAll();
        return postagens.stream().map(PostagemResponseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public PostagemResponseDTO buscarPorId(Long id) {
        Postagem Postagem = PostagemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não encontrado. Id: " + id));

        return new PostagemResponseDTO(Postagem);
    }

    @Transactional
    public PostagemResponseDTO inserir(PostagemRequestDTO dto, Long usuarioId) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Postagem Postagem = new Postagem();
        Postagem.setConteudo(dto.getConteudo());
        Postagem.setUsuario(usuario);

        return new PostagemResponseDTO(PostagemRepository.save(Postagem));
    }

    @Transactional
    public PostagemResponseDTO atualizar(Long id, PostagemRequestDTO dto) {

        Postagem Postagem = PostagemRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Postagem não encontrada. Id: " + id));


        return new PostagemResponseDTO(PostagemRepository.save(Postagem));
    }   


    @Transactional
    public void excluir(Long id) {
        if (!PostagemRepository.existsById(id)) {
            throw new ResourceNotFoundException("Postagem não encontrada com ID: " + id);
        }

        PostagemRepository.deleteById(id);
    }
      
    
    @Transactional
    public Postagem buscarPorIdEntity(Long id) {
        Postagem postagem = PostagemRepository.findById(id)
                .orElse(null);

        return postagem;
    }
}

package com.cefetmg.starapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefetmg.starapp.dto.ComentarioRequestDTO;
import com.cefetmg.starapp.dto.ComentarioResponseDTO;
import com.cefetmg.starapp.entity.Comentario;
import com.cefetmg.starapp.entity.Usuario;
import com.cefetmg.starapp.entity.Postagem;
import com.cefetmg.starapp.exception.BusinessException;
import com.cefetmg.starapp.exception.ResourceNotFoundException;
import com.cefetmg.starapp.repository.ComentarioRepository;
import com.cefetmg.starapp.repository.PostagemRepository;
import com.cefetmg.starapp.repository.UsuarioRepository;
@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

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
    public List<ComentarioResponseDTO> listarPorPostagem(Long postagemId) {
        List<Comentario> comentarios = comentarioRepository.findByPostagemId(postagemId);
        return comentarios.stream().map(ComentarioResponseDTO::new).toList();
    }

@Transactional
public ComentarioResponseDTO inserir(
        ComentarioRequestDTO dto,
        Long usuarioId) {

    Usuario usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() ->
                new ResourceNotFoundException(
                    "Usuário não encontrado."
                )
            );

    Comentario comentario = new Comentario();
    comentario.setConteudo(dto.getConteudo());
    comentario.setUsuario(usuario);
    comentario.setPostagem(dto.getPostagem());

    return new ComentarioResponseDTO(
            comentarioRepository.save(comentario)
    );
}

    @Transactional
    public ComentarioResponseDTO atualizar(Long id, ComentarioRequestDTO dto) {

        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Não encontrado. Id: " + id));


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
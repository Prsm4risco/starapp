package com.cefetmg.starapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cefetmg.starapp.dto.PostagemRequestDTO;
import com.cefetmg.starapp.dto.PostagemResponseDTO;
import com.cefetmg.starapp.service.PostagemService;
import com.cefetmg.starapp.exception.*;
import com.cefetmg.starapp.repository.UsuarioRepository;
import com.cefetmg.starapp.entity.Usuario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/Postagens")
@CrossOrigin(origins = "http://localhost:8100", allowCredentials = "true")
@Tag(name = "postagem")
public class PostagemController {


    @Autowired
    private PostagemService PostagemService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    @Operation(summary = "Listar postagens")
    public ResponseEntity<List<PostagemResponseDTO>> listar() {
        List<PostagemResponseDTO> Postagems = PostagemService.listar();
        return ResponseEntity.ok(Postagems);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar postagem por ID")
    public ResponseEntity<PostagemResponseDTO> buscarPorId(@PathVariable Long id) {
        PostagemResponseDTO PostagemResponseDTO = PostagemService.buscarPorId(id);
        return ResponseEntity.ok(PostagemResponseDTO);
    }

    @PostMapping
    @Operation(summary = "Cadastrar postagem")
    public ResponseEntity<PostagemResponseDTO> inserir(@Valid @RequestBody PostagemRequestDTO requestDTO, Authentication authentication) {

    String login = authentication.getName();

    Usuario usuario = usuarioRepository.findByLogin(login)
            .orElseThrow(() ->
                new ResourceNotFoundException(
                    "Usuário não encontrado."
                )
            );

    PostagemResponseDTO PostagemResponseDTO =
            PostagemService.inserir(requestDTO, usuario.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(PostagemResponseDTO);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir postagem")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        PostagemService.excluir(id);
        return ResponseEntity.noContent().build();
    }
    
}

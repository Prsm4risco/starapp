package com.cefetmg.starapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.cefetmg.starapp.dto.ComentarioRequestDTO;
import com.cefetmg.starapp.dto.ComentarioResponseDTO;
import com.cefetmg.starapp.service.ComentarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/comentarios")
@CrossOrigin(origins = "http://localhost:8100")
@Tag(name = "Comentário")
public class ComentarioController {


    @Autowired
    private ComentarioService comentarioService;

    @GetMapping
    @Operation(summary = "Listar comentários")
    public ResponseEntity<List<ComentarioResponseDTO>> listar() {
        List<ComentarioResponseDTO> comentarios = comentarioService.listar();
        return ResponseEntity.ok(comentarios);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar comentário por ID")
    public ResponseEntity<ComentarioResponseDTO> buscarPorId(@PathVariable Long id) {
        ComentarioResponseDTO comentarioResponseDTO = comentarioService.buscarPorId(id);
        return ResponseEntity.ok(comentarioResponseDTO);
    }

    @GetMapping("/{id}/comentarios")
    @Operation(summary = "Buscar comentário por ID da postagem")
public ResponseEntity<List<ComentarioResponseDTO>> listarComentarios(@PathVariable Long id) {
        List<ComentarioResponseDTO> comentarios = comentarioService.listarPorPostagem(id);
        return ResponseEntity.ok(comentarios);
    }

    @PostMapping
    @Operation(summary = "Cadastrar comentário")
    public ResponseEntity<ComentarioResponseDTO> inserir(@Valid @RequestBody ComentarioRequestDTO comentarioRequestDTO) {
        ComentarioResponseDTO comentarioResponseDTO = comentarioService.inserir(comentarioRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(comentarioResponseDTO);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir comentário")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        comentarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }
    
}

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

import com.cefetmg.starapp.dto.GaleriaRequestDTO;
import com.cefetmg.starapp.dto.GaleriaResponseDTO;
import com.cefetmg.starapp.service.GaleriaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/galeria")
@CrossOrigin(origins = "http://localhost:8100")
@Tag(name = "Galeria")
public class GaleriaController {

    @Autowired
    private GaleriaService GaleriaService;

    @GetMapping
    @Operation(summary = "Listar imagens")
    public ResponseEntity<List<GaleriaResponseDTO>> listar() {
        List<GaleriaResponseDTO> imagens = GaleriaService.listar();
        return ResponseEntity.ok(imagens);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar imagem por ID")
    public ResponseEntity<GaleriaResponseDTO> buscarPorId(@PathVariable Long id) {
        GaleriaResponseDTO GaleriaResponseDTO = GaleriaService.buscarPorId(id);
        return ResponseEntity.ok(GaleriaResponseDTO);
    }

    @PostMapping
    @Operation(summary = "Cadastrar imagem")
    public ResponseEntity<GaleriaResponseDTO> inserir(@Valid @RequestBody GaleriaRequestDTO GaleriaRequestDTO) {
        GaleriaResponseDTO GaleriaResponseDTO = GaleriaService.inserir(GaleriaRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(GaleriaResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir imagem")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        GaleriaService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}

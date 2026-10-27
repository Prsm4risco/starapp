package com.cefetmg.starapp.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cefetmg.starapp.service.GaleriaService;
import com.cefetmg.starapp.entity.Galeria;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/galeria")
@CrossOrigin(origins = "http://localhost:8100", allowCredentials = "true")
@Tag(name = "Galeria")
public class GaleriaController {
    @Autowired
    private GaleriaService GaleriaService;

    @PostMapping
    public ResponseEntity<Galeria> upload(@RequestParam("galeria") MultipartFile Galeria) throws IOException {
        Galeria resultado = GaleriaService.salvar(Galeria);

        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }
    
    @GetMapping
    public ResponseEntity<List<Galeria>> listar() {
        List<Galeria> Galerias = GaleriaService.listar();
        return ResponseEntity.ok(Galerias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Galeria> buscarPorId(@PathVariable Long id) {
        Galeria Galeria = GaleriaService.buscarPorId(id);
        return ResponseEntity.ok(Galeria);
    }    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) throws IOException {
        GaleriaService.excluir(id);
        return ResponseEntity.noContent().build();
    }     
}   
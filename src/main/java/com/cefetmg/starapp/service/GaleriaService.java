package com.cefetmg.starapp.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.cefetmg.starapp.entity.Galeria;
import com.cefetmg.starapp.exception.BusinessException;
import com.cefetmg.starapp.exception.ResourceNotFoundException;
import com.cefetmg.starapp.repository.GaleriaRepository;

@Service
public class GaleriaService {

    @Autowired
    private GaleriaRepository GaleriaRepository;

    @Value("${app.upload.dir}")
    private String diretorioUpload;

    @Value("${app.upload.url}")
    private String urlGaleria;   
    
    public Galeria salvar(MultipartFile Galeria) throws IOException {

        Path diretorio = Paths.get(diretorioUpload);

        if (!Files.exists(diretorio)) {
            Files.createDirectories(diretorio);
        }

        String nomeOriginal = Galeria.getOriginalFilename();

        String extensao = "";

        if (nomeOriginal != null && nomeOriginal.contains(".")) {
            extensao = nomeOriginal.substring(nomeOriginal.lastIndexOf("."));
        }

        String nomeArmazenado = UUID.randomUUID().toString() + extensao;

        Path destino = diretorio.resolve(nomeArmazenado);

        Files.copy(Galeria.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);

        Galeria entidade = new Galeria();

        entidade.setNomeOriginal(nomeOriginal);
        entidade.setNomeArmazenado(nomeArmazenado);
        entidade.setTipo(Galeria.getContentType());
        entidade.setTamanho(Galeria.getSize());
        entidade.setCaminho(destino.toString());
        entidade.setUrl(urlGaleria + nomeArmazenado);

        return GaleriaRepository.save(entidade);
    }

    public List<Galeria> listar() {
        return GaleriaRepository.findAll();
    }

    public Galeria buscarPorId(Long id) {
        return GaleriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Galeria não encontrado."));
    }

    public boolean excluir(Long id) throws IOException {

        Galeria Galeria = GaleriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Galeria não encontrado."));

        if (Galeria == null) {
            return false;
        }

        Path caminhoGaleria = Paths.get(Galeria.getCaminho());
        Files.deleteIfExists(caminhoGaleria);
        GaleriaRepository.delete(Galeria);

        return true;
    }
}
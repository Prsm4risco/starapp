package com.cefetmg.starapp.service;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cefetmg.starapp.entity.Galeria;
import com.cefetmg.starapp.repository.GaleriaRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class GaleriaService {

    @Autowired
    private GaleriaRepository GaleriaRepository;

    @Autowired
    private Cloudinary cloudinary;

    public Galeria salvar(MultipartFile Galeria) throws IOException {

        String nomeOriginal = Galeria.getOriginalFilename();

        Map resultadoUpload = cloudinary.uploader().upload(Galeria.getBytes(), ObjectUtils.asMap("folder", "api-upload",  "resource_type", "auto"));

        String publicId = resultadoUpload.get("public_id").toString();
        String url = resultadoUpload.get("secure_url").toString();

        Galeria entidade = new Galeria();

        entidade.setNomeOriginal(nomeOriginal);
        entidade.setNomeArmazenado(publicId);
        entidade.setTipo(Galeria.getContentType());
        entidade.setTamanho(Galeria.getSize());
        entidade.setCaminho(publicId);
        entidade.setUrl(url);

        return GaleriaRepository.save(entidade);
    }

    public List<Galeria> listar() {
        return GaleriaRepository.findAll();
    }

    public Galeria buscarPorId(Long id) {
        return GaleriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Galeria não encontrado."));
    }

    public void excluir(Long id) throws IOException {
        Galeria Galeria = GaleriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Galeria não encontrado."));
        cloudinary.uploader().destroy(Galeria.getCaminho(), ObjectUtils.asMap("resource_type", "image"));
        GaleriaRepository.delete(Galeria);
    }
}
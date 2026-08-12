package com.cefetmg.starapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefetmg.starapp.entity.Postagem;

public interface PostagemRepository extends JpaRepository<Postagem, Long>{
	boolean existsByUsuarioId(Long usuarioId);

	Postagem findByUsuarioId(Long usuarioId);	
    Optional<Postagem> findById(Long id);	
}

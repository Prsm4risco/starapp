package com.cefetmg.starapp.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefetmg.starapp.entity.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long>{

	Comentario findByUsuarioId(Long usuarioId);	
	List<Comentario> findByPostagemId(Long postagemId);	
}

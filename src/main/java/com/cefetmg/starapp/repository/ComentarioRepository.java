package com.cefetmg.starapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cefetmg.starapp.entity.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long>{

	Comentario findByUsuarioId(Long usuarioId);	
	Comentario findByPostagemId(Long postagemId);	
}

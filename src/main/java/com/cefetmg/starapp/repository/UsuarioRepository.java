package com.cefetmg.starapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefetmg.starapp.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	boolean existsByLogin(String login);
	boolean existsByLoginAndIdNot(String login, Long id);
	
	Optional<Usuario> findByLoginAndSenha(String login, String senha);
    Optional<Usuario> findByLogin(String login);	
    Optional<Usuario> findById(Long id);	
}
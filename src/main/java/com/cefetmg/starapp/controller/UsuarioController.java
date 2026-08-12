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

import com.cefetmg.starapp.dto.AutenticacaoRequestDTO;
import com.cefetmg.starapp.dto.UsuarioRequestDTO;
import com.cefetmg.starapp.dto.UsuarioResponseDTO;
import com.cefetmg.starapp.dto.UsuarioSenhaRequestDTO;
import com.cefetmg.starapp.entity.Usuario;
import com.cefetmg.starapp.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:8100", allowCredentials = "true")
@Tag(name = "Usuário")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "Listar usuários")
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {
        List<UsuarioResponseDTO> usuarios = usuarioService.listar();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id) {
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(usuarioResponseDTO);
    }

    @PostMapping("/login")
    @Operation(summary = "autenticar")
    public ResponseEntity<UsuarioResponseDTO> buscarLoginSenha(@RequestBody AutenticacaoRequestDTO dados,
            HttpSession sessao) {
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.buscarLoginSenha(dados.getLogin(), dados.getSenha());
        if (usuarioResponseDTO == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        sessao.setAttribute("usuarioId", usuarioResponseDTO.getId());
        return ResponseEntity.ok(usuarioResponseDTO);
    }

    @GetMapping("/login")
    @Operation(summary = "Buscar usuário por Login")
    public ResponseEntity<UsuarioResponseDTO> buscarLogin(@RequestBody UsuarioRequestDTO dados) {

        UsuarioResponseDTO usuarioResponseDTO = usuarioService.buscarLogin(dados.getLogin());
        return ResponseEntity.ok(usuarioResponseDTO);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession sessao, HttpServletRequest request, HttpServletResponse response) {
        sessao = request.getSession(false);
        if (sessao != null) {
            sessao.invalidate();
        }
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);

        response.addCookie(cookie);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @Operation(summary = "Cadastrar usuário")
    public ResponseEntity<UsuarioResponseDTO> inserir(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.inserir(usuarioRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioResponseDTO);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Atualizar senha do usuário")
    public ResponseEntity<UsuarioResponseDTO> atualizarSenha(@PathVariable Long id,
            @Valid @RequestBody UsuarioSenhaRequestDTO usuarioSenhaRequestDTO) {
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.atualizar(id, usuarioSenhaRequestDTO);
        return ResponseEntity.ok(usuarioResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir usuário")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        usuarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/existe")
    @Operation(summary = "Verificar existência de login")
    public ResponseEntity<Boolean> existeLogin(@RequestParam String login) {
        return ResponseEntity.ok(usuarioService.existeLogin(login));
    }
@GetMapping("/quemLogado")
public ResponseEntity<UsuarioResponseDTO> usuarioLogado(HttpSession sessao) {
    Long usuarioId = (Long) sessao.getAttribute("usuarioId");
    if (usuarioId == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    UsuarioResponseDTO usuario = usuarioService.buscarPorId(usuarioId);
    return ResponseEntity.ok(usuario);
}
}

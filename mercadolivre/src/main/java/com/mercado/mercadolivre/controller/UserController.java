package com.mercado.mercadolivre.controller;

import javax.validation.Valid;

import com.mercado.mercadolivre.dto.UserRequest;
import com.mercado.mercadolivre.entity.Usuario;
import com.mercado.mercadolivre.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UserController {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid UserRequest userRequest) {
        Usuario usuario = userRequest.convertToUsuario();
        usuarioRepository.save(usuario);
        return ResponseEntity.ok().build();
    }
}

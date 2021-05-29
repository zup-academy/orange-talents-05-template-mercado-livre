package com.mercado.mercadolivre.controller;

import javax.validation.Valid;

import com.mercado.mercadolivre.security.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
    
    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody @Valid LoginFrom loginFrom) {
        UsernamePasswordAuthenticationToken dadosLogin = loginFrom.converter();
        System.out.println(dadosLogin);
        try {
            System.out.println("Passou aqui 1");
            Authentication authentication = authenticationManager.authenticate(dadosLogin);
            System.out.println("Passou aqui 2");
            String token = tokenService.gerarToken(authentication);
            System.out.println(token);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }
}

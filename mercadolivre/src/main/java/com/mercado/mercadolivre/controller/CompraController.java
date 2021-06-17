package com.mercado.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.mercado.mercadolivre.dto.CompraRequest;
import com.mercado.mercadolivre.entity.Compra;
import com.mercado.mercadolivre.entity.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos/")
public class CompraController {

    @Autowired
    EntityManager manager;

    @PostMapping("/comprar")
    @Transactional
    public ResponseEntity<?> comprar(@RequestBody @Valid CompraRequest compraRequest, @AuthenticationPrincipal Usuario usuario) {
        Compra compra = compraRequest.toModel(manager, usuario);
        manager.persist(compra);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(compra.getGatewayUrl());
    }
}

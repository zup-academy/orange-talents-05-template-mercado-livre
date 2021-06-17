package com.mercado.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.mercado.mercadolivre.dto.OpiniaoRequest;
import com.mercado.mercadolivre.entity.Opiniao;
import com.mercado.mercadolivre.entity.Produto;
import com.mercado.mercadolivre.entity.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos/{id}")
public class OpiniaoController {

    @Autowired
    EntityManager manager;
    
    @PostMapping("/opinioes")
    @Transactional
    public ResponseEntity<?> cadastrar(@PathVariable("id") Long id, @RequestBody @Valid OpiniaoRequest opiniaoRequest,
            @AuthenticationPrincipal Usuario usuario) {

        Produto produto = manager.find(Produto.class, id);
        if (produto == null) return ResponseEntity.notFound().build();

        Opiniao opiniao = opiniaoRequest.toModel(produto, usuario);
        manager.persist(opiniao);

        return ResponseEntity.ok().build();
    }
}

package com.mercado.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.mercado.mercadolivre.dto.ProdutoRequest;
import com.mercado.mercadolivre.entity.Produto;
import com.mercado.mercadolivre.entity.Usuario;
import com.mercado.mercadolivre.validate.ProibeCaracteristicasComNomeIgualValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    EntityManager manager;

    @InitBinder
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new ProibeCaracteristicasComNomeIgualValidator());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid ProdutoRequest produtoRequest, @AuthenticationPrincipal Usuario usuario) {
        Produto produto = produtoRequest.toModel(manager, usuario);
        manager.persist(produto);

        return ResponseEntity.ok().build();
    }
}

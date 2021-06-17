package com.mercado.mercadolivre.controller;

import javax.persistence.EntityManager;

import com.mercado.mercadolivre.dto.ProdutoResponse;
import com.mercado.mercadolivre.entity.Produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos/{id}")
public class DetalheProdutoController {
    
    @Autowired
    EntityManager manager;

    @GetMapping
    public ResponseEntity<ProdutoResponse> detalhesProduto(@PathVariable("id") Long id) {
        Produto produto = manager.find(Produto.class, id);

        if (produto == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new ProdutoResponse(produto));
    }
}

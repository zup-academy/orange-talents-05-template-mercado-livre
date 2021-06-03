package com.mercado.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.mercado.mercadolivre.dto.PerguntaRequest;
import com.mercado.mercadolivre.entity.Pergunta;
import com.mercado.mercadolivre.entity.Produto;
import com.mercado.mercadolivre.entity.Usuario;
import com.mercado.mercadolivre.util.Emails;

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
public class PerguntaController {

    @Autowired
    EntityManager manager;

    @Autowired
    private Emails emails;

    @PostMapping("/perguntas")
    @Transactional
    public ResponseEntity<?> perguntar(@PathVariable("id") Long id, @RequestBody @Valid PerguntaRequest perguntaRequest,
            @AuthenticationPrincipal Usuario usuario) {

        Produto produto = manager.find(Produto.class, id);
        if (produto == null) return ResponseEntity.notFound().build();

        Pergunta pergunta = perguntaRequest.toModel(produto, usuario);
        manager.persist(pergunta);

        emails.novaPergunta(pergunta);

        return ResponseEntity.ok().build();
    }
    
}

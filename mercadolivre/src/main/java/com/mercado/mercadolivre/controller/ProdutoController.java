package com.mercado.mercadolivre.controller;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.mercado.mercadolivre.dto.ImagemRequest;
import com.mercado.mercadolivre.dto.ProdutoRequest;
import com.mercado.mercadolivre.entity.Produto;
import com.mercado.mercadolivre.entity.Usuario;
import com.mercado.mercadolivre.util.UploaderFake;
import com.mercado.mercadolivre.validate.ProibeCaracteristicasComNomeIgualValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    EntityManager manager;

    @Autowired
    private UploaderFake uploaderFake;

    @InitBinder(value = "produtoRequest")
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new ProibeCaracteristicasComNomeIgualValidator());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid ProdutoRequest produtoRequest,
            @AuthenticationPrincipal Usuario usuario) {
        Produto produto = produtoRequest.toModel(manager, usuario);
        manager.persist(produto);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/imagens")
    @Transactional
    public ResponseEntity<?> adicionarImagens(@PathVariable("id") Long id, @Valid ImagemRequest imagemRequest, @AuthenticationPrincipal Usuario usuario) {
        Produto produto = manager.find(Produto.class, id);

        if (produto == null) return ResponseEntity.notFound().build();
        if (!produto.belongsTo(usuario)) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        Set<String> links = uploaderFake.envia(imagemRequest.getImagens());
        produto.associaImagens(links);
        manager.merge(produto);
        return ResponseEntity.ok().build();
    }
}

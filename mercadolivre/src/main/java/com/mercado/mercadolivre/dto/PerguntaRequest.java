package com.mercado.mercadolivre.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.mercado.mercadolivre.entity.Pergunta;
import com.mercado.mercadolivre.entity.Produto;
import com.mercado.mercadolivre.entity.Usuario;

public class PerguntaRequest {

    @NotBlank
    private String titulo;

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Pergunta toModel(@NotNull @Valid Produto produto, @NotNull @Valid Usuario usuario) {
        return new Pergunta(this.titulo, produto, usuario);
    }

}

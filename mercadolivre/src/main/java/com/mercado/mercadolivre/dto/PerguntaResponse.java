package com.mercado.mercadolivre.dto;

import com.mercado.mercadolivre.entity.Pergunta;

public class PerguntaResponse {

    private String titulo;

    public PerguntaResponse(Pergunta pergunta) {
        this.titulo = pergunta.getTitulo();
    }

    public String getTitulo() {
        return this.titulo;
    }

}

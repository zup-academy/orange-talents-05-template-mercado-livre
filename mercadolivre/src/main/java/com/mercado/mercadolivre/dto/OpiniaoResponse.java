package com.mercado.mercadolivre.dto;

import com.mercado.mercadolivre.entity.Opiniao;


public class OpiniaoResponse {

    private String opiniao;
    private String titulo;

    public OpiniaoResponse(Opiniao opiniao) {
        this.titulo = opiniao.getTitulo();
        this.opiniao = opiniao.getDescricao();
    }

    public String getOpiniao() {
        return this.opiniao;
    }

    public String getTitulo() {
        return this.titulo;
    }
}

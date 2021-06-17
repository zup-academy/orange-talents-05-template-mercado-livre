package com.mercado.mercadolivre.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.mercado.mercadolivre.entity.Opiniao;
import com.mercado.mercadolivre.entity.Produto;
import com.mercado.mercadolivre.entity.Usuario;

public class OpiniaoRequest {

    @NotNull
    @Min(1)
    @Max(5)
    private Integer nota;
    @NotBlank
    private String titulo;
    @NotBlank
    @Size(max = 500)
    private String descricao;
    

    public OpiniaoRequest(Integer nota, String titulo, String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }


    public Opiniao toModel(Produto produto, Usuario usuario) {
        return new Opiniao(this.nota, this.titulo, this.descricao, produto, usuario);
    }


}

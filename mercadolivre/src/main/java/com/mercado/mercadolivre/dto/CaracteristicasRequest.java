package com.mercado.mercadolivre.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.mercado.mercadolivre.entity.CaracteristicaProduto;
import com.mercado.mercadolivre.entity.Produto;

public class CaracteristicasRequest {
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;


    public CaracteristicasRequest(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }


    public String getNome() {
        return this.nome;
    }

    public String getDescricao() {
        return this.descricao;
    }


    public CaracteristicaProduto toModel(@NotNull @Valid Produto produto) {
        return new CaracteristicaProduto(nome, descricao, produto);
    }

}

package com.mercado.mercadolivre.dto;

import com.mercado.mercadolivre.entity.CaracteristicaProduto;

public class CaracteristicasResponse {

    private String nome;
    private String descricao;

    public CaracteristicasResponse(CaracteristicaProduto caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    public String getNome() {
        return this.nome;
    }

    public String getDescricao() {
        return this.descricao;
    }
}

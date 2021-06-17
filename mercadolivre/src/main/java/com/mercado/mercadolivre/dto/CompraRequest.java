package com.mercado.mercadolivre.dto;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.mercado.mercadolivre.entity.Compra;
import com.mercado.mercadolivre.entity.Produto;
import com.mercado.mercadolivre.entity.Usuario;
import com.mercado.mercadolivre.util.Gateway;
import com.mercado.mercadolivre.validate.OnStock;

@OnStock(message = "Não possuimos esta quantidade de produto")
public class CompraRequest {

    @Positive
    @NotNull    
    private Integer quantidade;

    @NotNull
    private String gateway;

    @NotNull
    private Long id;

    public CompraRequest(Integer quantidade, String gateway, Long id) {
        this.quantidade = quantidade;
        this.gateway = gateway;
        this.id = id;
    }    

    public Compra toModel(EntityManager manager, Usuario usuario) {
        @NotNull Produto produto = manager.find(Produto.class, this.id);

        return new Compra(this.quantidade, Gateway.valueOf(this.gateway), usuario, produto);
    }

    public Integer getQuantidade() {
        return this.quantidade;
    }

    public Long getId() {
        return this.id;
    }

}

package com.mercado.mercadolivre.entity;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.mercado.mercadolivre.util.Gateway;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @Positive @NotNull Integer quantidade;
    private @NotNull Gateway gateway;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private @NotNull Produto produto;
    private BigDecimal valor;
    private String code;

    public Compra(@Positive @NotNull Integer quantidade, @NotBlank Gateway gateway, Usuario usuario,
            @NotNull Produto produto) {
        this.quantidade = quantidade;
        this.gateway = gateway;
        this.usuario = usuario;
        this.produto = produto;
        this.valor = produto.getValor();
    }

    @Deprecated
    public Compra() {
    }

    @PrePersist
    private void prePersist() {
        this.code = UUID.randomUUID().toString();
    }

    public String getProductOwner() {
        return this.produto.getOwner().getUsername();
    }

    public String getGatewayUrl() {
        return this.gateway.getUrl(this.code);
    }

    public BigDecimal getValor() {
        return this.valor;
    }

}

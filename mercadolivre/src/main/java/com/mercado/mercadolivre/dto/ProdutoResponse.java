package com.mercado.mercadolivre.dto;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

import com.mercado.mercadolivre.entity.Produto;

public class ProdutoResponse {

    private String descricao;
    private String nome;
    private BigDecimal valor;
    private Set<CaracteristicasResponse> caracteristicas;
    private Set<ImagemResponse> links;
    private Set<PerguntaResponse> perguntas;
    private Set<OpiniaoResponse> opinioes;
    private Integer totalOpinioes;
    private BigDecimal mediaNotas;

    public ProdutoResponse(Produto produto) {
        this.descricao = produto.getDescricao();
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.caracteristicas = produto.getCaracteristicas().stream().map(caracteristica -> new CaracteristicasResponse(caracteristica)).collect(Collectors.toSet());
        this.links = produto.getImagens().stream().map(imagem -> new ImagemResponse(imagem)).collect(Collectors.toSet());
        this.perguntas = produto.getPerguntas().stream().map(pergunta -> new PerguntaResponse(pergunta)).collect(Collectors.toSet());
        this.opinioes = produto.getOpinioes().stream().map(opiniao -> new OpiniaoResponse(opiniao)).collect(Collectors.toSet());
        this.totalOpinioes = produto.getTotalOpinioes();
        this.mediaNotas = produto.getMediaOpinioes();
    }

    public String getDescricao() {
        return this.descricao;
    }

    public String getNome() {
        return this.nome;
    }

    public BigDecimal getValor() {
        return this.valor;
    }

    public Set<CaracteristicasResponse> getCaracteristicas() {
        return this.caracteristicas;
    }

    public Set<ImagemResponse> getLinks() {
        return this.links;
    }

    public Set<PerguntaResponse> getPerguntas() {
        return this.perguntas;
    }

    public Set<OpiniaoResponse> getOpinioes() {
        return this.opinioes;
    }

    public Integer getTotalOpinioes() {
        return this.totalOpinioes;
    }

    public BigDecimal getMediaNotas() {
        return this.mediaNotas;
    }

}

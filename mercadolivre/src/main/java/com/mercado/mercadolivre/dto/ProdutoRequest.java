package com.mercado.mercadolivre.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.mercado.mercadolivre.entity.Categoria;
import com.mercado.mercadolivre.entity.Produto;
import com.mercado.mercadolivre.entity.Usuario;
import com.mercado.mercadolivre.validate.UniqueValue;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

public class ProdutoRequest {

    @NotBlank
    @UniqueValue(entidade = Produto.class, atributo = "nome")
    private String nome;
    @NotNull
    @Positive
    private BigDecimal valor;
    @NotNull
    @PositiveOrZero
    private Integer quantidade;
    @NotBlank
    @Length(max = 1000)
    private String descricao;
    // @ExisteId(entidade = Categoria.class)
    @NotNull
    private Long categoria_id;
    @Size(min = 3)
    @Valid
    private List<CaracteristicasRequest> caracteristicas = new ArrayList<>();
    
    public ProdutoRequest(String nome, BigDecimal valor, Integer quantidade, String descricao, Long categoria_id, List<CaracteristicasRequest> caracteristicas) {
        super();
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria_id = categoria_id;
        this.caracteristicas.addAll(caracteristicas);
    }

    public List<CaracteristicasRequest> getCaracteristicas() {
        return this.caracteristicas;
    }


    public Produto toModel(EntityManager manager, Usuario usuario) {
        Assert.state(categoria_id != null, "categoria_id precisa existir para criar um produto");
        Assert.notNull(usuario, "user não pode ser nulo para criar um produto");

        Categoria categoria = manager.find(Categoria.class, this.categoria_id);
        Assert.notNull(categoria, "Categoria precisa existir para criar um Produto");

        return new Produto(this.nome, this.valor, this.quantidade, this.descricao, categoria, usuario, descricao, categoria, usuario, this.caracteristicas);
    }

    public boolean hasCaracteristicasIguais() {
        HashSet<String> nomesIguais = new HashSet<>();

        for(CaracteristicasRequest caracteristica : caracteristicas) {
            if(!nomesIguais.add(caracteristica.getNome())) return true;
        }
        return false;
    }


}

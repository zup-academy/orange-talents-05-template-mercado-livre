package com.mercado.mercadolivre.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class CaracteristicaProduto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private @NotBlank String nome;
    private @NotBlank String descricao;
    @ManyToOne
    private @NotNull @Valid Produto produto;

    public CaracteristicaProduto(@NotBlank String nome, @NotBlank String descricao, @NotNull @Valid Produto produto) {
        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }

    @Deprecated
    public CaracteristicaProduto() {
    }


    public String getNome() {
        return this.nome;
    }

    public String getDescricao() {
        return this.descricao;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CaracteristicaProduto)) {
            return false;
        }
        CaracteristicaProduto caracteristicaProduto = (CaracteristicaProduto) o;
        return Objects.equals(id, caracteristicaProduto.id) && Objects.equals(nome, caracteristicaProduto.nome) && Objects.equals(descricao, caracteristicaProduto.descricao) && Objects.equals(produto, caracteristicaProduto.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, produto);
    }
}

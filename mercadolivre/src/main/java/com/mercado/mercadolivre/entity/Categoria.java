package com.mercado.mercadolivre.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @ManyToOne @JoinColumn(name = "categoria_id", nullable = true)
    private Categoria categoria;

    public Categoria(@NotBlank String nome) {
        super();
        this.nome = nome;
    }

    @Deprecated
    public Categoria(){}

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}

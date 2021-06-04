package com.mercado.mercadolivre.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Pergunta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private @NotBlank String titulo;
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Usuario usuario;
    @NotNull
    private LocalDate instante;

    public Pergunta(@NotBlank String titulo, Produto produto, Usuario usuario) {
        this.titulo = titulo;
        this.produto = produto;
        this.usuario = usuario;
        this.instante = LocalDate.now();
    }

    @Deprecated
    public Pergunta() {
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public Usuario getOwnerProduto() {
        return produto.getOwner();
    }

    public String getTitulo() {
        return this.titulo;
    }

}

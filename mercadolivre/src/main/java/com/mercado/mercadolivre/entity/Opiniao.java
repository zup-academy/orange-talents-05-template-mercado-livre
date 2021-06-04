package com.mercado.mercadolivre.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Opiniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotNull @Min(1) @Max(5) Integer nota;
    private @NotBlank String titulo;
    private @NotBlank @Size(max = 500) String descricao;
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Usuario usuario;

    public Opiniao(@NotNull @Min(1) @Max(5) Integer nota, @NotBlank String titulo,
            @NotBlank @Size(max = 500) String descricao, Produto produto, Usuario usuario) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produto = produto;
        this.usuario = usuario;
    }

    @Deprecated
    public Opiniao() {
    }

    public Integer getNota() {
        return this.nota;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public String getTitulo() {
        return this.titulo;
    }


}

package com.mercado.mercadolivre.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.mercado.mercadolivre.dto.CaracteristicasRequest;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotBlank String nome;
    private @NotNull @Positive BigDecimal valor;
    private @NotNull @PositiveOrZero Integer quantidade;
    private @NotBlank @Length(max = 1000) String descricao;
    @ManyToOne
    private Categoria categoria;
    @NotNull
    private LocalDateTime cadastro = LocalDateTime.now();
    @ManyToOne
    private Usuario usuario;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<>();

    public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
            @NotNull @PositiveOrZero Integer quantidade, @NotBlank @Length(max = 1000) String descricao,
            Categoria categoria, Usuario usuario, @NotBlank @Length(max = 1000) String descricao2, Categoria categoria2,
            Usuario usuario2, @Size(min = 3) @Valid Collection<CaracteristicasRequest> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuario;
        this.caracteristicas.addAll(caracteristicas.stream().map(caracteristica -> caracteristica.toModel(this))
                .collect(Collectors.toSet()));

        Assert.isTrue(this.caracteristicas.size() >= 3, "Todo produto precisa ter no minimo 3 ou mais caracteristicas");
    }

    @Deprecated
    public Produto() {
    }

    public void associaImagens(Set<String> links) {
        Set<ImagemProduto> imagensProduto = links.stream().map(link -> new ImagemProduto(this, link)).collect(Collectors.toSet());

        this.imagens.addAll(imagensProduto);
    }

    public boolean belongsTo(Usuario user) {
        return this.usuario.equals(user);
    }
}

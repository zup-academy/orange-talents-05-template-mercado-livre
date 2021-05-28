package me.rayll.mercadolivreorangetalent.opiniaoproduto;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import me.rayll.mercadolivreorangetalent.produtos.Produto;
import me.rayll.mercadolivreorangetalent.usuario.Usuario;

public class OpiniaoProdutoDTO {
	
	@Min(1) @Max(5)
	private int nota;
	@NotEmpty
	private String titulo;
	@NotEmpty
	private String descricao;
	
	@Deprecated
	private OpiniaoProdutoDTO() {}
	
	public OpiniaoProdutoDTO(@Size(min = 1, max = 5) int nota, @NotEmpty String titulo, @NotEmpty String descricao) {
		super();
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public int getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public OpiniaoProduto toModel(@NotNull @Valid Produto produto, @NotNull @Valid Usuario usuario) {
		return new OpiniaoProduto(nota, titulo, descricao, produto, usuario);
	}
	
	
}

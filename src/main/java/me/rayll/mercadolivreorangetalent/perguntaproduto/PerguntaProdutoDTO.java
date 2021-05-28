package me.rayll.mercadolivreorangetalent.perguntaproduto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import me.rayll.mercadolivreorangetalent.produtos.Produto;
import me.rayll.mercadolivreorangetalent.usuario.Usuario;

public class PerguntaProdutoDTO {
	
	@NotEmpty
	private String titulo;
	
	@Deprecated
	private PerguntaProdutoDTO() {}

	public PerguntaProdutoDTO(@NotEmpty String titulo) {
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public PerguntaProduto toModel(Produto produto, Usuario usuario) {
		return new PerguntaProduto(titulo, produto, usuario);
	}
	
	
}

package me.rayll.mercadolivreorangetalent.perguntaproduto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import me.rayll.mercadolivreorangetalent.produtos.Produto;
import me.rayll.mercadolivreorangetalent.usuario.Usuario;

@Entity
public class PerguntaProduto {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private @NotEmpty String titulo;
	@ManyToOne
	private Produto produto;
	@ManyToOne
	private Usuario usuario;
	
	@Deprecated
	private PerguntaProduto() {}
	
	public PerguntaProduto(@NotEmpty String titulo, Produto produto, Usuario usuario) {
		this.titulo = titulo;
		this.produto = produto;
		this.usuario = usuario;	
	}
	
	public PerguntaProdutoDTO toDTO() {
		return new PerguntaProdutoDTO(titulo);
	}
}

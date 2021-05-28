package me.rayll.mercadolivreorangetalent.perguntaproduto;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;

import me.rayll.mercadolivreorangetalent.produtos.Produto;
import me.rayll.mercadolivreorangetalent.usuario.Usuario;

@Entity
public class PerguntaProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private @NotEmpty String titulo;
	@ManyToOne
	private Produto produto;
	@ManyToOne
	private Usuario usuario;
	@CreationTimestamp
	private Instant intente;

	@Deprecated
	private PerguntaProduto() {
	}

	public PerguntaProduto(@NotEmpty String titulo, Produto produto, Usuario usuario) {
		this.titulo = titulo;
		this.produto = produto;
		this.usuario = usuario;
	}

	public PerguntaProdutoDTO toDTO() {
		return new PerguntaProdutoDTO(titulo);
	}

	public Usuario getPerguntador() {
		return usuario;
	}

	public Usuario getDono() {
		return this.produto.getDono();
	}
}

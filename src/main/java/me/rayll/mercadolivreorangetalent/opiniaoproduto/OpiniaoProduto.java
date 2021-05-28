package me.rayll.mercadolivreorangetalent.opiniaoproduto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import me.rayll.mercadolivreorangetalent.produtos.Produto;
import me.rayll.mercadolivreorangetalent.usuario.Usuario;

@Entity
public class OpiniaoProduto {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private @NotNull @Valid Usuario usuario;
	@ManyToOne
	private @NotNull @Valid Produto produto;
	private @NotEmpty String descricao;
	private @NotEmpty String titulo;
	private @Min(1) @Max(5) int nota;
	
	@Deprecated
	private OpiniaoProduto() {}

	public OpiniaoProduto(@Min(1) @Max(5) int nota, @NotEmpty String titulo, @NotEmpty String descricao,
			@NotNull @Valid Produto produto, @NotNull @Valid Usuario usuario) {
			
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.produto = produto;
		this.usuario = usuario;
	}

	public OpiniaoProdutoDTO toDTO() {
		return new OpiniaoProdutoDTO(nota, titulo, descricao);
	}
	
	
}

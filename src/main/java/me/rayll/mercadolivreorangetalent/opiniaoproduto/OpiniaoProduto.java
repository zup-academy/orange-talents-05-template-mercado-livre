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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OpiniaoProduto other = (OpiniaoProduto) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}
	
	
}

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
public class PerguntaProduto implements Comparable<PerguntaProduto>{

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		PerguntaProduto other = (PerguntaProduto) obj;
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
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	@Override
	public int compareTo(PerguntaProduto o) {
		return this.titulo.compareTo(o.titulo);
	}
	
	
}

package me.rayll.mercadolivreorangetalent.categoria;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Categoria {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nome;
	@ManyToOne
	private Categoria categoriaMae;
	
	@Deprecated
	private Categoria() {}

	public Categoria(String nome) {
		this.nome = nome;
	}

	public void setCategoriaMae(Categoria categoriaMae) {
		this.categoriaMae = categoriaMae;
	}

	public CategoriaDTO toDto() {
		return new CategoriaDTO(nome);
	}

	public Long getId() {
		return this.id;
	}

}

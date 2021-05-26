package me.rayll.mercadolivreorangetalent.categoria;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import me.rayll.mercadolivreorangetalent.categoria.categoriaprincipal.CategoriaPrincipal;

@Entity
public class Categoria {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true)
	private String nome;
	
	private CategoriaPrincipal categoriaPrincipal;
	
	@Deprecated
	private Categoria() {}

	public Categoria(String nome, CategoriaPrincipal categoriaPrincipal) {
		this.nome = nome;
		this.categoriaPrincipal = categoriaPrincipal;
	}
	
	
}

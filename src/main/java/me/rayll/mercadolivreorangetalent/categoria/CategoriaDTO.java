package me.rayll.mercadolivreorangetalent.categoria;

import javax.validation.constraints.NotEmpty;

import me.rayll.mercadolivreorangetalent.categoria.categoriaprincipal.CategoriaPrincipal;
import me.rayll.mercadolivreorangetalent.validador.categorianome.NomeUnicoCategoria;

public class CategoriaDTO {

	@NotEmpty @NomeUnicoCategoria
	private String nome;
	
	private CategoriaPrincipal categoriaPrincipal;

	@Deprecated
	private CategoriaDTO() {}
	
	public CategoriaDTO(@NotEmpty String nome, CategoriaPrincipal categoriaPrincipal) {
		this.nome = nome;
		this.categoriaPrincipal = categoriaPrincipal;
	}

	public String getNome() {
		return nome;
	}

	public CategoriaPrincipal getCategoriaPrincipal() {
		return categoriaPrincipal;
	}

	public Categoria toModel() {
		return new Categoria(this.nome, this.categoriaPrincipal);
	}
	
	
}

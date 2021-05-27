package me.rayll.mercadolivreorangetalent.categoria;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import me.rayll.mercadolivreorangetalent.validador.categorianome.NomeUnicoCategoria;

public class CategoriaDTO {
	
	@NotEmpty @NomeUnicoCategoria
	private String nome;
	
	private Long idCategoriaMae;

	@Deprecated
	private CategoriaDTO() {}
	
	public CategoriaDTO(@NotEmpty String nome, Long idCategoriaMae) {
		this.nome = nome;
		this.idCategoriaMae = idCategoriaMae;
	}
	
	public CategoriaDTO(@NotEmpty String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public Long getIdCategoriaMae() {
		return idCategoriaMae;
	}

	@Override
	public String toString() {
		return "CategoriaDTO [nome=" + nome + ", idCategoriaMae=" + idCategoriaMae + "]";
	}

	public Categoria toModel() {
		return new Categoria(this.nome);
	}
	
	
}

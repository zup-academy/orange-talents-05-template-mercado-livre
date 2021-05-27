package me.rayll.mercadolivreorangetalent.categoria;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.jpa.repository.JpaRepository;

import me.rayll.mercadolivreorangetalent.categoria.categoriaprincipal.CategoriaPrincipal;
import me.rayll.mercadolivreorangetalent.validador.categorianome.NomeUnicoCategoria;

public class CategoriaDTO {

	@NotEmpty @NomeUnicoCategoria
	private String nome;
	
	private Long categoriaPrincipal;

	@Deprecated
	private CategoriaDTO() {}
	
	public CategoriaDTO(@NotEmpty String nome, Long categoriaPrincipal) {
		this.nome = nome;
		this.categoriaPrincipal = categoriaPrincipal;
	}

	public String getNome() {
		return nome;
	}

	public Long getCategoriaPrincipal() {
		return categoriaPrincipal;
	}

	public Categoria toModel(JpaRepository<Categoria, Long> repository) {
		Categoria categoriaToModel = repository.findById(this.categoriaPrincipal).get();
		return new Categoria(this.nome, categoriaToModel);
	}
	
	
}

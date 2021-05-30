package me.rayll.mercadolivreorangetalent.produtos;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CaracteristicaDTO {
	
	@NotEmpty
	private String nome;
	@NotEmpty
	private String descricao;
	
	@Deprecated
	private CaracteristicaDTO() {}
	
	public CaracteristicaDTO(@NotEmpty String nome, @NotEmpty String descricao) {
		
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return "CaracteristicaDTO [nome=" + nome + ", descricao=" + descricao + "]";
	}

	public Caracteristica toModel(@NotNull @Valid Produto produto) {
		return new Caracteristica(nome, descricao, produto);
	}
	
	 
}

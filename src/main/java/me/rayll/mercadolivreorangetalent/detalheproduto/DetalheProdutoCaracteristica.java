package me.rayll.mercadolivreorangetalent.detalheproduto;

import me.rayll.mercadolivreorangetalent.produtos.CaracteristicaDTO;

public class DetalheProdutoCaracteristica {

	private String descricao;
	private String nome;
	
	@Deprecated
	private DetalheProdutoCaracteristica() {}
	
	public DetalheProdutoCaracteristica(CaracteristicaDTO dto) {
		this.nome = dto.getNome();
		this.descricao =	dto.getDescricao();
	}

	public String getDescricao() {
		return descricao;
	}

	public String getNome() {
		return nome;
	}
	
}

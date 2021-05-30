package me.rayll.mercadolivreorangetalent.produtos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Caracteristica {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String descricao;
	@Valid 
	@ManyToOne
	private Produto produto;

	@Deprecated
	private Caracteristica() {}

	public Caracteristica(String nome, String descricao, @Valid Produto produto) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.produto = produto;
	}
	
	public CaracteristicaDTO toDTO() {
		return new CaracteristicaDTO(nome, descricao);
	}
}

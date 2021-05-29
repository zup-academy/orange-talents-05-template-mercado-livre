package me.rayll.mercadolivreorangetalent.finalizandocompra;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import me.rayll.mercadolivreorangetalent.produtos.Produto;
import me.rayll.mercadolivreorangetalent.usuario.Usuario;

@Entity
public class NovaCompra {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne @NotNull @Valid
	private Usuario usuario;
	
	private Integer quantidade;
	@ManyToOne @NotNull @Valid
	private Produto produto;
	@NotNull @Enumerated
	private GatewayPagamento tipoPagamento;

	@Deprecated
	private NovaCompra() {}
	
	public NovaCompra(Produto produto, Integer quantidade, Usuario usuario, GatewayPagamento tipoPagamento) {
		this.produto = produto;
		this.quantidade = quantidade;
		this.usuario = usuario;
		this.tipoPagamento = tipoPagamento;
	}

	public NovaCompraDTO toDTO() {
		NovaCompraDTO novaCompraDTO = new NovaCompraDTO(this.quantidade, this.produto.getId(), this.getTipoPagamento());
		novaCompraDTO.setId(this.id);
		return novaCompraDTO;
		
	}

	public GatewayPagamento getTipoPagamento() {
		return tipoPagamento;
	}
	
	
	
}

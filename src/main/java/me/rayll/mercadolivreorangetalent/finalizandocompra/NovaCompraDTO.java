package me.rayll.mercadolivreorangetalent.finalizandocompra;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class NovaCompraDTO {
	@JsonIgnore
	private Long id;
	@Positive
	private int quantidade;
	@NotNull
	private Long idProduto;
	@NotNull
	private GatewayPagamento gateway;
	
	@Deprecated
	private NovaCompraDTO() {}

	public NovaCompraDTO(@Positive int quantidade, @NotNull Long idProduto, GatewayPagamento gatewayPagamento) {
		this.quantidade = quantidade;
		this.idProduto = idProduto;
		this.gateway = gatewayPagamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public GatewayPagamento getGateway() {
		return gateway;
	}
	
	
}

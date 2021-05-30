package me.rayll.mercadolivreorangetalent.finalizandocompra;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;
import me.rayll.mercadolivreorangetalent.finalizandocompra.retornocompra.Transacao;
import me.rayll.mercadolivreorangetalent.usuario.Usuario;

import java.util.HashSet;
import java.util.Set;

public class NovaCompraDTO {
	@JsonIgnore
	private Long id;
	@Positive
	private int quantidade;
	@NotNull
	private Long idProduto;
	@NotNull
	private GatewayPagamento gateway;
	private Set<Transacao> transacoes = new HashSet<>();
	private Boolean compraFinalizada;
	private Long idUsuario;

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

	public Set<Transacao> getTransacoes() {
		return transacoes;
	}

    public void setTransacoes(Set<Transacao> transacoes) {
		this.transacoes = transacoes;
    }

	public void setCompraFinalizada(Boolean compraFinalizada) {
		this.compraFinalizada = compraFinalizada;
	}

	public void setUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Long getIdUsuario(){
		return this.idUsuario;
	}
}

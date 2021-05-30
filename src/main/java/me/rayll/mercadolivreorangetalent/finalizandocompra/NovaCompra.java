package me.rayll.mercadolivreorangetalent.finalizandocompra;

import me.rayll.mercadolivreorangetalent.finalizandocompra.retornocompra.IRetornoDeGateway;
import me.rayll.mercadolivreorangetalent.finalizandocompra.retornocompra.StatusTransacao;
import me.rayll.mercadolivreorangetalent.finalizandocompra.retornocompra.Transacao;
import me.rayll.mercadolivreorangetalent.produtos.Produto;
import me.rayll.mercadolivreorangetalent.usuario.Usuario;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;

@Entity
public class NovaCompra {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne @NotNull @Valid
	private Usuario usuario;
	@Positive
	private Integer quantidade;
	@ManyToOne @NotNull @Valid
	private Produto produto;
	@NotNull @Enumerated
	private GatewayPagamento tipoPagamento;
	@OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
	private Set<Transacao> transacoes = new HashSet<>();
	private Boolean compraFinalizada = false;

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
		novaCompraDTO.setTransacoes(this.transacoes);
		novaCompraDTO.setId(this.id);
		novaCompraDTO.setCompraFinalizada(this.compraFinalizada);
		novaCompraDTO.setUsuario(this.usuario.getId());
		return novaCompraDTO;
		
	}

	public GatewayPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	public Boolean compararTransacao(Transacao transacao){
		Boolean comparador = this.transacoes.contains(transacao);
		return comparador;
	}

    public Boolean adicionaRetornoTransacaoGateway(IRetornoDeGateway retornoDeGateway) {
		Transacao novaTransacao = retornoDeGateway.toTransacao(this);

		if(compararTransacao(novaTransacao)){ throw new IllegalArgumentException("ID da transação repetido!");}

		if(novaTransacao.getStatusTransacao() == StatusTransacao.SUCESSO){
			if(this.compraFinalizada){
				throw new IllegalArgumentException("Essa compra já está encerrada com sucesso.");
			}else{
				this.compraFinalizada = true;
				this.transacoes.add(novaTransacao);
				return true;
			}
		}else if(this.compraFinalizada){
			throw new IllegalArgumentException("Essa compra já está encerrada com sucesso.");
		}else {
			this.transacoes.add(novaTransacao);
		}
		return false;
	}



}

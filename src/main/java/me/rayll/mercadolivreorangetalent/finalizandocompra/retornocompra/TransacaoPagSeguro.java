package me.rayll.mercadolivreorangetalent.finalizandocompra.retornocompra;

import me.rayll.mercadolivreorangetalent.finalizandocompra.NovaCompra;

import javax.validation.constraints.NotNull;

public class TransacaoPagSeguro implements IRetornoDeGateway{

    @NotNull
    private Long idRetornoGateway;
    @NotNull
    private StatusTransacao statusTransacao;

    @Deprecated
    private TransacaoPagSeguro() {}

    public TransacaoPagSeguro(Long idRetornoGateway, StatusTransacao statusTransacao) {
        this.idRetornoGateway = idRetornoGateway;
        this.statusTransacao = statusTransacao;
    }

    @Override
    public Transacao toTransacao(NovaCompra novaCompra) {

        return new Transacao(idRetornoGateway, novaCompra, statusTransacao);
    }

}

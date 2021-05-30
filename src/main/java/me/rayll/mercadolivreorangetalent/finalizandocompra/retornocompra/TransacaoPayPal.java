package me.rayll.mercadolivreorangetalent.finalizandocompra.retornocompra;

import me.rayll.mercadolivreorangetalent.finalizandocompra.NovaCompra;

import javax.validation.constraints.NotNull;

public class TransacaoPayPal implements IRetornoDeGateway{
    @NotNull
    private Long idRetornoGateway;
    @NotNull
    private int status;

    @Deprecated
    private TransacaoPayPal() {}

    public TransacaoPayPal(Long idRetornoGateway, int status) {
        this.idRetornoGateway = idRetornoGateway;
        this.status = status;
    }

    @Override
    public Transacao toTransacao(NovaCompra novaCompra) {
        StatusTransacao statusTransacao;
        if(this.status == 0){
            statusTransacao = StatusTransacao.FALHA;
        }else{
            statusTransacao = StatusTransacao.SUCESSO;
        }
        return new Transacao(idRetornoGateway, novaCompra, statusTransacao);
    }
}

package me.rayll.mercadolivreorangetalent.finalizandocompra.retornocompra;

import me.rayll.mercadolivreorangetalent.finalizandocompra.NovaCompra;

public interface IRetornoDeGateway {

    public Transacao toTransacao(NovaCompra novaCompra);
}

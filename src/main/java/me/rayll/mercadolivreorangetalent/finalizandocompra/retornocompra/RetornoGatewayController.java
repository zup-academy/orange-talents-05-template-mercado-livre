package me.rayll.mercadolivreorangetalent.finalizandocompra.retornocompra;

import me.rayll.mercadolivreorangetalent.finalizandocompra.CompraRepository;
import me.rayll.mercadolivreorangetalent.finalizandocompra.NovaCompra;
import me.rayll.mercadolivreorangetalent.finalizandocompra.NovaCompraDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/mercadolivre/v1")
public class RetornoGatewayController {

    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private EnviarEmails enviarEmails;

    @PostMapping("/retorno-paypal/{idCompra}")
    public NovaCompraDTO processamentoPayPal(@PathVariable Long idCompra, @Valid TransacaoPayPal transacaoPayPal){
        return processamentoTransacao(idCompra, transacaoPayPal);
    }

    @PostMapping("/retorno-pagseguro/{idCompra}")
    public NovaCompraDTO processamentoPayPal(@PathVariable Long idCompra, @Valid TransacaoPagSeguro transacaoPagSeguro){

        return processamentoTransacao(idCompra, transacaoPagSeguro);
    }

    private NovaCompraDTO processamentoTransacao(Long idCompra, IRetornoDeGateway retornoDeGateway){
        NovaCompra compra = compraRepository.findById(idCompra).get();
        Boolean compraFinalizada = compra.adicionaRetornoTransacaoGateway(retornoDeGateway);

        if(compraFinalizada){
            enviarEmails.enviarSucesso(compra);
        }else{
            String linkParaCompra = "http://localhost:8080/mercadolivre/v1/compras";
            enviarEmails.enviarFalha(linkParaCompra);
        }

        compraRepository.save(compra);

        return compra.toDTO();
    }
}

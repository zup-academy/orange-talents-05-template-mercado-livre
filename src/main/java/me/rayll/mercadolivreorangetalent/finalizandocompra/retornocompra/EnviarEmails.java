package me.rayll.mercadolivreorangetalent.finalizandocompra.retornocompra;

import me.rayll.mercadolivreorangetalent.finalizandocompra.NovaCompra;
import me.rayll.mercadolivreorangetalent.finalizandocompra.NovaCompraDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class EnviarEmails {

    public void enviarSucesso(NovaCompra novaCompra){
        NovaCompraDTO dto = novaCompra.toDTO();
        Long idCompra = dto.getId();
        Long idUsuario = dto.getIdUsuario();

        Map<String, Long> objeto = Map.of("idCompra", idCompra, "idUsuario", idUsuario);
        System.out.println("Nota Fiscal: " + objeto.toString());
        System.out.println("Ranking: " + objeto.toString());
    }

    public void enviarFalha(String linkParaCompra) {
        System.out.println("Não foi possível efetuar a compra, tenta novamente! " + linkParaCompra);
    }
}

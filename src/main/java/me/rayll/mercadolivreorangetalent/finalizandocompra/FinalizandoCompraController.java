package me.rayll.mercadolivreorangetalent.finalizandocompra;

import me.rayll.mercadolivreorangetalent.produtos.Produto;
import me.rayll.mercadolivreorangetalent.produtos.ProdutoRepository;
import me.rayll.mercadolivreorangetalent.usuario.Usuario;
import me.rayll.mercadolivreorangetalent.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/mercadolivre/v1/compras")
public class FinalizandoCompraController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CompraRepository compraRepository;

    @PostMapping
    @ResponseStatus(value = HttpStatus.FOUND)
    @Transactional
    public String encerrarCompra(@RequestBody @Valid NovaCompraDTO dto, @AuthenticationPrincipal UserDetails user, UriComponentsBuilder uriComponentsBuilder) throws BindException {

        GatewayPagamento tipoPagamento = dto.getGateway();
        Integer quantidade = dto.getQuantidade();
        Usuario usuario = usuarioRepository.findByLogin(user.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        Produto produto = produtoRepository.findById(dto.getIdProduto()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        boolean podeDiminuirQuantidade = produto.abaterDoEstoque(dto.getQuantidade());

        if (!podeDiminuirQuantidade) {
            BindException problemaComEstoque = new BindException(dto, "novaCompra");
            problemaComEstoque.reject(null, "A quantidade do produto está menor que o estoque.");
            throw problemaComEstoque;

        }
        NovaCompra novaCompra = new NovaCompra(produto, quantidade, usuario, tipoPagamento);
        compraRepository.save(novaCompra);
        NovaCompraDTO compraSalva = novaCompra.toDTO();

        String redirectUrl;

        if (tipoPagamento.equals(GatewayPagamento.pagseguro)) {
            String urlRetornoPagseguro = uriComponentsBuilder.path("/retorno-pagseguro/{id}")
                    .buildAndExpand(compraSalva.getId()).toString();
            redirectUrl = "pagsegurocom?returnId" + compraSalva.getId() + "&redirectUrl=" + urlRetornoPagseguro;
        } else {
            String urlRetornoPaypal = uriComponentsBuilder.path("/retorno-paypal/{id}")
                    .buildAndExpand(compraSalva.getId()).toString();
            redirectUrl = "paypal.com?buyerId" + compraSalva.getId() + "&redirectUrl=" + urlRetornoPaypal;
        }

        return redirectUrl;
    }

}

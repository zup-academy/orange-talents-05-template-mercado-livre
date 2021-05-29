package me.rayll.mercadolivreorangetalent.finalizandocompra;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import me.rayll.mercadolivreorangetalent.produtos.Produto;
import me.rayll.mercadolivreorangetalent.produtos.ProdutoRepository;
import me.rayll.mercadolivreorangetalent.usuario.Usuario;
import me.rayll.mercadolivreorangetalent.usuario.UsuarioRepository;

@RestController
@RequestMapping("/mercadolivre/v1/compras")
public class FinalizandoCompra {

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
		
		if(!podeDiminuirQuantidade) {
			BindException problemaComEstoque = new BindException(dto, "novaCompra");
			problemaComEstoque.reject(null, "A quantidade do produto está menor que o estoque.");
			throw problemaComEstoque; 
			
		}
		NovaCompra novaCompra = new NovaCompra(produto,  quantidade, usuario, tipoPagamento);
		compraRepository.save(novaCompra);
		NovaCompraDTO compraSalva =novaCompra.toDTO();
		
		String redirectUrl;
		
		if(tipoPagamento.equals(GatewayPagamento.pagseguro)) {
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

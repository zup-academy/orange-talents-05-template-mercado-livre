package me.rayll.mercadolivreorangetalent.detalheproduto;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import me.rayll.mercadolivreorangetalent.produtos.Produto;
import me.rayll.mercadolivreorangetalent.produtos.ProdutoDTO;
import me.rayll.mercadolivreorangetalent.produtos.ProdutoRepository;

@RestController
@RequestMapping("/mercadolivre/v1/produto")
public class DetalhesProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping("/{id}")
	public DetalhesProduto exibeDetalhesProdutos(@PathVariable Long id) {
		Produto produto =  produtoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
		return new DetalhesProduto(produto.toDTO());
	}
}

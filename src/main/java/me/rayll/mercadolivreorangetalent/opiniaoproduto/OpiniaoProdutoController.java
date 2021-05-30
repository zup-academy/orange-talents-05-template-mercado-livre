package me.rayll.mercadolivreorangetalent.opiniaoproduto;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.rayll.mercadolivreorangetalent.produtos.Produto;
import me.rayll.mercadolivreorangetalent.produtos.ProdutoRepository;
import me.rayll.mercadolivreorangetalent.usuario.Usuario;
import me.rayll.mercadolivreorangetalent.usuario.UsuarioRepository;

@RestController
@RequestMapping("mercadolivre/v1/produto")
public class OpiniaoProdutoController {
		
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private OpiniaoProdutoRepository opiniaoRepository;
	
	@PostMapping("/{id}/opiniaoproduto")
	public OpiniaoProdutoDTO cadastrarOpiniaoProduto(@PathVariable Long id, @RequestBody @Valid OpiniaoProdutoDTO dto) {
		
		Produto produto = produtoRepository.findById(id).get();
		Usuario usuario = usuarioRepository.findByLogin("rayller@gmail.com").get();
		
		OpiniaoProduto opiniao = opiniaoRepository.save(dto.toModel(produto, usuario));
		
		return opiniao.toDTO();
	}
}

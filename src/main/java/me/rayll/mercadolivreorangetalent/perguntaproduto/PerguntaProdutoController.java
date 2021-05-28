package me.rayll.mercadolivreorangetalent.perguntaproduto;

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
@RequestMapping("/mercadolivre/v1/produto")
public class PerguntaProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PerguntaRepository perguntaRepository;
	
	@PostMapping("/{id}/pergunta")
	public PerguntaProdutoDTO cadastroNovaPergunta(@RequestBody PerguntaProdutoDTO dto, @PathVariable Long id) {
		Produto produto = produtoRepository.findById(id).get();
		Usuario usuario = usuarioRepository.findByLogin("rayller@gmail.com").get();
		
		PerguntaProduto perguntaNova = perguntaRepository.save(dto.toModel(produto, usuario));
		
		return perguntaNova.toDTO();
	}
	
}

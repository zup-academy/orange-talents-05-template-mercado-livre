package me.rayll.mercadolivreorangetalent.perguntaproduto;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
	
	@Autowired
	private Emails emails;
	
	@PostMapping("/{id}/pergunta")
	@ResponseStatus(value = HttpStatus.OK)
	@Transactional
	public PerguntaProdutoDTO cadastroNovaPergunta(@RequestBody PerguntaProdutoDTO dto, @PathVariable Long id) {
		Produto produto = produtoRepository.findById(id).get();
		Usuario usuario = usuarioRepository.findByLogin("rayller@gmail.com").get();
		
		PerguntaProduto perguntaNova = perguntaRepository.save(dto.toModel(produto, usuario));
		
		emails.novaPergunta(perguntaNova);
		
		return perguntaNova.toDTO();
	}
	
}

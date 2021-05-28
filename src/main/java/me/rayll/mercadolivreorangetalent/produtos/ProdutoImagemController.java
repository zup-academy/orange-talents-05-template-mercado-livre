package me.rayll.mercadolivreorangetalent.produtos;

import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import me.rayll.mercadolivreorangetalent.usuario.Usuario;
import me.rayll.mercadolivreorangetalent.usuario.UsuarioRepository;

@RestController
@RequestMapping("/mercadolivre/v1/produto")
public class ProdutoImagemController {

	@Autowired
	private Uploader uploadFake;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@PostMapping("/{id}/imagens")
	@Transactional
	public String adicionaImagens(@PathVariable("id") Long id, @Valid NovasImagensRequest request) {
		
		Usuario dono = usuarioRepository.findByLogin("rayla@gmail.com").get();
		Produto produto = produtoRepository.findById(id).get();
		
		if(!produto.pertenceAoUsuario(dono)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
			
		Set<String> links = uploadFake.envia(request.getImagens());
		produto.associaImagens(links);
		
		produtoRepository.save(produto);
		
		return produto.toString();
	}
}

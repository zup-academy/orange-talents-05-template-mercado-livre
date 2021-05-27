package me.rayll.mercadolivreorangetalent.produtos;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import me.rayll.mercadolivreorangetalent.categoria.Categoria;
import me.rayll.mercadolivreorangetalent.categoria.CategoriaRepository;
import me.rayll.mercadolivreorangetalent.usuario.Usuario;
import me.rayll.mercadolivreorangetalent.usuario.UsuarioRepository;

@RestController
@RequestMapping("/mercadolivre/v1/produto")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@InitBinder
	public void init(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(new ProibeCaracteristicasComNomeIgualValidador());
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.OK)
	@Transactional
	public ProdutoDTO cadastroNovoProduto(@RequestBody @Valid ProdutoDTO dto) {
		Categoria categoria = categoriaRepository.findById(dto.getIdCategoria()).get();
		Usuario dono = usuarioRepository.findByLogin("rayller@gmail.com").get();

		Produto produto = produtoRepository.save(dto.toModel(categoria, dono));

		return produto.toDTO();
	}
}

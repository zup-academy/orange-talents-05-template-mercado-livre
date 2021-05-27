package me.rayll.mercadolivreorangetalent.categoria;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import me.rayll.mercadolivreorangetalent.usuario.UsuarioRepository;

@RestController
@RequestMapping("mercadolivre/v1/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.OK)
	@Transactional
	public CategoriaDTO cadastroNovaCategoria(@RequestBody @Valid CategoriaDTO dto) {
		Categoria categoriaNova;
		
		if(dto.getIdCategoriaMae() != null) {
			Categoria c = categoriaRepository.findById(dto.getIdCategoriaMae()).get();
			categoriaNova = dto.toModel();
			categoriaNova.setCategoriaMae(c);
		}else {
			categoriaNova = dto.toModel();
		}
		
		Categoria categoriaSalva = categoriaRepository.save(categoriaNova);
		
		return categoriaSalva.toDto();
	}

}

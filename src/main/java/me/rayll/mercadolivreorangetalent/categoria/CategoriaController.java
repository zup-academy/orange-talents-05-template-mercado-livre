package me.rayll.mercadolivreorangetalent.categoria;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mercadolivre/v1/categoria")
public class CategoriaController {
	
	@Autowired
	CategoriaRepository repository;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.OK)
	@Transactional
	public void cadastroNovaCategoria(@RequestBody @Valid CategoriaDTO dto) {
		
		Categoria categoria = repository.save(dto.toModel(repository));
	}
}

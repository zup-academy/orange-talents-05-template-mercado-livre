package me.rayll.mercadolivreorangetalent.usuario;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import me.rayll.mercadolivreorangetalent.encriptador.Encriptador;

@RestController
@RequestMapping("mercadolivre/v1/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioRepository repository;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.OK)
	@Transactional
	public void cadastroNovoUsuario(@RequestBody @Valid UsuarioDTO dto) {
		String encodingPass = Encriptador.encode(dto.getPass());
		dto.setPass(encodingPass);
		Usuario usuarioCadastrado = repository.save(dto.toModel());		
	}
}

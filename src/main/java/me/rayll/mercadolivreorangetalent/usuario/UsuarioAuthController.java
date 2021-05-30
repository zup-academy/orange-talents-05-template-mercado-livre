package me.rayll.mercadolivreorangetalent.usuario;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import me.rayll.mercadolivreorangetalent.encriptador.Encriptador;
import me.rayll.mercadolivreorangetalent.seguranca.TratamentoDeAutenticacao;
import me.rayll.mercadolivreorangetalent.seguranca.jwt.JWTUso;

@RestController
@RequestMapping("mercadolivre/v1/usuario/auth")
public class UsuarioAuthController {
	
	@Autowired
	private TratamentoDeAutenticacao tsc;
	
	private JWTUso jwtUso = new JWTUso();
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.OK)
	@Transactional
	public String autenticarUsuario(@RequestBody UsuarioDTO dto) {
		
		try {
			
			tsc.autenticar(dto);
			String token = jwtUso.gerarToken(dto);
			return token;
		}catch (UsernameNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ex.getMessage());
		}
	}
	
}

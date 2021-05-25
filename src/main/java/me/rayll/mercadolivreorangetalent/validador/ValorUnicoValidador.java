package me.rayll.mercadolivreorangetalent.validador;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import me.rayll.mercadolivreorangetalent.cliente.UsuarioRepository;

@Component
public class ValorUnicoValidador implements ConstraintValidator<ValorUnico, Object>{

	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		return repository.existsByLogin(value.toString());
		
	}

}

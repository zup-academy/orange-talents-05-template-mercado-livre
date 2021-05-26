package me.rayll.mercadolivreorangetalent.validador.categorianome;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import me.rayll.mercadolivreorangetalent.categoria.CategoriaRepository;

@Component
public class NomeUnicoCategoriaValidador implements ConstraintValidator<NomeUnicoCategoria, Object>{
	
	@Autowired
	CategoriaRepository repository;
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		return !repository.existsByNome(value.toString());
	}

}

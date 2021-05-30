package me.rayll.mercadolivreorangetalent.produtos;

import java.util.Set;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProibeCaracteristicasComNomeIgualValidador implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ProdutoDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return;
		}
		
		ProdutoDTO request = (ProdutoDTO) target;
		Set<String> nomesIguais = request.temCaracteristicasIguais();
		if(!nomesIguais.isEmpty()) {
			errors.reject("caracteristicas", null, "Caracteristicas repetidas." +  nomesIguais);
		}
	}

}

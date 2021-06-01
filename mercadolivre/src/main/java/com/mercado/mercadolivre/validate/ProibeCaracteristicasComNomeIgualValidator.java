package com.mercado.mercadolivre.validate;

import com.mercado.mercadolivre.dto.ProdutoRequest;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProibeCaracteristicasComNomeIgualValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ProdutoRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){
            return;
        }

        ProdutoRequest produtoRequest = (ProdutoRequest) target;
        if(produtoRequest.hasCaracteristicasIguais()){
            errors.rejectValue("Caracteristicas", null, "Voce tem caracteristicas iguais");
        }    
    }


}

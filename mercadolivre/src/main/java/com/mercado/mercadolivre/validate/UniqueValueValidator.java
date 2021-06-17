package com.mercado.mercadolivre.validate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, String> {

    Class<?> entidade;
    String atributo;

    @PersistenceContext
    private EntityManager manager;

    public void initialize(UniqueValue annotationParams) {
        this.entidade = annotationParams.entidade();
        this.atributo = annotationParams.atributo();
    }


    public boolean isValid(String valor, ConstraintValidatorContext context) {
        Query query = manager.createQuery("SELECT 1 FROM " + entidade.getName() + " WHERE " + atributo + " = :valor");

        query.setParameter("valor", valor);

        return query.getResultList().isEmpty();
    }
        
}

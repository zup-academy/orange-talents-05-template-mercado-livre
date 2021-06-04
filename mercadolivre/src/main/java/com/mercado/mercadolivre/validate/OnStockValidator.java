package com.mercado.mercadolivre.validate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.mercado.mercadolivre.dto.CompraRequest;
import com.mercado.mercadolivre.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class OnStockValidator implements ConstraintValidator<OnStock, CompraRequest> {

    @Autowired
    ProdutoRepository produtoRepository;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean isValid(CompraRequest compraRequest, ConstraintValidatorContext context) {
        Integer quantidade = compraRequest.getQuantidade();
        Long id = compraRequest.getId();

        Query query = manager.createQuery("SELECT p.quantidade FROM Produto p WHERE p.id = :id");
        query.setParameter("id", id);
        List<?> resultado = query.getResultList();

        if (Integer.parseInt(resultado.get(0).toString()) < quantidade) return false;

        return true;
    }

}
